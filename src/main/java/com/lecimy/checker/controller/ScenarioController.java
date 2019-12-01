package com.lecimy.checker.controller;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.service.NoActorStepsFinderService;
import com.lecimy.checker.service.ScenarioCounterContext;
import com.lecimy.checker.service.ScenarioKeywordsCounter;
import com.lecimy.checker.service.ScenarioStepsCounter;
import io.vertx.core.json.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioCounterContext scenarioCounter;

    private final NoActorStepsFinderService noActorsService;

    public ScenarioController(ScenarioCounterContext scenarioCounter, NoActorStepsFinderService noActorsService) {
        this.scenarioCounter = scenarioCounter;
        this.noActorsService = noActorsService;
    }

    @PostMapping("/steps")
    public ResponseEntity<JsonObject> getStepsAmount(@RequestBody Scenario scenario) {
        scenarioCounter.setStrategy(new ScenarioStepsCounter());
        return ResponseEntity.ok(new JsonObject().put("steps", scenarioCounter.countSteps(scenario.getSteps())));
    }

    @PostMapping("keywords")
    public ResponseEntity<JsonObject> getKeywordsAmount(@RequestBody Scenario scenario) {
        scenarioCounter.setStrategy(new ScenarioKeywordsCounter());
        return ResponseEntity.ok(new JsonObject().put("keywords", scenarioCounter.countSteps(scenario.getSteps())));
    }

    @PostMapping("/noActor")
    public ResponseEntity<JsonObject> getStepsWithoutActor(@RequestBody Scenario scenario) {
        return ResponseEntity.ok(
            new JsonObject().put("stepsWithoutActors", noActorsService.findStepsWithNoActorProvided(scenario)));
    }

    @PostMapping("/documentation")
    public ResponseEntity<JsonObject> getPrettyPrintedScenario() {
        return ResponseEntity.ok(new JsonObject().put("key", "value"));
    }

    @PostMapping("/{level}")
    public ResponseEntity<JsonObject> getScenariosAtProvidedLevel(@PathVariable Integer level) {
        return ResponseEntity.ok(new JsonObject().put("key", "value"));
    }
}
