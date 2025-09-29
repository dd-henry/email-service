package com.henry.dev.email_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String home() {

        Random random = new Random();

        int randomNumber =   random.nextInt(1, 4);
        return switch (randomNumber) {
            case 1 -> "Tá rodando! 🚀";
            case 2 -> "HenryLabz \uD83D\uDDA5\uFE0F";
            default ->
                    "Pod está de pé!";
        };
    }
}