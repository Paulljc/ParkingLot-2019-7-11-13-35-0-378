package com.parkinglot;

public class ParkingBoy {

    public ParkingTicket fetchTicketByCar(ParkingLot parkingLot, Car car){
        return parkingLot.generateTicketByCar(car);
    }

    public Car fetchCarByTickey(ParkingLot parkingLot, ParkingTicket parkingTicket){
        return parkingLot.TakeOutCarByTicket(parkingTicket);
    }
}
