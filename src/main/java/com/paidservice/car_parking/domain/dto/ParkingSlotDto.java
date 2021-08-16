package com.paidservice.car_parking.domain.dto;

import com.paidservice.car_parking.constants.Availability;

import java.time.Instant;

public class ParkingSlotDto {
    private final String slotId;
    private final Availability status;
    private final Instant checkInTime;
    private final Instant checkOutTime;
    private final Instant expiry;

    public ParkingSlotDto(String slotId, Availability status, Instant checkInTime, Instant checkOutTime, Instant expiry) {
        this.slotId = slotId;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.expiry = expiry;
    }

    public String getSlotId() {
        return slotId;
    }

    public Availability getStatus() {
        return status;
    }

    public Instant getCheckInTime() {
        return checkInTime;
    }

    public Instant getCheckOutTime() {
        return checkOutTime;
    }

    public Instant getExpiry() {
        return expiry;
    }

    @Override
    public String toString() {
        return "ParkingSlotDto{" +
                "slotId='" + slotId + '\'' +
                ", status=" + status +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", expiry=" + expiry +
                '}';
    }
}
