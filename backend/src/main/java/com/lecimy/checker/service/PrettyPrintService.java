package com.lecimy.checker.service;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.model.Step;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrettyPrintService {

    /**
     * This method produces pretty printed scenario
     *
     * @param scenario - scenario provided by user
     * @return - list containing enumerated steps
     */
    public List<String> prettyPrint(Scenario scenario) {
        return prettyPrint(scenario.getSteps(), "");
    }

    private List<String> prettyPrint(List<Step> steps, String prefix) {
        ArrayList<String> enumeratedSteps = new ArrayList<>();
        String enumeration;
        for (int i = 1; i <= steps.size(); i++) {
            if (!"".equals(prefix)) {
                enumeration = prefix + "." + i;
            } else {
                enumeration = prefix + i;
            }
            enumeratedSteps.add(enumeration + " " + steps.get(i - 1).getDescription());
            enumeratedSteps.addAll(prettyPrint(steps.get(i - 1).getSubSteps(), enumeration));
        }
        return enumeratedSteps;
    }
}
