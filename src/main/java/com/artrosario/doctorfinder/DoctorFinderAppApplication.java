package com.artrosario.doctorfinder;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.artrosario.doctorfinder.response.model.DoctorResponse;
import com.artrosario.doctorfinder.response.model.DoctorRest;
import com.artrosario.doctorfinder.response.model.DoctorsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DoctorFinderAppApplication {
	
    private static final Logger log = LoggerFactory.getLogger(DoctorFinderAppApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(DoctorFinderAppApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {

			//DoctorsResponse doctor = restTemplate.getForObject(
			//		"https://api.betterdoctor.com/2016-03-01/doctors?location=37.773,-122.413,100&limit=10&user_key=CODE_SAMPLES_KEY_9d3608187", DoctorsResponse.class);
		
		//ResponseEntity<String> resp = restTemplate.getForEntity("https://api.betterdoctor.com/2016-03-01/doctors?location=37.773,-122.413,100&limit=10&user_key=CODE_SAMPLES_KEY_9d3608187", String.class);
		//    Gson gson = new GsonBuilder().create();
		 //  DoctorsResponse doctor1 = gson.fromJson(resp.getBody(), DoctorsResponse.class);
			//log.info(doctor.toString());

		};
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

