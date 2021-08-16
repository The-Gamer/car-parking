package com.paidservice.car_parking.repository;

import com.paidservice.car_parking.domain.ParkingSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static com.paidservice.car_parking.constants.Availability.RESERVED;
import static org.junit.jupiter.api.Assertions.*;

class ParkingSlotRepoTest {
    private ParkingSlotRepo parkingSlotRepo;
    int mapSize = 10;
    @BeforeEach
    void setUp(){
        parkingSlotRepo = new ParkingSlotRepo();
    }
    @DisplayName("Test to retrieve a Map of parking slots")
    @Test
    void get_ParkingSlots(){
        Map<String, ParkingSlot> parkingSlotMap = parkingSlotRepo.getAllParkingSlots(mapSize);
        assertTrue(parkingSlotMap.size() == 10);
        //Check to see the map is not being reinitialized;
        Map<String,ParkingSlot> secondcall = parkingSlotRepo.getAllParkingSlots(mapSize);
        assertEquals(parkingSlotMap,secondcall);
        // With above check we can be sure that there is a single Map in memory
    }

    @DisplayName("Test to check that we can find a Parkingslot by Id")
    @Test
    void get_ASingleParkingSlot(){
        Map<String, ParkingSlot> parkingSlotMap = parkingSlotRepo.getAllParkingSlots(mapSize);
        ParkingSlot parkingSlot = parkingSlotMap.values().stream().findFirst().get();
        ParkingSlot returnedParkingSlopFromRepo = parkingSlotRepo.getParkingSlot(parkingSlot.getSlotId());
        assertEquals(parkingSlot.getSlotId(),returnedParkingSlopFromRepo.getSlotId());
    }

    @DisplayName("Test to check that the parkingSlot can be modified")
    @Test
    void update_ParkingSlotValues(){
        Map<String, ParkingSlot> parkingSlotMap = parkingSlotRepo.getAllParkingSlots(mapSize);
        ParkingSlot parkingSlot = parkingSlotMap.values().stream().findFirst().get();
        ParkingSlot updatedParkingSLot = new ParkingSlot.Builder(parkingSlot.getSlotId(),RESERVED).build();
        ParkingSlot repoResponseForUpdate = parkingSlotRepo.update(parkingSlot.getSlotId(),updatedParkingSLot);
        //Calling to check if the map has the latest values.
        Map<String, ParkingSlot> parkingSlotMapUpdated = parkingSlotRepo.getAllParkingSlots(mapSize);
        assertTrue(parkingSlotMapUpdated.size() == 10);
    }

}