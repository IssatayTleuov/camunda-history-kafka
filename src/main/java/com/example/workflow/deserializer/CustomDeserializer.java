package com.example.workflow.deserializer;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.Serializable;
import java.util.Map;

public class CustomDeserializer<T extends Serializable> implements Deserializer<T> {

    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        return (bytes == null) ? null : SerializationUtils.deserialize(bytes);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
