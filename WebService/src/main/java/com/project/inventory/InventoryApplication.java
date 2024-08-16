package com.project.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ComponentScan("com.project.inventory.*")
@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(InventoryApplication.class, args);
		System.out.println("InitialLoad Started!!");
		InitialDataLoad initialDataLoad = (InitialDataLoad) context.getBean("InitialDataLoad");
		initialDataLoad.fetchMetaDataQueries();
		initialDataLoad.updateServiceDetails();
	}

}
