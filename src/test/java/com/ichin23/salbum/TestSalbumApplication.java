package com.ichin23.salbum;

import org.springframework.boot.SpringApplication;

public class TestSalbumApplication {

	public static void main(String[] args) {
		SpringApplication.from(SalbumApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
