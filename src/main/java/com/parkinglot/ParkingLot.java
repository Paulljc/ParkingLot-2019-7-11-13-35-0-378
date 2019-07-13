package com.parkinglot;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParkingLot {

    private int capacity;
    private HashMap<ParkingTicket, Car> ticketMatchCar;
    private HashMap<String, Boolean> ticketIsUsed;

    public ParkingLot(int capacity, HashMap<ParkingTicket, Car> ticketMatchCar, HashMap<String, Boolean> ticketIsUsed) {
        this.capacity = capacity;
        this.ticketMatchCar = ticketMatchCar;
        this.ticketIsUsed = ticketIsUsed;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<ParkingTicket, Car> getTicketMatchCar() {
        return ticketMatchCar;
    }

    public HashMap<String, Boolean> getTicketIsUsed() {
        return ticketIsUsed;
    }

    public ParkingTicket generateTicketByCar(Car car) {
        if (ticketMatchCar.containsKey(car.getCarLicense())) {
            System.out.print("Car is in the parkingLot！");
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket(car.getCarLicense());
            ticketMatchCar.put(parkingTicket, car);
            if (ticketIsUsed.containsKey(car.getCarLicense())){
                ticketIsUsed.put(car.getCarLicense(), false);
            }else{
                ticketIsUsed.put(car.getCarLicense(), false);
            }
            return parkingTicket;
        }
    }

    public Car TakeOutCarByTicket(ParkingTicket parkingTicket) {
        if (ticketMatchCar.containsKey(parkingTicket)) {
            if (!ticketIsUsed.get(parkingTicket.getCarLicense())) {
                Car car = ticketMatchCar.get(parkingTicket);
                ticketIsUsed.put(parkingTicket.getCarLicense(), true);
                return car;
            } else {
                System.out.print("Sorry, your ticket is uesd!");
                return null;
            }
        } else if (parkingTicket == null) {
            System.out.print("Please give me your ticket!");
            return null;
        } else {
            System.out.print("It is an invalid ticket!");
            return null;
        }
    }

}
