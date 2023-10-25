package com.deutschebank.ms.util;

import com.deutschebank.ms.exception.InvalidOperationException;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * SignalUtils provides functionalities to deal with signal such as adding a new signal and
 * listing all the signals.
 */
public class SignalUtils {
    @Getter
    private static Map<Integer, List<String>> sigOpsMap = new HashMap<>();

    public static void addSignal(int signalId, List<String> ops) throws InvalidOperationException {
        // List of permitted operations/tasks
        List<String> allowedOps = Stream.of("doAlgo",
                        "cancelTrades",
                        "reverse",
                        "submitToMarket",
                        "performCalc",
                        "setUp",
                        "setAlgoParam")
                .toList();
        for (String op : ops) {
            // valid operation is setAlgoParam(num,num)
            if (op.contains("setAlgoParam")) {
                if (!op.matches("setAlgoParam\\(\\d+,\\s*\\d+\\)")) {
                    throw new InvalidOperationException();
                }
            } else if (!allowedOps.contains(op)) {
                throw new InvalidOperationException();
            }
        }
        sigOpsMap.put(signalId, ops);
    }
}
