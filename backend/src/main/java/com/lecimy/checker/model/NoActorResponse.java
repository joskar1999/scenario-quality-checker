package com.lecimy.checker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoActorResponse {
    List<String> stepsWithoutActors;
}
