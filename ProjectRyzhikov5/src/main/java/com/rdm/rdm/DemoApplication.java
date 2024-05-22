package com.rdm.rdm;

import Interfaces.Animal;
import Interfaces.CreateAnimalService;
import com.rdm.rdm.implementations.CreateAnimalServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			testMain(ctx);
		};
	}

	private static void testMain(ApplicationContext ctx) throws IOException {
		System.out.println("----- Тестовый вывод -----");
		CreateAnimalService createAnimalService = ctx.getBean(CreateAnimalServiceImpl.class);
		createAnimalService.createAnimals(5);
		/*Map<String, List<Animal>> mapAnimals = createAnimalService.createAnimalsMap(5);
		System.out.println(mapAnimals.keySet().toString());*/
	}

}
