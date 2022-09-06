package com.example.galaxyreader.controller;

import com.example.galaxyreader.model.PDF;
import com.example.galaxyreader.parser.Line;
import com.example.galaxyreader.parser.PDFLineReader;
import com.example.galaxyreader.parser.PDFViewer;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController
public class ConvertController {

    @CrossOrigin
    @PostMapping("/api/convert")
    public ResponseEntity<String> convert(@RequestBody PDF pdf) throws Exception {
        try {
            PDFLineReader lineReader = new PDFLineReader();
            List<Line> lines = lineReader.getLines(Base64.getDecoder().decode(pdf.base64));
            return ResponseEntity.ok(new Gson().toJson(new Response(PDFViewer.buildPDF(lines))));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(new Gson().toJson(new Response("PDF Error")));
        }
    }
}

class Response {
    String response;

    public Response(String response) {
        this.response = response;
    }
}