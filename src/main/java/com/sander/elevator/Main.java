package com.sander.elevator;

import com.sander.elevator.model.Building;

public class Main {
    private static final int numOfIterations = 10;

    public static void main(String[] args) {
        Building building = new Building();
        for (int i = 0; i < numOfIterations; i++) {
            building.getElevator().move();
        }
    }
}
