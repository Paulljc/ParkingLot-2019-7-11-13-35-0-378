package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingBoyTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_fetch_ticket_by_car(){
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket(){
        //given
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ticketIsUsed.put("9527", false);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket1 = parkingBoy.fetchTicketByCar(car1).getCarLicense();
        String parkingTicket2 = parkingBoy.fetchTicketByCar(car2).getCarLicense();
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
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car1);
        ticketMatchCar.put(parkingTicket2, car2);
        ticketIsUsed.put("9527", false);
        ticketIsUsed.put("8080", false);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar1 = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket1);
        Car fetchCar2 = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket2);
        //then
        assertThat(fetchCar1.getCarLicense(), is("9527"));
        assertThat(fetchCar2.getCarLicense(), is("8080"));
    }

    @Test
    public void should_not_fetch_car_if_ticket_invalid(){
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = new ParkingTicket("8080");
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket2);
        //then
        assertThat(systemOut(), is("Unrecognized parking ticket."));
        assertThat(fetchCar,nullValue());
    }

    @Test
    public void should_not_fetch_car_if_no_ticket(){
        //given
        ParkingTicket parkingTicket1 = new ParkingTicket("9527");
        ParkingTicket parkingTicket2 = null;
        Car car = new Car("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket1, car);
        ParkingLot parkingLot = new ParkingLot(10, ticketMatchCar, ticketIsUsed);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Car fetchCar = parkingBoy.fetchCarByTickey(parkingLot, parkingTicket2);
        //then
        assertThat(systemOut(), is("Please provide your parking ticket."));
        assertThat(fetchCar,nullValue());
    }

    @Test
    public void should_not_fetch_ticket_if_parkingLot_is_full(){
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot = new ParkingLot(10, new HashMap<>(0), new HashMap<>(0));
        parkingLot.setCapacity(0);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        Object parkingTicket = parkingBoy.fetchTicketByCar(car);
        //then
        assertThat(systemOut(), is("Not enough position."));
        assertThat(parkingTicket,nullValue());
    }

    @Test
    public void should_park_second_parkingLot_if_first_parkingLot_is_full(){
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(10, new HashMap<>(0), new HashMap<>(0));
        parkingLot1.setCapacity(0);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        //when
        String parkingTicket = parkingBoy.fetchTicketByCar(car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
    }
}
