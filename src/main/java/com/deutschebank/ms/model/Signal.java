package com.deutschebank.ms.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Signal {
    private Integer signalId;
    private List<String> ops;

    public Signal(Integer signalId, List<String> ops) {
        this.signalId = signalId;
        this.ops = ops;
    }
}
