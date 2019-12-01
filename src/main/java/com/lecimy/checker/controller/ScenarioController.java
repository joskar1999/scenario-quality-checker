package com.lecimy.checker.controller;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.service.*;
import io.vertx.core.json.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioCounterContext scenarioCounter;

    private final NoActorStepsFinderService noActorsService;

    private final ScenarioFlattingService flattingService;

    public ScenarioController(ScenarioCounterContext scenarioCounter, NoActorStepsFinderService noActorsService,
                              ScenarioFlattingService flattingService) {
        this.scenarioCounter = scenarioCounter;
        this.noActorsService = noActorsService;
        this.flattingService = flattingService;
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
    public ResponseEntity<JsonObject> getScenariosAtProvidedLevel(@RequestBody Scenario scenario,
                                                                  @PathVariable Integer level) {
        return ResponseEntity.ok(
            new JsonObject().put("flattedScenario", flattingService.flatScenario(scenario, level)));
    }
}
