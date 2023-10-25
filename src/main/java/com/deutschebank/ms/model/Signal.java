package com.deutschebank.ms.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Signal {
    private Integer signalId;
    private List<String> ops;

    public Signal(Integer signalId, List<String> ops) {
        this.signalId = signalId;
        this.ops = ops;
    }
}
