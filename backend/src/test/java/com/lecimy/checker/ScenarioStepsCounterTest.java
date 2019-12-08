package com.lecimy.checker;

import com.lecimy.checker.service.ScenarioStepsCounter;
import org.junit.jupiter.api.BeforeAll;

public class ScenarioStepsCounterTest {

    private ScenarioStepsCounter counter;

    @BeforeAll
    void init() {
        counter = new ScenarioStepsCounter();
    }
}
