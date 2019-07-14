package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ManagerTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_fetch_ticket_by_car_when_manager_spectify_his_boy() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
        boy_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
        Manager manager = new Manager(manager_01_parkingLots);
        manager.addParkingBoy(parkingBoy1);
        //when
        String parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car).getCarLicense();
        //then
        assertThat(parkingTicket, is("9527"));
    }

    @Test
    public void should_not_fetch_ticket_by_car_when_manager_spectify_not_his_boy() {
        //given
        Car car = new Car("9527");
        ParkingLot parkingLot1 = new ParkingLot(10, 10, new HashMap<>(0), new HashMap<>(0));
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
        boy_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
        Manager manager = new Manager(manager_01_parkingLots);
        //when
        ParkingTicket parkingTicket = manager.specifyBoyParkCar(parkingBoy1, car);
        //then
        assertThat(systemOut(), is("You are not in charge of this boy!"));
        assertThat(parkingTicket, nullValue());
    }

    @Test
    public void should_fetch_car_by_ticket_when_manager_spectify_his_boy_in_parkingLot() {
        //given
        Car car = new Car("9527");
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ticketIsUsed.put("9527", false);
        ParkingLot parkingLot1 = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
        boy_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
        Manager manager = new Manager(manager_01_parkingLots);
        manager.addParkingBoy(parkingBoy1);
        //when
        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy1, parkingLot1, parkingTicket);
        //then
        assertThat(fetchCar.getCarLicense(), is("9527"));
    }

    @Test
    public void should_fetch_car_by_ticket_when_manager_spectify_his_boy_not_in_parkingLot() {
        //given
        Car car = new Car("9527");
        ParkingTicket parkingTicket = new ParkingTicket("9527");
        HashMap<ParkingTicket, Car> ticketMatchCar = new HashMap<>(0);
        HashMap<String, Boolean> ticketIsUsed = new HashMap<>(0);
        ticketMatchCar.put(parkingTicket, car);
        ticketIsUsed.put("9527", false);
        ParkingLot parkingLot1 = new ParkingLot(10, 10, ticketMatchCar, ticketIsUsed);
        ParkingLot parkingLot2 = new ParkingLot(12, 12, new HashMap<>(0), new HashMap<>(0));
        ArrayList<ParkingLot> boy_01_parkingLots = new ArrayList<>();
        ArrayList<ParkingLot> manager_01_parkingLots = new ArrayList<>();
        boy_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot1);
        manager_01_parkingLots.add(parkingLot2);
        ParkingBoy parkingBoy1 = new ParkingBoy(boy_01_parkingLots);
        Manager manager = new Manager(manager_01_parkingLots);
        manager.addParkingBoy(parkingBoy1);
        //when
        Car fetchCar = manager.specifyBoyFetchCar(parkingBoy1, parkingLot2, parkingTicket);
        //then
        assertThat(systemOut(), is("This parking boy can not fetch cay in this parkingLot"));
        assertThat(fetchCar, nullValue());
    }
}
