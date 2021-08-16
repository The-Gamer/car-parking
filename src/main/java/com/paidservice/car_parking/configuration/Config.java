package com.paidservice.car_parking.configuration;

import com.paidservice.car_parking.repository.ParkingSlotRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ParkingSlotRepo getParkingSlotrepo(){
        ParkingSlotRepo parkingSlotRepo = new ParkingSlotRepo();
        return parkingSlotRepo;
    }
}
