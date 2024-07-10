package com.example.ticketBooking.controller;

import com.example.ticketBooking.configuration.ApplicationConfiguration;
import com.example.ticketBooking.dto.ClickOccupied;
import com.example.ticketBooking.dto.validateDto;
import com.example.ticketBooking.entity.TicketDetailsEntity;
import com.example.ticketBooking.repository.TicketDetailsRepo;
import com.example.ticketBooking.service.TicketDetailService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import freemarker.template.Template;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private TicketDetailService qrCodeService;
    @Autowired
    TicketDetailsRepo ticketDetailsRepo;

    @Autowired
    private ApplicationConfiguration freemarkerConfig;

    @GetMapping("/generate")
    public void generateQRCode(HttpServletResponse response, @RequestParam Integer userId) throws Exception {
        TicketDetailsEntity ticketDetailsEntity = ticketDetailsRepo.findByUserId(userId);
        String name = ticketDetailsEntity.getName();
        Integer id = ticketDetailsEntity.getUserId();

        BufferedImage qrImage = qrCodeService.generateQRCode(120, 120, userId);

        ByteArrayOutputStream qrCodeOutputStream = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", qrCodeOutputStream);

        String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeOutputStream.toByteArray());

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("organization", ticketDetailsEntity.getOrganization());
        templateData.put("designation", ticketDetailsEntity.getDesignation());
        templateData.put("name", ticketDetailsEntity.getName());
        templateData.put("type", ticketDetailsEntity.getType());
        templateData.put("qrCodeBase64", qrCodeBase64);
        String type = ticketDetailsEntity.getType();
        String templateName = null;
        if (type.equals("VIP")) {
            templateName = "QRcode_VIP.ftl";
        } else if (type.equals("VVIP")) {
            templateName = "QRcode_VVIP.ftl";
        } else {
            templateName = "QRcode_Delegates.ftl";
        }
        Template template = freemarkerConfig.freemarkerConfigurer().createConfiguration().getTemplate(templateName);
        String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);

        try (ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(pdfOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            PageSize customPageSize = new PageSize(416, 630);
            pdfDoc.setDefaultPageSize(customPageSize);

            Document document = new Document(pdfDoc);
            document.setMargins(0, 0, 0, 0);

            HtmlConverter.convertToPdf(htmlContent, pdfDoc, new ConverterProperties());

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "_" + id + ".pdf\"");
            try (OutputStream responseOutputStream = response.getOutputStream()) {
                responseOutputStream.write(pdfOutputStream.toByteArray());
                responseOutputStream.flush();
            }
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> split(@RequestBody validateDto text) {
        return qrCodeService.validateUser(text.getText());
    }
    @GetMapping("/getAllType")
    public ResponseEntity<?>allUser(){
            return ResponseEntity.ok().body( qrCodeService.countByType());
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?>getAll(){
            return ResponseEntity.ok().body(qrCodeService.getAllUser());
    }
    @PostMapping("/change")
    public ResponseEntity<?>change(@RequestParam Integer userId){
            return ResponseEntity.ok().body(qrCodeService.click(userId));
    }
}

