package com.henry.dev.email_service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmailServiceApplication {

    @Value("${spring.datasource.url}")
    String dbUrl;

    @Value("${spring.datasource.username}")
    String dbUser;

    @Value("${spring.datasource.password}")
    String dbPassword;

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    @PostConstruct
    public void testDatabaseConnection() {
        try {

            System.out.println("############################################");
            System.out.println("Conectando ao banco de dados em: " + dbUrl);
            System.out.println("Usuário: " + dbUser);
            System.out.println("Senha: " + dbPassword.replaceAll(".", "*"));

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
