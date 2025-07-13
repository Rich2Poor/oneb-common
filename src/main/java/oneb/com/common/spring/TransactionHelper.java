package oneb.com.common.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

/**
 * Helper class for transaction management.
 * Requires Spring Transaction dependency to be available.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionHelper {

    /**
     * Execute supplier in a new transaction.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T isolate(Supplier<T> supplier) {
        log.debug("Executing supplier in new transaction");
        return supplier.get();
    }

    /**
     * Execute runnable in a new transaction.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void isolate(Runnable runnable) {
        log.debug("Executing runnable in new transaction");
        runnable.run();
    }
    
    /**
     * Execute supplier without transaction.
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public <T> T withoutTransaction(Supplier<T> supplier) {
        log.debug("Executing supplier without transaction");
        return supplier.get();
    }
    
    /**
     * Execute runnable without transaction.
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void withoutTransaction(Runnable runnable) {
        log.debug("Executing runnable without transaction");
        runnable.run();
    }
}
