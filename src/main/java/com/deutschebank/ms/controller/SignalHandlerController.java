package com.deutschebank.ms.controller;

import com.deutschebank.ms.exception.InvalidOperationException;
import com.deutschebank.ms.model.Signal;
import com.deutschebank.ms.service.SignalHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/signal")
public class SignalHandlerController {
    @Autowired
    private SignalHandlerService signalHandler;

    @GetMapping("/{signalId}")
    public void processSignal(@PathVariable int signalId) {
        signalHandler.handleSignal(signalId);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSignal(@RequestBody Signal signal) {
        try {
            signalHandler.addSignals(signal);
        } catch (InvalidOperationException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Signal> getSignals() {
        List<Signal> signalList = new ArrayList<>();
        for (Map.Entry<Integer, List<String>> e : signalHandler.getSignals().entrySet()) {
            signalList.add(new Signal(e.getKey(), e.getValue()));
        }
        return signalList;
    }
}
