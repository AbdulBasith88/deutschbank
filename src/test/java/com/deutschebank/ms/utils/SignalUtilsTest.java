package com.deutschebank.ms.utils;

import com.deutschebank.ms.exception.DuplicateSignalException;
import com.deutschebank.ms.exception.InvalidOperationException;
import com.deutschebank.ms.util.SignalUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignalUtilsTest {
    @Mock
    private SignalUtils utils;

    @Before
    public void setup() {
        Map<Integer, List<String>> signals = new HashMap<>();
        ReflectionTestUtils.setField(utils, "sigOpsMap", signals);
    }

    @Test
    public void testAddSignalToAddExistingSignal() {
        Map<Integer, List<String>> signals = new HashMap<>();
        signals.put(1, Stream.of("abc").toList());
        ReflectionTestUtils.setField(utils, "sigOpsMap", signals);
        Assertions.assertThrows(DuplicateSignalException.class, () -> {
            SignalUtils.addSignal(1, Stream.of("xyz").toList());
        });
    }

    @Test
    public void testAddSignalInvalidOperation() {
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            SignalUtils.addSignal(1, Stream.of("xyz").toList());
        });
    }

    @Test
    public void testAddSignal() throws DuplicateSignalException, InvalidOperationException {
        SignalUtils.addSignal(1, Stream.of("doAlgo").toList());
        Assert.assertEquals("The number of signal added is incorrect",
                1, SignalUtils.getSigOpsMap().size());
    }

    @Test
    public void testAddSignalValidSetAlgoParamOp() throws DuplicateSignalException, InvalidOperationException {
        SignalUtils.addSignal(1, Stream.of("setAlgoParam(23,100)").toList());
        Assert.assertEquals("The number of signal added is incorrect",
                1, SignalUtils.getSigOpsMap().size());
    }
    @Test
    public void testAddSignalInvalidSetAlgoParamOp() {
        Assertions.assertThrows(InvalidOperationException.class, () -> {
            SignalUtils.addSignal(1, Stream.of("setAlgoParam()").toList());
        });
    }
}
