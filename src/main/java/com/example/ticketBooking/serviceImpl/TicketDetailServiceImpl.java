package com.example.ticketBooking.serviceImpl;

import com.example.ticketBooking.entity.TicketDetailsEntity;
import com.example.ticketBooking.repository.TicketDetailsRepo;
import com.example.ticketBooking.service.TicketDetailService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TicketDetailServiceImpl implements TicketDetailService {

    @Autowired
    TicketDetailsRepo ticketDetailsRepo;

    @Override
    public BufferedImage generateQRCode(int width, int height, Integer userId) throws Exception {
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN,1);
        TicketDetailsEntity ticketDetailsEntity = ticketDetailsRepo.findByUserId(userId);
        String name = ticketDetailsEntity.getName();
        String type = ticketDetailsEntity.getType();
        Integer id=ticketDetailsEntity.getUserId();
        Integer tableNo = ticketDetailsEntity.getTableNo();
        String text = name + ":" + type + ":" + tableNo+":"+id;
        Color qrCodeColor;
            qrCodeColor = Color.BLACK;
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hintMap);
        BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        BufferedImage coloredQRCodeImage = addColorToQRCode(qrCodeImage, qrCodeColor, Color.WHITE);

//        String headerText = "Hi my QR code";
//        int headerHeight = 50;
//        BufferedImage finalImage = addHeaderToImage(coloredQRCodeImage, headerText, headerHeight);
        ticketDetailsEntity.setIsScanned('y');
        LocalDateTime now=LocalDateTime.now();
        ticketDetailsEntity.setScannedTime(now);
        ticketDetailsRepo.save(ticketDetailsEntity);
        return coloredQRCodeImage;
    }

    @Override
    public List<TicketDetailsEntity> getAllUser() {
       return ticketDetailsRepo.findAll();
    }

    @Override
    public Map<String, Object> countByType() {
        Map<String,Object>count=new HashMap<>();
        count.put("OccupiedVIP",ticketDetailsRepo.countVipOccupied());
        count.put("OccupiedVVIP",ticketDetailsRepo.countVVipOccupied());
        count.put("OccupiedDELEGATES",ticketDetailsRepo.countDelegatesOccupied());
        count.put("UnOccupiedVIP",ticketDetailsRepo.countVipUnOccupied());
        count.put("UnOccupiedVVIP",ticketDetailsRepo.countVVipUnOccupied());
        count.put("UnOccupiedDELEGATES",ticketDetailsRepo.countDelegatesUnOccupied());
        return count;
    }

    @Override
    public ResponseEntity<String> validateUser(String text) {

        String SuccessMessage="Occupied";
        String FailureMessage="Already Occupied";

        String[] split = text.split(":");

        Integer userId = Integer.valueOf(split[3]);
        TicketDetailsEntity ticketDetailsEntity = ticketDetailsRepo.findByUserId(userId);
        Character occupiedOrNot=ticketDetailsEntity.getIsOccupied();
        if(occupiedOrNot.equals('N')) {
            ticketDetailsEntity.setIsOccupied('y');
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(FailureMessage);
        ticketDetailsRepo.save(ticketDetailsEntity);
        return ResponseEntity.ok().body(SuccessMessage);
    }

    @Override
    public ResponseEntity<?> click( Integer userId) {
        TicketDetailsEntity ticketDetails=ticketDetailsRepo.findByUserId(userId);
        ticketDetails.setIsOccupied('Y');
        ticketDetailsRepo.save(ticketDetails);
        return ResponseEntity.ok().body(ticketDetails);
    }

    private BufferedImage addColorToQRCode(BufferedImage qrCodeImage, Color onColor, Color offColor) {
        int width = qrCodeImage.getWidth();
        int height = qrCodeImage.getHeight();
        BufferedImage coloredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = qrCodeImage.getRGB(x, y);
                if (color == Color.BLACK.getRGB()) {
                    coloredImage.setRGB(x, y, onColor.getRGB());
                } else {
                    coloredImage.setRGB(x, y, offColor.getRGB());
                }
            }
        }

        return coloredImage;
    }

    private BufferedImage addHeaderToImage(BufferedImage qrCodeImage, String headerText, int headerHeight) {
        int width = qrCodeImage.getWidth();
        int height = qrCodeImage.getHeight() + headerHeight;

        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = finalImage.createGraphics();

        // Draw header
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, headerHeight);

        // Set font and color
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Montserrat", Font.BOLD, 26));

        // Measure the text to center it
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(headerText);
        int textHeight = fontMetrics.getAscent();
        int x = (width - textWidth) / 2;
        int y = (headerHeight - textHeight) / 2 + textHeight; // Center vertically

        graphics.drawString(headerText, x, y);

        // Draw QR code
        graphics.drawImage(qrCodeImage, 0, headerHeight, null);

        graphics.dispose();

        return finalImage;
    }
    }



