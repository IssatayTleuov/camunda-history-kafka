package com.example.workflow.config;

import com.example.workflow.dto.HistoryEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;


@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfig<T> {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, HistoryEventDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<
                ConcurrentMessageListenerContainer<String, HistoryEventDto>> factory(
                        ConsumerFactory<String, HistoryEventDto> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, HistoryEventDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
