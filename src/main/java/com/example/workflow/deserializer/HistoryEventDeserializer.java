package com.example.workflow.deserializer;

import com.example.workflow.dto.HistoryEventDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class HistoryEventDeserializer<T extends Serializable> implements Deserializer<HistoryEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public HistoryEvent deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            System.out.println("Deserializing...");

            HistoryEventDto historyEventDto = objectMapper
                    .reader()
                    .readValue(new String(bytes, StandardCharsets.UTF_8), HistoryEventDto.class);
            var clazz = Class.forName(historyEventDto.getClassName());
            JsonNode node =  objectMapper.readTree(bytes);
            return (HistoryEvent) objectMapper.treeToValue(node.get("historyEvent"), clazz);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to HistoryEventDto");
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
