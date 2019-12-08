package com.lecimy.checker;

import com.lecimy.checker.model.Step;
import com.lecimy.checker.service.ScenarioKeywordsCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScenarioKeywordsCounterTest {

    private static ScenarioKeywordsCounter counter;

    @BeforeAll
    static void init() {
        counter = new ScenarioKeywordsCounter();
    }

    @Test
    void shouldReturn0WhenEmptyArrayPassed() {
        List<Step> steps = new ArrayList<>();
        assertEquals(0, counter.countSteps(steps));
    }

    @Test
    void shouldCountSteps() {
        List<Step> steps = new ArrayList<>();
        Step step = new Step();
        step.setDescription("IF");
        steps.add(step);
        assertEquals(1, counter.countSteps(steps));
    }

    @Test
    void shouldCountStepsWith1LevelSubStepsNoKeywords() {
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> subSteps = new ArrayList<>();
        Step step = new Step();
        Step step2 = new Step();
        step.setDescription("desc");
        step2.setDescription("desc");
        subSteps.add(step2);
        step.setSubSteps(subSteps);
        steps.add(step);
        assertEquals(0, counter.countSteps(steps));
    }

    @Test
    void shouldCountStepsWith1LevelSubStepsWithKeywords() {
        List<Step> steps = new ArrayList<>();
        ArrayList<Step> subSteps = new ArrayList<>();
        Step step = new Step();
        Step step2 = new Step();
        step.setDescription("desc");
        step2.setDescription("FOR_EACH");
        subSteps.add(step2);
        step.setSubSteps(subSteps);
        steps.add(step);
        assertEquals(1, counter.countSteps(steps));
    }
}
