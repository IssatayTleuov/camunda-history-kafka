package com.example.workflow;

import com.example.workflow.dto.HistoryEventDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoricProcessInstanceEventEntity;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "camunda", groupId = "history-camunda")
    void listener(HistoryEvent historyEvent) throws JsonProcessingException {
        log.info("History data received");
        log.info("Object as json: " + objectMapper.writeValueAsString(historyEvent));
        log.info("End of listener");
    }
}
