package com.sander.elevator.model;

import com.sander.elevator.Direction;
import lombok.Data;

@Data
public class Passenger {
    private String name;
    private Direction direction;
    private int destinationFloorNumber;
    private int currentFloorNumber;
    private static final int MIN_FLOOR = 1;

    public Passenger(String passengerId, int currentFloorNumber) {
        this.name = passengerId;
        this.currentFloorNumber = currentFloorNumber;
        destinationFloorNumber = generateDestinationFloorNumber();
        direction = setDirection();
    }

    private int generateDestinationFloorNumber() {
        int middleOfHouse = Building.MAX_FLOOR_NUMBER_IN_BUILDING;
        int destinationFloorNumber = (int) (Math.random() * (Building.MAX_FLOOR_NUMBER_IN_BUILDING));

        if (destinationFloorNumber == currentFloorNumber) {
            destinationFloorNumber = extraRandom(middleOfHouse, destinationFloorNumber);
        }

        if (destinationFloorNumber == 0) {
            destinationFloorNumber = Building.MAX_FLOOR_NUMBER_IN_BUILDING / 2;
        }

        return destinationFloorNumber;
    }

    private Direction setDirection() {
        return currentFloorNumber < destinationFloorNumber ? Direction.UP : Direction.DOWN;
    }

    private int extraRandom(int middle, int destinationFloorNumber) {
        if (destinationFloorNumber >= middle) {
            return (int) (Math.random() * middle - 1) + 1;
        }

        return (int) (Math.random() * Building.MAX_FLOOR_NUMBER_IN_BUILDING) + middle + 1;
    }

}
