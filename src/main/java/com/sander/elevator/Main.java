package com.sander.elevator;

import com.sander.elevator.model.Building;

public class Main {

    public static void main(String[] args) {
        Building building = new Building();
        while (building.isNotTransportedPassengers()) {
            building.getElevator().move();
        }

        System.out.println("=================================================");
        System.out.println("|\t\t\t!!!Elevator stopped!!!\t\t\t\t|");
        System.out.println("=================================================");
    }
}
