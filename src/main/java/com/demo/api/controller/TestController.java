package com.demo.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/test")
public class TestController {

    // test method
    @GetMapping(value = "/get")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("test");
    }
}
