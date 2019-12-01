package com.lecimy.checker.service;

import com.lecimy.checker.model.Keywords;
import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.model.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NoActorStepsFinderService {

    public List<String> findStepsWithNoActorProvided(Scenario scenario) {
        ArrayList<String> actors = (ArrayList<String>) Stream.concat(
            scenario.getActors().stream(), scenario.getSystemActors().stream())
            .collect(Collectors.toList());
        return findStepsWithNoActorProvided(scenario.getSteps(), actors);
    }

    private List<String> findStepsWithNoActorProvided(List<Step> steps, List<String> actors) {
        ArrayList<String> noActorSteps = new ArrayList<>();
        steps.forEach(step -> {
            String[] firstWords = step.getDescription().split(" ");
            if (Stream.of(Keywords.values()).anyMatch(k -> k.name().equals(firstWords[0]))) {
                checkIfActorNotInStep(actors, firstWords[1], step, noActorSteps);
            } else {
                checkIfActorNotInStep(actors, firstWords[0], step, noActorSteps);
            }
            noActorSteps.addAll(findStepsWithNoActorProvided(step.getSubSteps(), actors));
        });
        return noActorSteps;
    }

    private void checkIfActorNotInStep(List<String> actors, String potential, Step step, List<String> resultSet) {
        actors.stream()
            .filter(a -> a.equals(potential))
            .findFirst()
            .ifPresentOrElse(a -> {
            }, () -> resultSet.add(step.getDescription()));
    }
}
