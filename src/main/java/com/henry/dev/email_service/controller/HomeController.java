package com.henry.dev.email_service.controller;


import com.henry.dev.email_service.scheduler.ProduceMailScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {
    private final ProduceMailScheduler tools;

    @GetMapping
    public String home() {

        Random random = new Random();

        int randomNumber =   random.nextInt(1, 4);
        return switch (randomNumber) {
            case 1 -> "<h1>Tá rodando! 🚀</h1>";
            case 2 -> "<h1>HenryLabz \uD83D\uDDA5\uFE0F</h1>";
            default ->
                    "<h1>Pod está de pé!</h1>";
        };
    }

    @GetMapping("/shift")
    public String getActualShift(){
        return tools.getActualShift().getDescription();
    }
}