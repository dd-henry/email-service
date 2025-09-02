package com.henry.dev.email_service.controller;

import com.henry.dev.email_service.useCase.EmailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailUseCase useCase;

    @GetMapping("/{id}/confirm")
    public ResponseEntity<String> confirmEmail(@PathVariable String id) {
        useCase.confirmEmail(id);
        String html = "<html><body><h1 style='color: green;'>Email confirmado com sucesso!</h1></body></html>";
        return ResponseEntity
                .ok()
                .header("Content-Type", "text/html")
                .body(html);
    }
}
