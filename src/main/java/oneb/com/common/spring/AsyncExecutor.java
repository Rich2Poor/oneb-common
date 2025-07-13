package oneb.com.common.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Async executor for background tasks.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
@Component
@Slf4j
public class AsyncExecutor {
    
    private final ExecutorService executorService;
    private final ConcurrentHashMap<Object, Object> locks = new ConcurrentHashMap<>();
    
    public AsyncExecutor() {
        this.executorService = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("async-executor-" + thread.getId());
            return thread;
        });
    }
    
    /**
     * Execute runnable asynchronously.
     */
    public CompletableFuture<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(runnable, executorService)
                .exceptionally(ex -> {
                    log.error("Error in async execution", ex);
                    throw new RuntimeException(ex);
                });
    }
    
    /**
     * Execute runnable asynchronously with key-based locking.
     */
    public CompletableFuture<Void> runAsync(Runnable runnable, Object keyLock) {
        return runAsync(() -> {
            log.debug("Running async task with lock key: {}", keyLock);
            Object lock = locks.computeIfAbsent(keyLock, k -> new Object());
            synchronized (lock) {
                runnable.run();
            }
            log.debug("Completed async task with lock key: {}", keyLock);
        });
    }
    
    /**
     * Execute supplier asynchronously.
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return CompletableFuture.supplyAsync(supplier, executorService)
                .exceptionally(ex -> {
                    log.error("Error in async execution", ex);
                    throw new RuntimeException(ex);
                });
    }
    
    /**
     * Execute supplier asynchronously with key-based locking.
     */
    public <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, Object keyLock) {
        return supplyAsync(() -> {
            log.debug("Running async supplier with lock key: {}", keyLock);
            Object lock = locks.computeIfAbsent(keyLock, k -> new Object());
            synchronized (lock) {
                return supplier.get();
            }
        });
    }
    
    /**
     * Execute runnable with delay.
     */
    public CompletableFuture<Void> runDelayed(Runnable runnable, long delay, TimeUnit timeUnit) {
        return CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(timeUnit.toMillis(delay));
                runnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }, executorService);
    }
    
    /**
     * Execute supplier with timeout.
     */
    public <T> CompletableFuture<T> supplyWithTimeout(Supplier<T> supplier, long timeout, TimeUnit timeUnit) {
        CompletableFuture<T> future = supplyAsync(supplier);

        // Create a scheduled executor for timeout
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        CompletableFuture<T> timeoutFuture = new CompletableFuture<>();

        scheduler.schedule(() -> {
            if (!future.isDone()) {
                timeoutFuture.completeExceptionally(new TimeoutException("Operation timed out"));
            }
            scheduler.shutdown();
        }, timeout, timeUnit);

        return CompletableFuture.anyOf(future, timeoutFuture)
                .thenApply(result -> (T) result);
    }
    
    @PreDestroy
    public void shutdown() {
        log.info("Shutting down async executor");
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    log.warn("Executor did not terminate");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
