package com.sander.elevator.model;

import com.sander.elevator.service.Elevator;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private List<Floor> floors = new ArrayList<>();
    private Elevator elevator;
    public final static int MAX_FLOOR_NUMBER_IN_BUILDING = generateMaxFloorNumberInBuilding();
    private int notTransportedPassengers;

    public Building() {
        generateFloors();
        print();
        elevator = new Elevator(this);
        notTransportedPassengers = getQuantityPassengersInBuilding();
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

    public boolean isNotTransportedPassengers() {
        return getQuantityPassengersInBuilding() > 0;
    }

    private int getQuantityPassengersInBuilding() {
        int sum = 0;

        for (Floor floor : floors) {
            sum += floor.getPassengers().size();
        }

        return sum;
    }

    public void setNotTransportedPassengers(int notTransportedPassengers) {
        this.notTransportedPassengers = notTransportedPassengers;
    }

    public int getNotTransportedPassengers() {
        return notTransportedPassengers;
    }

    public void print() {
        System.out.println();
        System.out.println("\t\t\tCreate Building with " + MAX_FLOOR_NUMBER_IN_BUILDING + " floors\t\t\t");
        System.out.println();
    }
}
