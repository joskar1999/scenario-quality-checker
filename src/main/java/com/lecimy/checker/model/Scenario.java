package com.lecimy.checker.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Scenario {

    private String title;
    private ArrayList<String> actors;
    private ArrayList<String> systemActors;
    private ArrayList<Step> steps;
}
