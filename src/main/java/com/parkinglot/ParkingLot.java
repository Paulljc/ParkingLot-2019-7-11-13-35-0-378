package com.parkinglot;

import java.util.HashMap;

public class ParkingLot {

    private int capacity;
    private HashMap<ParkingTicket, Car> ticketMatchCar;

    public ParkingLot(int capacity, HashMap<ParkingTicket, Car> ticketMatchCar) {
        this.capacity = capacity;
        this.ticketMatchCar = ticketMatchCar;
    }

    public int getCapacity() {
        return capacity;
    }

    public HashMap<ParkingTicket, Car> getTicketMatchCar() {
        return ticketMatchCar;
    }

    public ParkingTicket generateTicketByCar(Car car){
        ParkingTicket parkingTicket = new ParkingTicket(car.getCarLicense());
        ticketMatchCar.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car TakeOutCarByTicket(ParkingTicket parkingTicket){
        if (ticketMatchCar.containsKey(parkingTicket)){
            Car car = ticketMatchCar.get(parkingTicket);
            ticketMatchCar.remove(parkingTicket);
            return car;
        }else if (parkingTicket == null){
            System.out.print("Please give me your ticket!");
            return null;
        }
        else{
            System.out.print("It is an invalid ticket!");
            return null;
        }
    }

}
