package com.paidservice.car_parking.repository;

import com.paidservice.car_parking.constants.Availability;
import com.paidservice.car_parking.domain.ParkingSlot;

import java.util.HashMap;
import java.util.Map;

/**
 * A class which acts as a repository.
 */
public class ParkingSlotRepo {
    public static Map<String, ParkingSlot> parkingSlots = new HashMap<>();
    //Method to get all parking Slots
    public Map<String,ParkingSlot> getAllParkingSlots(int size){
        if(parkingSlots.size() == 0) {
            for (int i = 0; i < size; i++) {
                String parkingId = "ParkingSlotId:" + i + 1;
                parkingSlots.put(parkingId, new ParkingSlot.Builder(parkingId, Availability.AVAILABLE).build());
            }
        }

        return parkingSlots;
    }

    // Method to update a parking slot.
    public ParkingSlot update(String parkingId, ParkingSlot updatedParkingSlot) {
        parkingSlots.put(parkingId, updatedParkingSlot);
        return updatedParkingSlot;
    }

    // Method to get a parking slot.
    public ParkingSlot getParkingSlot(String parkingId) {
        return parkingSlots.get(parkingId);
    }


}
