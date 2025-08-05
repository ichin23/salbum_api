package com.ichin23.salbum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SalbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalbumApplication.class, args);
	}

}
