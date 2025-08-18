# Migration to oneb-common

## Tóm tắt

Đã thành công di chuyển `PostEventDto` và `PostEventType` từ project community sang module `oneb-common` để có thể tái sử dụng trong các project khác.

## Các thay đổi đã thực hiện

### 1. Trong module oneb-common

#### Tạo mới các class:
- ✅ `com.oneb.common.domain.community.enums.PostEventType`
- ✅ `com.oneb.common.domain.community.enums.PostStatus`
- ✅ `com.oneb.common.domain.community.enums.PostType`
- ✅ `com.oneb.common.domain.community.enums.MediaType`
- ✅ `com.oneb.common.domain.community.model.MediaDTO`
- ✅ `com.oneb.common.domain.community.dto.PostEventDto`

#### Test:
- ✅ `com.oneb.common.domain.community.dto.PostEventDtoTest`

### 2. Trong project community

#### Cập nhật imports:
- ✅ `PostKafkaProducer.java` - Updated import
- ✅ `PostEventMapper.java` - Updated import + custom mapping methods
- ✅ `PostEventMapperTest.java` - Updated imports + assertions
- ✅ `PostKafkaIntegrationTest.java` - Updated imports + Media creation
- ✅ `PostKafkaProducerTest.java` - Updated imports + Media creation

#### Xóa các class cũ:
- ✅ `src/main/java/com/langbat/community/dto/kafka/PostEventDto.java`
- ✅ `src/main/java/com/langbat/community/model/xnum/PostEventType.java`

#### Custom mapping methods:
- ✅ Tạo mapping methods để convert giữa community enums và oneb-common enums
- ✅ Tạo mapping method để convert giữa community Media và oneb-common Media

## Kết quả test

### oneb-common module:
```
Tests run: 21, Failures: 0, Errors: 0, Skipped: 0
```

### community project (Kafka tests):
```
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
- PostKafkaIntegrationTest: 2 tests passed
- PostKafkaProducerTest: 4 tests passed
```

### PostEventMapperTest:
```
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
- toCreateEvent_ShouldMapPostToCreateEvent: ✅
- toDeleteEvent_ShouldMapPostToDeleteEvent: ✅
- toCreateEvent_ShouldHandleNullFields: ✅
```

## Cách sử dụng mới

### Import statements:

```java
// Thay vì:

import com.langbat.community.dto.kafka.PostEventDto;
import com.langbat.community.model.xnum.PostEventType;

// Sử dụng:

```

### Tạo PostEventDto:
```java
PostEventDto event = new PostEventDto();
event.setEventType(PostEventType.CREATE);
event.setPostId(1L);
event.setTitle("New Post");
event.setType(PostType.TEXT);
event.setStatus(PostStatus.PUBLISHED);
// ... other fields
```

## Lưu ý quan trọng

1. **Dependency version**: Project community đã sử dụng `oneb-common:1.0.8-SNAPSHOT`
2. **Enum mapping**: PostEventMapper có custom methods để convert giữa community enums và oneb-common enums
3. **Media mapping**: Custom mapping method để convert từ community Media (có type là enum) sang oneb-common Media (có type là enum và thêm các field khác)
4. **Backward compatibility**: Các API endpoints và Kafka messages vẫn hoạt động như cũ

## Các project khác có thể sử dụng

Bây giờ các project khác có thể:
1. Add dependency `oneb-common:1.0.8-SNAPSHOT`
2. Import và sử dụng `PostEventDto`, `PostEventType` và các class liên quan
3. Tạo Kafka consumers để lắng nghe post events từ community service
