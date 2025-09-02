package com.henry.dev.email_service.controller;

import com.henry.dev.email_service.domain.request.Email;
import com.henry.dev.email_service.scheduler.ProduceMailScheduler;
import com.henry.dev.email_service.useCase.RegistrationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private final RegistrationUseCase useCase;
    private final ProduceMailScheduler scheduler;

    @PostMapping("/register")
    public ResponseEntity<String> registerMail(@RequestBody Email email) {
        try {
            useCase.register(email);
            return ResponseEntity.ok("Email registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao enviar email: " + e.getMessage());
        }
    }


    @PostMapping("/run-scheduler")
    public void runScheduler() {
        scheduler.execute();
    }
}
