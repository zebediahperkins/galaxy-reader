package com.example.galaxyreader.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReactController {
    @GetMapping("/")

    public ResponseEntity<String> react() {
        // TODO: SERVE REACT APP HERE
        return ResponseEntity.ok("Placeholder");
    }
}
