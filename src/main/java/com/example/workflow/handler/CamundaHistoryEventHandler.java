package com.example.workflow.handler;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.HistoryEventHandler;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CamundaHistoryEventHandler implements HistoryEventHandler {
    @Override
    public void handleEvent(HistoryEvent historyEvent) {

    }

    @Override
    public void handleEvents(List<HistoryEvent> list) {

    }
}
