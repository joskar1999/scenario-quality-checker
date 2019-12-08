package com.lecimy.checker.controller;

import com.lecimy.checker.model.*;
import com.lecimy.checker.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
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
        log.info("POST request to /steps endpoint with scenario: {}", scenario);
        scenarioCounter.setStrategy(new ScenarioStepsCounter());
        log.debug("ScenarioStepsCounter strategy set");
        return new StepsResponse(scenarioCounter.countSteps(scenario.getSteps()));
    }

    @PostMapping("/keywords")
    public KeywordsResponse getKeywordsAmount(@RequestBody Scenario scenario) {
        log.info("POST request to /keywords endpoint with scenario: {}", scenario);
        scenarioCounter.setStrategy(new ScenarioKeywordsCounter());
        log.debug("ScenarioKeywordsCounter strategy set");
        return new KeywordsResponse(scenarioCounter.countSteps(scenario.getSteps()));
    }

    @PostMapping("/no-actor")
    public NoActorResponse getStepsWithoutActor(@RequestBody Scenario scenario) {
        log.info("POST request to /no-actor endpoint with scenario: {}", scenario);
        return new NoActorResponse(noActorsService.findStepsWithNoActorProvided(scenario));
    }

    @PostMapping("/documentation")
    public DocumentationResponse getPrettyPrintedScenario(@RequestBody Scenario scenario) {
        log.info("POST request to /documentation endpoint with scenario: {}", scenario);
        return new DocumentationResponse(prettyPrinter.prettyPrint(scenario));
    }

    @PostMapping("/{level}")
    public FlattenScenarioResponse getScenariosAtProvidedLevel(@RequestBody Scenario scenario,
                                                               @PathVariable Integer level) {
        log.info("POST request to /level endpoint with level: {}, scenario: {}", level, scenario);
        return new FlattenScenarioResponse(flattingService.flatScenario(scenario, level));
    }
}
