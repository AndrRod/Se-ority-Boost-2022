package com.example.Seniority.Boost2;

import com.example.Seniority.Boost2.entity.Text;
import com.example.Seniority.Boost2.service.TextService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SeniorityBoost2022Application{
	public static void main(String[] args) {
		SpringApplication.run(SeniorityBoost2022Application.class, args);
	}

	@Bean
	CommandLineRunner run(TextService textService){
		return args -> {
			textService.saveText(new Text(null, "Uno", null, 2, null));
			textService.saveText(new Text(null, "Dos", null, 2, null));
			textService.saveText(new Text(null, "Tres", null, 2, null));
			textService.saveText(new Text(null, "Cuatro", null, 2, null));
			textService.saveText(new Text(null, "Cinco", null, 2, null));
			textService.saveText(new Text(null, "Seis", null, 2, null));
			textService.saveText(new Text(null, "Siete", null, 2, null));
			textService.saveText(new Text(null, "Ocho", null, 2, null));
			textService.saveText(new Text(null, "Nueve", null, 2, null));
			textService.saveText(new Text(null, "Diez", null, 2, null));
			textService.saveText(new Text(null, "Once", null, 2, null));
			textService.saveText(new Text(null, "Doce", null, 2, null));
			textService.saveText(new Text(null, "Trece", null, 2, null));
			textService.saveText(new Text(null, "Catorce", null, 2, null));
			textService.saveText(new Text(null, "Quince", null, 2, null));
			textService.saveText(new Text(null, "Dieciseis", null, 2, null));
			textService.saveText(new Text(null, "Diecisiete", null, 2, null));
			textService.saveText(new Text(null, "Dieciocho", null, 2, null));
			textService.saveText(new Text(null, "Diecinueve", null, 2, null));
			textService.saveText(new Text(null, "Veinte", null, 2, null));
		};
	}
}

