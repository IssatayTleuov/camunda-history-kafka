package com.example.workflow.listeners;

import com.example.workflow.ProcessHistoryEventCommand;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.CommandExecutorImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    @KafkaListener(topics = "#{'${spring.kafka.template.default-topic}'}", groupId = "history-camunda")
    void listener(HistoryEvent historyEvent) {
        log.info(historyEvent.toString() + "\n");
    }
}

