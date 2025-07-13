# OneB Common Library

A comprehensive Java library containing common utilities and components for OneB projects.

## Features

- **String Utilities**: Comprehensive string manipulation and validation methods
- **Date Utilities**: Date/time formatting, parsing, and calculation utilities
- **Collection Utilities**: Enhanced collection operations and transformations
- **JSON Utilities**: Flexible JSON processing interface (bring your own implementation)
- **Exception Handling**: Structured exception hierarchy for better error handling
- **Spring Integration**: Optional Spring components for transaction and async execution

## Installation

### Maven

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>oneb.com</groupId>
    <artifactId>oneb-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

### GitHub Packages

To use this library from GitHub Packages, add the following repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/tuyen/oneb-common</url>
    </repository>
</repositories>
```

And configure authentication in your `~/.m2/settings.xml`:

```xml
<servers>
    <server>
        <id>github</id>
        <username>YOUR_GITHUB_USERNAME</username>
        <password>YOUR_GITHUB_TOKEN</password>
    </server>
</servers>
```

## Usage

### String Utilities

```java
import oneb.com.common.util.StringUtils;

// Basic string operations
boolean isEmpty = StringUtils.isEmpty(str);
boolean isBlank = StringUtils.isBlank(str);
String defaultValue = StringUtils.defaultIfEmpty(str, "default");

// String transformations
String camelCase = StringUtils.toCamelCase("test_string");
String snakeCase = StringUtils.toSnakeCase("testString");
String capitalized = StringUtils.capitalize("test");

// Validation
boolean isEmail = StringUtils.isValidEmail("test@example.com");
boolean isPhone = StringUtils.isValidPhone("+1234567890");
boolean isNumeric = StringUtils.isNumeric("12345");

// String manipulation
String truncated = StringUtils.truncate("long string", 10);
String reversed = StringUtils.reverse("test");
int count = StringUtils.countOccurrences("test test", "test");
```

### Date Utilities

```java
import oneb.com.common.util.DateUtils;
import java.time.*;

// Current date/time
Instant now = DateUtils.now();
LocalDate today = DateUtils.today();
LocalDateTime nowDateTime = DateUtils.nowDateTime();

// Formatting
String dateStr = DateUtils.formatDate(LocalDate.now());
String dateTimeStr = DateUtils.formatDateTime(LocalDateTime.now());
String isoStr = DateUtils.formatToIso(LocalDateTime.now());

// Parsing
LocalDate date = DateUtils.parseDate("2023-12-25");
LocalDateTime dateTime = DateUtils.parseDateTime("2023-12-25 10:30:00");

// Calculations
long daysBetween = DateUtils.daysBetween(startDate, endDate);
long hoursBetween = DateUtils.hoursBetween(startDateTime, endDateTime);
int age = DateUtils.getAge(birthDate);

// Date checks
boolean isToday = DateUtils.isToday(someDate);
boolean isLeapYear = DateUtils.isLeapYear(2024);
```

### Collection Utilities

```java
import oneb.com.common.util.CollectionUtils;
import java.util.*;

// Basic operations
boolean isEmpty = CollectionUtils.isEmpty(collection);
int size = CollectionUtils.size(collection);
String first = CollectionUtils.first(list);
String last = CollectionUtils.last(list);

// Creating collections
List<String> list = CollectionUtils.listOf("a", "b", "c");
Set<String> set = CollectionUtils.setOf("a", "b", "c");
Map<String, String> map = CollectionUtils.mapOf("key", "value");

// Functional operations
List<String> filtered = CollectionUtils.filter(list, s -> s.startsWith("a"));
List<Integer> mapped = CollectionUtils.map(list, String::length);
Optional<String> found = CollectionUtils.findFirst(list, s -> s.contains("test"));

// Set operations
Set<String> intersection = CollectionUtils.intersection(set1, set2);
Set<String> union = CollectionUtils.union(set1, set2);
Set<String> difference = CollectionUtils.difference(set1, set2);

// Grouping and transformations
Map<Integer, List<String>> grouped = CollectionUtils.groupBy(list, String::length);
List<List<String>> partitions = CollectionUtils.partition(list, 3);
```

### JSON Utilities

First, configure a JSON processor (example with Jackson):

```java
import oneb.com.common.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

// Configure JSON processor (do this once at application startup)
JsonUtils.setProcessor(new JacksonJsonProcessor(new ObjectMapper()));

// Then use JSON utilities
String json = JsonUtils.toJson(object);
MyClass obj = JsonUtils.fromJson(json, MyClass.class);
List<MyClass> list = JsonUtils.fromJsonList(json, MyClass.class);
Map<String, Object> map = JsonUtils.fromJsonMap(json);
boolean isValid = JsonUtils.isValidJson(json);
```

### Exception Handling

```java
import oneb.com.common.exception.*;

// Business exceptions
throw new BusinessException("INVALID_OPERATION", "Operation not allowed");

// Validation exceptions
Map<String, String> errors = new HashMap<>();
errors.put("email", "Invalid email format");
throw new ValidationException("Validation failed", errors);

// Custom exceptions
public class CustomException extends BaseException {
    public CustomException(String message) {
        super("CUSTOM_ERROR", message);
    }
}
```

### Spring Integration (Optional)

If you're using Spring, you can use the provided Spring components:

```java
import oneb.com.common.spring.*;

@Service
public class MyService {
    
    @Autowired
    private TransactionHelper transactionHelper;
    
    @Autowired
    private AsyncExecutor asyncExecutor;
    
    public void doSomething() {
        // Execute in new transaction
        String result = transactionHelper.isolate(() -> {
            // Your transactional code here
            return "result";
        });
        
        // Execute asynchronously
        asyncExecutor.runAsync(() -> {
            // Your async code here
        });
        
        // Execute with locking
        asyncExecutor.runAsync(() -> {
            // Your code here
        }, "lockKey");
    }
}
```

## Dependencies

### Required
- Java 21+
- SLF4J API

### Optional
- Spring Context (for Spring components)
- Spring Transaction (for TransactionHelper)
- Lombok (for reduced boilerplate)

## Building

```bash
mvn clean compile
```

## Testing

```bash
mvn test
```

## Publishing

To publish to GitHub Packages:

```bash
mvn clean deploy
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run tests to ensure everything passes
6. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Changelog

### 1.0.0
- Initial release
- String utilities
- Date utilities
- Collection utilities
- JSON utilities interface
- Exception handling framework
- Spring integration components
