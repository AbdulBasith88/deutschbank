package com.deutschebank.ms.util;

import com.deutschebank.ms.exception.InvalidOperationException;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SignalUtils {
    @Getter
    private static Map<Integer, List<String>> sigOpsMap = new HashMap<>();

    public static void addSignal(int signalId, List<String> ops) throws InvalidOperationException {
        List<String> allowedOps = Stream.of("doAlgo",
                        "cancelTrades",
                        "reverse",
                        "submitToMarket",
                        "performCalc",
                        "setUp",
                        "setAlgoParam")
                .toList();
        for (String op : ops) {
            if (!allowedOps.contains(op) || !op.matches("setAlgoParam(\\d,\\d)"))
                throw new InvalidOperationException();
        }
        sigOpsMap.put(signalId, ops);
    }
}
