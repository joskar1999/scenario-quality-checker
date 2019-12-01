package com.lecimy.checker.controller;

import com.lecimy.checker.model.*;
import com.lecimy.checker.service.*;
import io.vertx.core.json.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    private final ScenarioCounterContext scenarioCounter;

    private final NoActorStepsFinderService noActorsService;

    private final ScenarioFlattingService flattingService;

    private final PrettyPrintService prettyPrinter;

    public ScenarioController(ScenarioCounterContext scenarioCounter, NoActorStepsFinderService noActorsService,
                              ScenarioFlattingService flattingService, PrettyPrintService prettyPrinter) {
        this.scenarioCounter = scenarioCounter;
        this.noActorsService = noActorsService;
        this.flattingService = flattingService;
        this.prettyPrinter = prettyPrinter;
    }

    @PostMapping("/steps")
    public StepsResponse getStepsAmount(@RequestBody Scenario scenario) {
        scenarioCounter.setStrategy(new ScenarioStepsCounter());
        return new StepsResponse(scenarioCounter.countSteps(scenario.getSteps()));
    }

    @PostMapping("/keywords")
    public KeywordsResponse getKeywordsAmount(@RequestBody Scenario scenario) {
        scenarioCounter.setStrategy(new ScenarioKeywordsCounter());
        return new KeywordsResponse(scenarioCounter.countSteps(scenario.getSteps()));
    }

    @PostMapping("/no-actor")
    public NoActorResponse getStepsWithoutActor(@RequestBody Scenario scenario) {
        return new NoActorResponse(noActorsService.findStepsWithNoActorProvided(scenario));
    }

    @PostMapping("/documentation")
    public DocumentationResponse getPrettyPrintedScenario(@RequestBody Scenario scenario) {
        return new DocumentationResponse(prettyPrinter.prettyPrint(scenario));
    }

    @PostMapping("/{level}")
    public FlattenScenarioResponse getScenariosAtProvidedLevel(
            @RequestBody Scenario scenario,
            @PathVariable Integer level) {
        return new FlattenScenarioResponse(flattingService.flatScenario(scenario, level));
    }
}
