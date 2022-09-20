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

        checkDirection();

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

        building.setNotTransportedPassengers(building.getNotTransportedPassengers() - exitPassengersFromElevator.size());

        for (Passenger passenger : exitPassengersFromElevator) {
            System.out.println(passenger + " exit from elevator on " + currentFloor.getFloorNumber() + " floor");
        }
    }

    private void checkDirection() {
        if (currentFloorNumber == Building.MAX_FLOOR_NUMBER_IN_BUILDING) {
            direction = Direction.DOWN;
        }

        if (currentFloorNumber == 1) {
            direction = Direction.UP;
        }
    }

    private int getVacancies() {
        return ELEVATOR_MAX_CAPACITY - passengersInElevator.size();
    }

    public void print() {
        System.out.println("=================================================");
        System.out.println("|\t\t\tElevator now in " + currentFloorNumber + " floor\t\t\t\t|");
        System.out.println("=================================================");
    }
}
