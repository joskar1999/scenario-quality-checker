package com.lecimy.checker;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.service.ScenarioFlattingService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class ScenarioFlattingServiceTest {

    @Test
    void shouldVerifyEmptyData() {
        ScenarioFlattingService service = Mockito.mock(ScenarioFlattingService.class);
        service.flatScenario(new Scenario(), 1);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).flatScenario(new Scenario(), 1);
    }

    @Test
    void shouldVerifyNonEmptyData() {
        ScenarioFlattingService service = Mockito.mock(ScenarioFlattingService.class);
        Scenario scenario = new Scenario();
        scenario.setTitle("scenario");
        service.flatScenario(scenario, 1);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).flatScenario(scenario, 1);
    }
}
