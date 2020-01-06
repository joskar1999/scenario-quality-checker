package com.lecimy.checker;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.service.PrettyPrintService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class PrettyPrintServiceTest {

    @Test
    void shouldVerifyEmptyScenario() {
        PrettyPrintService service = Mockito.mock(PrettyPrintService.class);
        service.prettyPrint(new Scenario());
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).prettyPrint(new Scenario());
    }

    @Test
    void shouldVerifyNonEmptyScenario() {
        PrettyPrintService service = Mockito.mock(PrettyPrintService.class);
        Scenario scenario = new Scenario();
        scenario.setTitle("scenario");
        service.prettyPrint(scenario);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).prettyPrint(scenario);
    }
}
