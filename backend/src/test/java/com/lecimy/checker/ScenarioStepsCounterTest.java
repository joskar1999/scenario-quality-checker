package com.lecimy.checker;

import com.lecimy.checker.model.Step;
import com.lecimy.checker.service.ScenarioStepsCounter;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScenarioStepsCounterTest {

    @Test
    void shouldReturn0WhenEmptyArrayPassed() {
        ScenarioStepsCounter counter = new ScenarioStepsCounter();
        List<Step> steps = new ArrayList<>();
        assertEquals(0, counter.countSteps(steps));
    }

    @Test
    void shouldCountSteps() {
        ScenarioStepsCounter counter = new ScenarioStepsCounter();
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        step.setDescription("desc");
        steps.add(step);
        assertEquals(1, counter.countSteps(steps));
    }

    @Test
    void shouldCountStepsWith1LevelSubStepsNoKeywords() {
        ScenarioStepsCounter counter = new ScenarioStepsCounter();
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> subSteps = new ArrayList<>();
        Step step = new Step();
        Step step2 = new Step();
        step.setDescription("desc");
        step2.setDescription("desc");
        subSteps.add(step2);
        step.setSubSteps(subSteps);
        steps.add(step);
        assertEquals(2, counter.countSteps(steps));
    }

    @Test
    void shouldVerifyEmptyList() {
        ScenarioStepsCounter service = Mockito.mock(ScenarioStepsCounter.class);
        service.countSteps(null);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).countSteps(null);
    }

    @Test
    void shouldVerifyNonNullList() {
        ScenarioStepsCounter service = Mockito.mock(ScenarioStepsCounter.class);
        ArrayList<Step> steps = new ArrayList<>();
        service.countSteps(steps);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).countSteps(steps);
    }

    @Test
    void shouldVerifyNonEmptyList() {
        ScenarioStepsCounter service = Mockito.mock(ScenarioStepsCounter.class);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(new Step("step", null));
        service.countSteps(steps);
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).countSteps(steps);
    }
}
