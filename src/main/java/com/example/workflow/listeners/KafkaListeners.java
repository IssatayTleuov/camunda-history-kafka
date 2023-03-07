package com.example.workflow.listeners;

import com.example.workflow.handler.CamundaHistoryEventHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {
    private final CamundaHistoryEventHandler eventHandler;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public KafkaListeners(CamundaHistoryEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @KafkaListener(topics = "camunda", groupId = "history-camunda")
    void listener(HistoryEvent historyEvent) {
        eventHandler.handleEvent(historyEvent);
    }
}
