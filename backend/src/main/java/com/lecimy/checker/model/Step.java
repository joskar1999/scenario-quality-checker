package com.lecimy.checker.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Step {

    private String description;
    private ArrayList<Step> subSteps;
}
