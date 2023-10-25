package com.deutschebank.ms.service;

import com.deutschebank.ms.algo.Algo;
import com.deutschebank.ms.exception.DuplicateSignalException;
import com.deutschebank.ms.exception.InvalidOperationException;
import com.deutschebank.ms.model.Signal;
import com.deutschebank.ms.util.SignalUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SignalHandlerService implements SignalHandler {
    public void handleSignal(int signal) {
        performSignalOperations(signal);
    }

    /**
     * Calls the Algo to execute the operations for the signal
     * @param signalId The ID of the signal
     */
    private void performSignalOperations(int signalId) {
        Algo algo = new Algo();
        Map<Integer, List<String>> signals = SignalUtils.getSigOpsMap();
        // If the signal is not created then execute the cancelTrade() as specified in the example
        if (!signals.containsKey(signalId)) {
            algo.cancelTrades();
        } else {
            List<String> operations = SignalUtils.getSigOpsMap().get(signalId);
            for (String op : operations) {
                switch (op) {
                    case "doAlgo":
                        algo.doAlgo();
                        break;
                    case "cancelTrades":
                        algo.cancelTrades();
                        break;
                    case "reverse":
                        algo.reverse();
                        break;
                    case "submitToMarket":
                        algo.submitToMarket();
                        break;
                    case "performCalc":
                        algo.performCalc();
                        break;
                    case "setUp":
                        algo.setUp();
                        break;
                    default:
                        if (op.contains("setAlgoParam")) {
                            String[] s = op.substring(op.indexOf("(") + 1, op.indexOf(")")).split(",");
                            algo.setAlgoParam(Integer.parseInt(s[0].trim()), Integer.parseInt(s[1].trim()));
                        }
                }
            }
        }
        algo.doAlgo(); // It runs at the end of all operations as specified in the example
    }

    public void addSignals(Signal signal) throws InvalidOperationException, DuplicateSignalException {
        SignalUtils.addSignal(signal.getSignalId(), signal.getOps());
    }

    public Map<Integer, List<String>> getSignals() {
        return SignalUtils.getSigOpsMap();
    }
}
