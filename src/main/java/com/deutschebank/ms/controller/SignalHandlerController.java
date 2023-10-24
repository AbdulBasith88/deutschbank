package com.deutschebank.ms.controller;

import com.deutschebank.ms.exception.InvalidOperationException;
import com.deutschebank.ms.model.Signal;
import com.deutschebank.ms.service.SignalHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("signal")
public class SignalHandlerController {
    @Autowired
    private SignalHandlerService signalHandler;

    @PostMapping("/{signalId}")
    public void processSignal(@PathVariable int signalId) {
        signalHandler.handleSignal(signalId);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity.BodyBuilder addSignal(@RequestBody Signal signal) {
        try {
            signalHandler.addSignals(signal);
        } catch (InvalidOperationException ex) {
            return ResponseEntity.badRequest();
        }
        return ResponseEntity.ok();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Integer, List<String>> getSignals(@PathVariable int signalId) {
        return signalHandler.getSignals();
    }
}
