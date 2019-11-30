package com.lecimy.checker.controller;

import io.vertx.core.json.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scenario")
public class ScenarioController {

    @PostMapping("/steps")
    public ResponseEntity<JsonObject> getStepsAmount() {
        return ResponseEntity.ok(new JsonObject().put("key", "value"));
    }

    @PostMapping("keywords")
    public ResponseEntity<JsonObject> getKeywordsAmount() {
        return ResponseEntity.ok(new JsonObject().put("key", "value"));
    }

    @PostMapping("/noActor")
    public ResponseEntity<JsonObject> getStepsWithoutActor() {
        return ResponseEntity.ok(new JsonObject().put("key", "value"));
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
