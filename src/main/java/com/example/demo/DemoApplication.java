package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.function.Function;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Autowired
//	private StreamBridge streamBridge;

	@Override
	public void run(String... args) throws Exception, RuntimeException {
		//logging
//		PropertyConfigurator.configure("C:/Users/Thi Cuong/IdeaProjects/demo/src/main/resources/log4j.properties");
//		log.info("test logging");

		//test logging kafka
//		reduceMachineProductMap();
//		int number;
//		while (true) {
//			Random random = new Random();
//			number = random.nextInt(10);
//			streamBridge.send("initialTopic", number);
//		}
	}

	@Bean
	public Function<Integer, String> reduceMachineProductMap() {
		log.info("Received Order and try to reduce product map");
		return (num) -> {
//			Random random = new Random();
//			num = random.nextInt(10);
			log.info("Number: " + num);
			return "Number: " + num;
		};
	}
}
