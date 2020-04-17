package com.river.iclassroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
@SpringBootApplication
@ServletComponentScan
public class IClassroomApplication {
	public static void main(String[] args) {
		SpringApplication.run(IClassroomApplication.class, args);
	}
}