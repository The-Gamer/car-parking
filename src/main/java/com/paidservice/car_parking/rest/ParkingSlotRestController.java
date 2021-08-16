package com.paidservice.car_parking.rest;

import com.paidservice.car_parking.domain.ParkingSlot;
import com.paidservice.car_parking.domain.dto.ParkingSlotDto;
import com.paidservice.car_parking.rest.exception.NotFoundException;
import com.paidservice.car_parking.service.ParkingSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ParkingSlotRestController {
    @Autowired
    private ParkingSlotsService parkingSlotsService;

    @GetMapping("/parkingSlots")
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingSlot> getParkingSlots(){
        List<ParkingSlot> parkingSlots = parkingSlotsService.getAllParkingSlots();
        if(parkingSlots.size() == 0){
            throw  new NotFoundException("No Parking slots found");
        }
        return parkingSlots;
    }

    @GetMapping("parkingSlots/{parkingSlotId}")
    @ResponseStatus(HttpStatus.OK)
    public ParkingSlot getParkingSlot(@PathVariable(required = true) String parkingSlotId){
        ParkingSlot parkingSlot = parkingSlotsService.getParkingSlot(parkingSlotId);
        if(parkingSlot == null){
            throw  new NotFoundException("There is no parking slot with "+parkingSlotId);
        }
        return parkingSlot;
    }

    @PutMapping("/parkings/{parkingSlotId}/reserve")
    @ResponseStatus(HttpStatus.OK)
    public ParkingSlot reserveParking(@RequestBody ParkingSlotDto parkingSlotDto){

        return null;
    }

    @PutMapping("/parkings/{parkingSlotId}/extendTime")
    @ResponseStatus(HttpStatus.OK)
    public ParkingSlot extendParkingTime(@PathVariable(required = true) String parkingSlotId,@RequestParam(required = true) String hours ){
        return null;
    }

    @PutMapping("/parkings/{parkingSlotId}/resetparking")
    @ResponseStatus(HttpStatus.OK)
    public void resetParkingStatus(@PathVariable(required = true) String parkingSlotId){

    }
}
