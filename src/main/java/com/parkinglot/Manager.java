package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Manager extends ParkingBoy {

    private List<ParkingLot> parkingLots = new ArrayList<>();
    private List<ParkingBoy> parkingBoys = new ArrayList<>();


    public Manager(ArrayList<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public Manager(ArrayList<ParkingLot> parkingLots, List<ParkingLot> parkingLots1, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingLots = parkingLots1;
        this.parkingBoys = parkingBoys;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket specifyBoyParkCar(ParkingBoy parkingBoy, Car car) {
        if (parkingBoys.contains(parkingBoy)) {
            return parkingBoy.parkCarService(car);
        } else {
            System.out.print("You are not in charge of this boy!");
            return null;
        }
    }

    public Car specifyBoyFetchCar(ParkingBoy parkingBoy, ParkingLot parkingLot, ParkingTicket parkingTicket) {
        if (!parkingBoys.contains(parkingBoy)) {
            System.out.print("You are not in charge of this boy!");
            return null;
        } else if (!parkingBoy.parkingLots.contains(parkingLot)) {
            System.out.print("This parking boy can not fetch cay in this parkingLot");
            return null;
        } else {
            return parkingBoy.fetchCarService(parkingTicket);
        }
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public void setParkingBoys(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }
}
