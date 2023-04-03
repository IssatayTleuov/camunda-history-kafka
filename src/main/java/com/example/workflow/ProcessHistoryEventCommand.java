package com.example.workflow;

import camundajar.impl.sourcecode.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.impl.history.event.HistoryEvent;
import org.camunda.bpm.engine.impl.history.handler.DbHistoryEventHandler;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
@Service
@NoArgsConstructor
@AllArgsConstructor
public class ProcessHistoryEventCommand implements Command<Object>, Serializable {
    private HistoryEvent historyEvent;
    private DbHistoryEventHandler historyEventHandler;

    @Override
    public Object execute(CommandContext commandContext) {
        historyEventHandler.handleEvent(historyEvent);
        return new Object();
    }
}

