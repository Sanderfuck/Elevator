package com.sander.elevator.service;

import com.sander.elevator.Direction;
import com.sander.elevator.model.Building;
import com.sander.elevator.model.Floor;
import com.sander.elevator.model.Passenger;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Elevator {
    public static final int ELEVATOR_MAX_CAPACITY = 5;
    private final Building building;
    private int currentFloorNumber;
    private Floor currentFloor;
    private int destinationFloorNumber = 2;
    private Direction direction;
    private List<Passenger> passengersInElevator = new ArrayList<>();

    public Elevator(Building building) {
        this.building = building;
        currentFloorNumber = 1;
        direction = Direction.UP;
        currentFloor = building.getFloor(currentFloorNumber);
        print();
    }

    public void move() {
        changePassengers();

        if (direction == Direction.UP) {
            currentFloor = building.getFloor(++currentFloorNumber);
        } else {
            currentFloor = building.getFloor(--currentFloorNumber);
        }

        System.out.println("=================================================");
        System.out.println("|\t\t\tElevator move to " + currentFloorNumber + " floor\t\t\t|");
        System.out.println("=================================================");
    }

    private void changePassengers() {
        if (!passengersInElevator.isEmpty()) {
            removePassengersFromElevator();
        }

        if (destinationFloorNumber == currentFloorNumber) {
            setNewDirection();
        }

        if (!currentFloor.getPassengers().isEmpty() && getVacancies() > 0) {
            addPassengersToElevator();
        }
    }

    private void addPassengersToElevator() {
        if (currentFloorNumber == Building.MAX_FLOOR_NUMBER_IN_BUILDING) {
            direction = Direction.DOWN;
        }

        if (currentFloorNumber == 1) {
            direction = Direction.UP;
        }

        for (Passenger passenger : currentFloor.getPassengers()) {
            if (passenger.getDirection() == direction && getVacancies() > 0) {
                passengersInElevator.add(passenger);
                System.out.println(passenger + " enter in elevator on " + currentFloorNumber + " floor");
            }
        }

        deletePassengersFromFloor();
    }

    private void deletePassengersFromFloor() {
        List<Passenger> currentFloorPassengers = currentFloor.getPassengers();
        currentFloorPassengers.removeAll(passengersInElevator);
    }

    private void removePassengersFromElevator() {
        List<Passenger> exitPassengersFromElevator = passengersInElevator.stream()
                .filter(passenger -> passenger.getDestinationFloorNumber() == currentFloor.getFloorNumber())
                .collect(Collectors.toList());

        passengersInElevator.removeAll(exitPassengersFromElevator);

        for (Passenger passenger : exitPassengersFromElevator) {
            System.out.println(passenger + " exit from elevator on " + currentFloor.getFloorNumber() + " floor");
        }
    }

    private void checkDestinationFloor() {
        for (Passenger passenger : passengersInElevator) {
            if (destinationFloorNumber < passenger.getDestinationFloorNumber()) {
                destinationFloorNumber = passenger.getDestinationFloorNumber();
            }
        }

        direction = checkDirection();
    }

    private Direction checkDirection() {
        return currentFloorNumber < destinationFloorNumber ? Direction.UP : Direction.DOWN;
    }

    private int getVacancies() {
        return ELEVATOR_MAX_CAPACITY - passengersInElevator.size();
    }

    private void setNewDirection() {
        int up = 0;
        int down = 0;
        for (int i = 0; i < currentFloor.getPassengers().size(); i++) {
            if (currentFloor.getPassengers().get(i).getDirection() == Direction.UP) {
                up++;
            } else {
                down++;
            }
        }

        direction = down >= up ? Direction.DOWN : Direction.UP;
    }

    public void print() {
        System.out.println("=================================================");
        System.out.println("|\t\t\tElevator now in " + currentFloorNumber + " floor\t\t\t\t|");
        System.out.println("=================================================");
    }
}
