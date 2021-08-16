package com.paidservice.car_parking.service;

import com.paidservice.car_parking.constants.Availability;
import com.paidservice.car_parking.domain.ParkingSlot;
import com.paidservice.car_parking.repository.ParkingSlotRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSlotsService {
    @Value("${parkinglot.size}")
    private int availableParkingSlotsInitially;
    private ParkingSlotRepo parkingSlotRepo;

    /**
     *
     * @param parkingSlotRepo
     */
    ParkingSlotsService (ParkingSlotRepo parkingSlotRepo){
        this.parkingSlotRepo = parkingSlotRepo;
    }

    /**
     * Get All parking slots available
     * @return
     */
    public List<ParkingSlot> getAllParkingSlots(){
       return parkingSlotRepo.getAllParkingSlots(availableParkingSlotsInitially).values().stream().collect(Collectors.toList());
    }

    /**
     *
     * @param parkingId
     * @param updatedParkingSlot
     * @return
     */
    public ParkingSlot reserveParkingSlot(String parkingId, ParkingSlot updatedParkingSlot) {
        ParkingSlot parkingSlot = parkingSlotRepo.getParkingSlot(parkingId);
        if(parkingSlot == null){
            return null;
        }else{
            return  parkingSlotRepo.update(parkingId, updatedParkingSlot);
        }
    }

    /**
     *
     * @param parkingId
     * @return
     */
    public ParkingSlot getParkingSlot(String parkingId) {
        ParkingSlot parkingSlot = parkingSlotRepo.getParkingSlot(parkingId);
        return parkingSlot;

    }

    /**
     *
     * @param extendedParkingSlot
     * @return
     */
    public ParkingSlot extendParkingTime(ParkingSlot extendedParkingSlot) {
        return parkingSlotRepo.update(extendedParkingSlot.getSlotId(),extendedParkingSlot);
    }

    /**
     * This method resets a parking lot to as ut was before
     * @param parkingSlotId
     * @return
     */
    public boolean resetParkingSlot(String parkingSlotId) {
        ParkingSlot refresedParkingSlot = null;
        ParkingSlot parkingSlotToBeReset = parkingSlotRepo.getParkingSlot(parkingSlotId);
        if(parkingSlotToBeReset != null){
            refresedParkingSlot = new ParkingSlot.Builder(parkingSlotId, Availability.AVAILABLE).build();
        }
        ParkingSlot refreshedParkingSlot = parkingSlotRepo.update(parkingSlotId, refresedParkingSlot);
        return refreshedParkingSlot != null?true:false;
    }
}
