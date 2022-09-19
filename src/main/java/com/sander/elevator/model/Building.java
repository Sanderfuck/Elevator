package com.sander.elevator.model;

import com.sander.elevator.service.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors = new ArrayList<>();
    private Elevator elevator;
    public final static int MAX_FLOOR_NUMBER_IN_BUILDING = generateMaxFloorNumberInBuilding();

    public Building() {
        generateFloors();
        print();
        elevator = new Elevator(this);
    }

    private static int generateMaxFloorNumberInBuilding() {
        return (int) (Math.random() * 15) + 5;
    }

    private void generateFloors() {
        for (int i = 1; i <= MAX_FLOOR_NUMBER_IN_BUILDING; i++) {
            floors.add(new Floor(i));
        }
    }

    public Floor getFloor(int floorNumber) {
        return floors.get(floorNumber - 1);
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void print() {
        System.out.println();
        System.out.println("\t\t\tCreate Building with " + MAX_FLOOR_NUMBER_IN_BUILDING + " floors\t\t\t");
        System.out.println();
    }
}
