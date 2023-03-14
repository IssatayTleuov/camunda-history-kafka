package com.example.workflow.listeners;

import com.example.workflow.ProcessHistoryEventCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaListeners {

    @KafkaListener(topics = "camunda", groupId = "history-camunda")
    void listener(HistoryEvent historyEvent) {
        DbHistoryEventHandler historyEventHandler = new DbHistoryEventHandler();
        ProcessHistoryEventCommand command = new ProcessHistoryEventCommand(historyEvent, historyEventHandler);
        CommandExecutorImpl executor = new CommandExecutorImpl();
        executor.execute(command);
    }
}

