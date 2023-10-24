package com.deutschebank.ms.service;

import com.deutschebank.ms.algo.Algo;
import com.deutschebank.ms.exception.InvalidOperationException;
import com.deutschebank.ms.model.Signal;
import com.deutschebank.ms.util.SignalUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class SignalHandlerService implements SignalHandler {
    public void handleSignal(int signal) {
        Algo algo = new Algo();

        switch (signal) {
            case 1:
                algo.setUp();
                algo.setAlgoParam(1, 60);
                algo.performCalc();
                algo.submitToMarket();
                break;

            case 2:
                algo.reverse();
                algo.setAlgoParam(1, 80);
                algo.submitToMarket();
                break;

            case 3:
                algo.setAlgoParam(1, 90);
                algo.setAlgoParam(2, 15);
                algo.performCalc();
                algo.submitToMarket();
                break;

            default:
                algo.cancelTrades();
                break;
        }

        algo.doAlgo();
    }

    public void addSignals(Signal signal) throws InvalidOperationException {
        SignalUtils.addSignal(signal.getSignalId(), signal.getOps());
    }

    public Map<Integer, List<String>> getSignals() {
        return SignalUtils.getSigOpsMap();
    }
}
