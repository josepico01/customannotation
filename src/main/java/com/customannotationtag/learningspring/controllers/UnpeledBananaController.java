package com.customannotationtag.learningspring.controllers;

import com.customannotationtag.learningspring.annotation.BananaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/bananas/{type}")
@BananaType(path = "/bananas/{type}", type = "unpeled")
public class UnpeledBananaController {
    @PostMapping("/eat")
    public ResponseEntity<String> doSomething() {
        return ResponseEntity.ok("Eww banana skin");
    }
}
