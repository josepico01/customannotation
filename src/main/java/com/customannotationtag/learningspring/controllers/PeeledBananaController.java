package com.customannotationtag.learningspring.controllers;

import com.customannotationtag.learningspring.annotation.BananaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bananas/{type}")
@BananaType(path = "/bananas/{type}", type = "peeled")
public class PeeledBananaController {
    @PostMapping("/eat/")
    public ResponseEntity<String> doSomething() {
        return ResponseEntity.ok("Delicious");
    }
}
