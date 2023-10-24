package com.deutschebank.ms.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Signal {
    private Integer signalId;
    private List<String> ops;
}
