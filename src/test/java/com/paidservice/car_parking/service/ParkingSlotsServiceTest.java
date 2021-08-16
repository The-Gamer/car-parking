package com.paidservice.car_parking.service;

import com.paidservice.car_parking.constants.Availability;
import com.paidservice.car_parking.domain.ParkingSlot;
import com.paidservice.car_parking.repository.ParkingSlotRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingSlotsServiceTest {
    private ParkingSlotsService parkingSlotsService;
    @Mock private ParkingSlotRepo parkingSlotRepo;
    private List<ParkingSlot> parkingSlots;
    private  Map<String , ParkingSlot> parkingMap;

    @BeforeEach
    public void setUp() throws Exception{
        parkingSlotsService = new ParkingSlotsService(parkingSlotRepo);
        parkingSlots = Arrays.asList(new ParkingSlot.Builder("ABC-1",
                Availability.AVAILABLE).build(),
                new ParkingSlot.Builder("ABC-2",
                        Availability.AVAILABLE).build(),
                new ParkingSlot.Builder("ABC-3",
                        Availability.AVAILABLE).build(),
                new ParkingSlot.Builder("ABC-4",
                Availability.AVAILABLE).build(),
                new ParkingSlot.Builder("ABC-5",
                        Availability.AVAILABLE).build());
        parkingMap = new HashMap<>();
        parkingSlots.forEach(e->parkingMap.put(e.getSlotId(),e));
    }

    @DisplayName("Get all the service slots.")
    @Test
    void getAll_AvailableParkingSLots(){
        //arrange
        when(parkingSlotRepo.getAllParkingSlots(anyInt())).thenReturn(parkingMap);
        //act
        List<ParkingSlot> parkingSlots = parkingSlotsService.getAllParkingSlots();
        //assert
        assertTrue(parkingSlots.size() > 1);

    }

    @DisplayName("Reserve a available parking slot.")
    @Test
    void reserve_AParticularParkingSlot(){

        // arrange
        // I want to reserve a parking spot from the list of available parking slots.
        // Assuming I want to reserve the first parking slot item for 2 hrs.
        ParkingSlot updatedParkingSlot = getADummyParkingSlot();
        when(parkingSlotRepo.update(anyString(), any())).thenReturn(updatedParkingSlot);
        when(parkingSlotRepo.getParkingSlot(anyString())).thenReturn(getADummyParkingSlot());
        // act
        String parkingId = "ABC";
        ParkingSlot response = parkingSlotsService.reserveParkingSlot(parkingId,updatedParkingSlot);
        // assert
        assertNotNull(response);
    }

    @DisplayName("Extend a slot's time as its about to expire.")
    @Test
    void extend_AParkingSlotsTime(){
        //arrange
        // A client can extend the parking time,
        // Get a parking slot passed on position parking ID.
        when(parkingSlotRepo.getParkingSlot(anyString())).thenReturn(getADummyParkingSlot());
        ParkingSlot retrievedParkingSlot = parkingSlotsService.getParkingSlot("ABC");
        // Assuming client asks for an extension of 1 hour
        ParkingSlot extendedParkingSlot = new ParkingSlot.Builder(retrievedParkingSlot.getSlotId(),Availability.EXTENDED)
                .checkInTime(retrievedParkingSlot.getCheckInTime()).checkOutTime(retrievedParkingSlot.getCheckOutTime().plus(1,ChronoUnit.HOURS))
                .expiry(retrievedParkingSlot.getCheckOutTime().plus(1,ChronoUnit.HOURS)).build();
        when(parkingSlotRepo.update(anyString(),any())).thenReturn(extendedParkingSlot);

        //act
        ParkingSlot parkingSlot = parkingSlotsService.extendParkingTime(extendedParkingSlot);
        //assert
        assertTrue(parkingSlot.getCheckOutTime().compareTo(retrievedParkingSlot.getCheckOutTime()) > 0);
    }

    @DisplayName("Update a parking slot's status to available when client has left parking.")
    @Test
    void update_ParkingSlotsStatus(){
        //arrange
        // After a client has left the facility update the status of parking slot to be available and reset checkIn checkout time to null.
        when(parkingSlotRepo.getParkingSlot(anyString())).thenReturn(getADummyParkingSlot());
        ParkingSlot parkingSlot = parkingSlotsService.getParkingSlot("ABC");
        when(parkingSlotRepo.update(anyString(),any())).thenReturn(getAnEmptyparkingSlot());
        //act
        boolean reset = parkingSlotsService.resetParkingSlot(parkingSlot.getSlotId());
        //assert
        assertTrue(reset);
    }

    private ParkingSlot getAnEmptyparkingSlot(){
        ParkingSlot emptyparkingSlot = new ParkingSlot.Builder("ABC-2"
                ,Availability.RESERVED).build();
        return emptyparkingSlot;
    }

    private ParkingSlot getADummyParkingSlot(){
        Instant timeNow = Instant.now();
        ParkingSlot updatedParkingSlot = new ParkingSlot.Builder("ABC-2"
                ,Availability.RESERVED).checkInTime(
                timeNow).checkOutTime(timeNow.plus(2, ChronoUnit.HOURS)).expiry(timeNow.plus(2, ChronoUnit.HOURS)).build();
        return updatedParkingSlot;
    }

}