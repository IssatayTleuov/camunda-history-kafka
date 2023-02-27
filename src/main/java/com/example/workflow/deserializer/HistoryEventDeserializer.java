package com.example.workflow.deserializer;

import com.example.workflow.dto.HistoryEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class HistoryEventDeserializer<T extends Serializable> implements Deserializer<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");

            var historyEventDto = objectMapper.convertValue(bytes, HistoryEventDto.class);
            return historyEventDto.getHistoryEvent();
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to HistoryEventDto");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
