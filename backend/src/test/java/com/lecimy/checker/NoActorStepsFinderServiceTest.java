package com.lecimy.checker;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.model.Step;
import com.lecimy.checker.service.NoActorStepsFinderService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoActorStepsFinderServiceTest {

    @Test
    void shouldVerifyEmptyData() {
        NoActorStepsFinderService service = Mockito.mock(NoActorStepsFinderService.class);
        service.findStepsWithNoActorProvided(new Scenario());
        InOrder inOrder = Mockito.inOrder(service);
        inOrder.verify(service).findStepsWithNoActorProvided(new Scenario());
    }

    @Test
    void shouldCountHowManyStepsHaveNoActor() {
        NoActorStepsFinderService service = new NoActorStepsFinderService();
        Scenario scenario = new Scenario();
        scenario.setTitle("scenario");
        ArrayList<String> actors  = new ArrayList<>();
        actors.add("actor");
        scenario.setActors(actors);
        scenario.setSystemActors(actors);
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> subSteps = new ArrayList<>();
        Step step = new Step();
        Step step2 = new Step();
        step.setDescription("desc");
        step2.setDescription("desc");
        subSteps.add(step2);
        step.setSubSteps(subSteps);
        steps.add(step);
        scenario.setSteps((ArrayList<Step>) steps);
        assertEquals(2, service.findStepsWithNoActorProvided(scenario).size());
    }

    @Test
    void shouldReturn0WhenAllStepsHaveActor() {
        NoActorStepsFinderService service = new NoActorStepsFinderService();
        Scenario scenario = new Scenario();
        scenario.setTitle("scenario");
        ArrayList<String> actors  = new ArrayList<>();
        actors.add("actor");
        scenario.setActors(actors);
        scenario.setSystemActors(actors);
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> subSteps = new ArrayList<>();
        Step step = new Step();
        Step step2 = new Step();
        step.setDescription("actor desc");
        step2.setDescription("actor desc");
        subSteps.add(step2);
        step.setSubSteps(subSteps);
        steps.add(step);
        scenario.setSteps((ArrayList<Step>) steps);
        assertEquals(0, service.findStepsWithNoActorProvided(scenario).size());
    }
}
