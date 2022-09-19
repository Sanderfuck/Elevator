package com.sander.elevator.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Floor {
    private static final int MAX_PASSENGERS_COUNT = 10;
    private List<Passenger> passengers = new ArrayList<>();
    private int floorNumber;
    private int passengerQuantityOnTheFloor;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.passengerQuantityOnTheFloor = generatePassengerQuantity();
        generatePassengersOnTheFloor();
        System.out.println(toString());
    }

    private int generatePassengerQuantity() {
        return (int) (Math.random() * 10);
    }

    private void generatePassengersOnTheFloor() {
        int startPassengerNumber = passengerQuantityOnTheFloor;

        for (int i = 1; i <= startPassengerNumber; i++) {
            String passengerId = "F" + floorNumber + " id" + i;
            this.passengers.add(new Passenger(passengerId, floorNumber));
        }
    }

    @Override
    public String toString() {
        return "Create new Floor with{" +
                "floorNumber=" + floorNumber +
                ", \t passengerQuantityOnTheFloor=" + passengers.size() +
                '}';
    }
}
