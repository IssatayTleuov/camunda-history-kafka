package com.example.workflow;

import camundajar.impl.sourcecode.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class ProcessHistoryEventCommand implements Command<Util>, Serializable {
    private HistoryEvent historyEvent;
    private DbHistoryEventHandler historyEventHandler;

    @Override
    public Util execute(CommandContext commandContext) {
        historyEventHandler.handleEvent(historyEvent);
        return null;
    }
}

