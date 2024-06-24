package com.filesharingapp.filesharingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.filesharingapp.filesharingapp.security", "com.filesharingapp.filesharingapp.security.jwt.JwtUtils"})
public class FilesharingappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesharingappApplication.class, args);
	}

}
