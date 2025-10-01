package com.rt.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rt.springboot.app.models.service.IUploadFileService;

import java.util.Locale;

@SpringBootApplication
public class SpringbootDataJpaApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootDataJpaApplication.class);
	}

	@Autowired
	IUploadFileService uploadFileService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(SpringbootDataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		uploadFileService.deleteAll();
//		uploadFileService.init();
	
		// test passwords
		String password = "12345";
		
		for(int i=0 ; i<2 ; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
		
	}

}
