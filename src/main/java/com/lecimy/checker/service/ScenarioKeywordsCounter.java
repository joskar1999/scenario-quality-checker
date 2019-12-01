package com.lecimy.checker.service;

import com.lecimy.checker.model.Keywords;
import com.lecimy.checker.model.Step;

import java.util.Arrays;
import java.util.List;

public class ScenarioKeywordsCounter implements ScenarioCounter {

    /**
     * This method counts number of steps containing keywords
     *
     * @param steps - list of steps provided by user
     * @return - number of all steps containing keywords in provided scenario
     */
    @Override
    public int countSteps(List<Step> steps) {
        int nodes = 0;
        for (Step s : steps) {
            nodes += countSteps(s.getSubSteps());
            if (Arrays.stream(Keywords.values()).anyMatch(
                k -> k.name().equals(s.getDescription().substring(0, s.getDescription().indexOf(' '))))) {
                nodes += 1;
            }
        }
        return nodes;
    }
}
