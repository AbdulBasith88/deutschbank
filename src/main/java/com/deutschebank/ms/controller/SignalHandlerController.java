package com.deutschebank.ms.controller;

import com.deutschebank.ms.service.SignalHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signal")
public class SignalHandlerController {
    @Autowired
    private SignalHandlerService signalHandler;
    @PostMapping("/{signalId}")
    public void processSignal(@PathVariable int signalId) {
        signalHandler.handleSignal(signalId);
    }
}
