package com.example.workflow.dto;

import org.camunda.bpm.engine.impl.history.event.HistoryEvent;

public class HistoryEventDto<T> {
    private String className;
    private T historyEvent;

    public HistoryEventDto() {}

    public HistoryEventDto(String className, T historyEvent) {
        this.className = className;
        this.historyEvent = historyEvent;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public T getHistoryEvent() {
        return historyEvent;
    }

    public void setHistoryEvent(T historyEvent) {
        this.historyEvent = historyEvent;
    }
}
