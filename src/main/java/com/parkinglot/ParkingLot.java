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
        System.out.println(ticketMatchCar);
        return parkingTicket;
    }

    public Car TakeOutCarByTicket(ParkingTicket parkingTicket){
        System.out.println(ticketMatchCar);
        Car car = ticketMatchCar.get(parkingTicket);
        ticketMatchCar.remove(parkingTicket);
        System.out.println(ticketMatchCar);
        return car;
    }

}
