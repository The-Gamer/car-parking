package com.paidservice.car_parking.domain;

import com.paidservice.car_parking.constants.Availability;

import java.time.Instant;

public class ParkingSlot {
    private final String slotId;
    private final Availability status;
    private final Instant checkInTime;
    private final Instant checkOutTime;
    private final Instant expiry;

    public ParkingSlot(Builder builder) {
        this.slotId = builder.slotId;
        this.status = builder.status;
        this.checkInTime = builder.checkInTime;
        this.checkOutTime = builder.checkOutTime;
        this.expiry = builder.expiry;
    }

    public static class Builder{
        private String slotId;
        private Availability status;
        private Instant checkInTime = null;
        private Instant checkOutTime = null;
        private Instant expiry = null;

        public Builder(String slotId, Availability status){
            this.slotId = slotId;
            this.status = status;
        }

        public Builder checkInTime(Instant timeStamp){
            this.checkInTime = timeStamp;
            return this;
        }
        public Builder checkOutTime(Instant timeStamp){
            this.checkOutTime = timeStamp;
            return this;
        }
        public Builder expiry(Instant timeStamp){
            this.expiry = timeStamp;
            return this;
        }

        public ParkingSlot build(){
            ParkingSlot parkingSlot = new ParkingSlot(this);
            return parkingSlot;
        }

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


}
