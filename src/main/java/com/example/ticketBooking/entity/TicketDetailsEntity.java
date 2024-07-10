package com.example.ticketBooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ITNT_DETAILS")
public class TicketDetailsEntity {
    @Id
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "ORGANIZATION")
    private String organization;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "TABLE_NO")
    private Integer tableNo;
    @Column(name = "IS_SCANNED")
    private Character isScanned;
    @Column(name = "IS_OCCUPIED")
    private Character isOccupied;
    @Column(name = "SCANNED_TIME")
    private LocalDateTime scannedTime;

}
