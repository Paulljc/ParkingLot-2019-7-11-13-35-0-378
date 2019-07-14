package com.parkinglot;

import java.util.ArrayList;

public class ParkingBoy {

    ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public ParkingBoy(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket fetchTicketByCar(Car car){
        for (int i = 0; i < parkingLots.size(); i++) {
            if (!parkingLots.get(i).isParkingFull()){
                return parkingLots.get(i).generateTicketByCar(car);
            }else{
                if (i + 1 < parkingLots.size()){
                    continue;
                }else{
                    break;
                }
            }
        }
        System.out.print("Not enough position.");
        return null;
    }

    public Car fetchCarByTickey(ParkingLot parkingLot, ParkingTicket parkingTicket){
        return parkingLot.TakeOutCarByTicket(parkingTicket);
    }

}
