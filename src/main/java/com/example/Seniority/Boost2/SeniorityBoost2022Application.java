package com.example.Seniority.Boost2;

import com.example.Seniority.Boost2.controller.TextController;
import com.example.Seniority.Boost2.entites.Text;
import com.example.Seniority.Boost2.services.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class SeniorityBoost2022Application{
	public static void main(String[] args) {
		SpringApplication.run(SeniorityBoost2022Application.class, args);
	}
}
