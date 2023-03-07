package com.example.workflow;

import camundajar.impl.scala.Unit;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import java.io.Serializable;

public class ProcessHistoryEventCommand implements Command<Unit>, Serializable {
    @Override
    public Unit execute(CommandContext commandContext) {
        return null;
    }
}
