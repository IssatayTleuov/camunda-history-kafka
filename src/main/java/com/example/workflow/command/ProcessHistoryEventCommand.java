package com.example.workflow.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class ProcessHistoryEventCommand implements Command<Object>, Serializable {
    private HistoryEvent historyEvent;
    private DbHistoryEventHandler eventHandler;

    @Override
    public Object execute(CommandContext commandContext) {
        log.info("Command: " + historyEvent.toString() + "\n");
        eventHandler.handleEvent(historyEvent);
        return null;
    }

    public void execute() {
        log.info("Command: " + historyEvent.toString() + "\n");
        eventHandler.handleEvent(historyEvent);
    }
}

