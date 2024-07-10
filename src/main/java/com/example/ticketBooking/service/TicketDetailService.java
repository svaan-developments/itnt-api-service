package com.example.ticketBooking.service;

import com.example.ticketBooking.entity.TicketDetailsEntity;
import org.springframework.http.ResponseEntity;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface TicketDetailService {

    BufferedImage generateQRCode(int width, int height,Integer userId) throws Exception;

    List<TicketDetailsEntity>getAllUser();

    Map<String,Object>countByType();

    ResponseEntity<String> validateUser(String text);

//    ResponseEntity<TicketDetailsEntity>validate(String sample);

    ResponseEntity<?>click(Integer userId);
}
