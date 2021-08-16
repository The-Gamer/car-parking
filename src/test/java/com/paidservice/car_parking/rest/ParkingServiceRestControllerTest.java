package com.paidservice.car_parking.rest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest()
class ParkingServiceRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("Test to get parking slot")
    @Test
    void getParkingSlot_ReturnsAParkingSlotForACar() throws Exception{
        Instant timeNow = Instant.parse("2021-08-14T12:34:32.847Z");
        // arrange
         mockMvc
                .perform(MockMvcRequestBuilders.get("/parkingSlots/MLCP/blocks/block-A/slots/slot-1?carNumber=KA-04-MQ-1234"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("checkInTime").value(String.valueOf(timeNow)))
                .andExpect(jsonPath("checkOutTime").value(String.valueOf(timeNow.plus(1, ChronoUnit.HOURS))))
                 .andExpect(jsonPath("expiryTime").value(String.valueOf(timeNow.plus(4,ChronoUnit.HOURS))))
                 .andExpect(jsonPath("carNumber").value("KA-04-MQ-1234"));

    }

}