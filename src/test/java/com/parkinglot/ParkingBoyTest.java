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

    @Test
    public void should_fetch_corresponding_ticket_by_different_car(){
        //given
        Car car1 = new Car("9527");
        Car car2 = new Car("8080");
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<>(0));
        ParkingBoy parkingBoy = new ParkingBoy();
        //when
        String parkingTicket1 = parkingBoy.fetchTicketByCar(parkingLot, car1).getCarLicense();
        String parkingTicket2 = parkingBoy.fetchTicketByCar(parkingLot, car2).getCarLicense();
        //then
        assertThat(parkingTicket1, is("9527"));
        assertThat(parkingTicket2, is("8080"));
    }

    @Test
    public void should_fetch_corresponding_car_by_different_ticket(){
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car1 = new Car("9527");
        Car car2 = new Car("8080");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car1);
        ticketMatchCar.put(parkingTicket2, car2);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar);
        ParkingBoy parkingBoy = new ParkingBoy();
        //when
        Car fetchCar1 = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket1);
        Car fetchCar2 = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket2);
        //then
        assertThat(fetchCar1.getCarLicense(), is("9527"));
        assertThat(fetchCar2.getCarLicense(), is("8080"));
    }

}
