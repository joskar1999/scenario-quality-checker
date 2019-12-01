package com.lecimy.checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scenario {

    public Scenario(Scenario other) {
        this.title = other.title;
        this.actors = other.actors;
        this.systemActors = other.systemActors;
        this.steps = other.steps;
    }

    private String title;
    private ArrayList<String> actors;
    private ArrayList<String> systemActors;
    private ArrayList<Step> steps;
}
