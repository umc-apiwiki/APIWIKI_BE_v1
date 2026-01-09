package com.umc.apiwiki;

import io.sentry.Sentry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ApiwikiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiwikiBackendApplication.class, args);
	}

}