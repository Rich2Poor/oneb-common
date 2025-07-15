package com.oneb.common.producer;

import com.oneb.common.kafka.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test to verify that @ConditionalOnProperty annotation is available and working
 */
class KafkaProducerTest {

    @Test
    void testConditionalOnPropertyAnnotationIsAvailable() {
        // This test verifies that the @ConditionalOnProperty annotation is available
        // and can be used in the codebase
        ConditionalOnProperty annotation = KafkaProducer.class.getAnnotation(ConditionalOnProperty.class);
        assertNotNull(annotation, "@ConditionalOnProperty annotation should be present on KafkaProducer class");
        
        // Verify the annotation properties
        assertNotNull(annotation.name(), "name property should not be null");
        assertNotNull(annotation.havingValue(), "havingValue property should not be null");
    }
}
