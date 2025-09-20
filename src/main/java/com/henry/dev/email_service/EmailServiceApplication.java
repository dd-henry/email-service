package com.henry.dev.email_service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @PostConstruct
    public void testDatabaseConnection() {
        try {
            jdbc.query("SELECT * from lembretes", rs -> null);
            System.out.println("""
                    ##########################################
                    Conexão com o banco realizada com sucesso.
                    ##########################################
                    """);
        } catch (Exception e) {
            System.err.println("Falha na conexão com o banco: " + e.getMessage());
        }
    }
}
