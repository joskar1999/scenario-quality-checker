package com.lecimy.checker.service;

import com.lecimy.checker.model.Keywords;
import com.lecimy.checker.model.Step;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
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
        if (steps == null) return 0;
        for (Step s : steps) {
            nodes += countSteps(s.getSubSteps());
            String[] firstWords = s.getDescription().split(" ");
            if (Arrays.stream(Keywords.values()).anyMatch(k -> k.name().equals(firstWords[0]))) {
                nodes += 1;
            }
        }
        return nodes;
    }
}
