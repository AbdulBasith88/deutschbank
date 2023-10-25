package com.deutschebank.ms.service;

import com.deutschebank.ms.model.Signal;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignalHandlerServiceTest {

    @Autowired
    private SignalHandlerService service;
    @Test
    public void testHandleSignal() {
        Signal s = new Signal(1, Stream.of("performCalc").toList());
        Assertions.assertAll(()-> service.addSignals(s));
    }
}
