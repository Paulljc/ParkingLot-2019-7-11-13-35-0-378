package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingBoyTest {

    @Test
    public void should_fetch_ticket_by_car(){
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        String parkingTicket = parkingBoy.fetchTicketByCar(parkingLot, car).getCarLicense();

        //then
        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket(){
        //given
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar);
        ParkingBoy parkingBoy = new ParkingBoy();

        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket);

        //then
        assertThat(fetchCar.getCarLicense(), is("9527"));
    }
}
