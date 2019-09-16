package com.example.aop;

import com.example.aop.aspect.MyAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.aop.service.InformationService;

@SpringBootApplication
public class AopDemoApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(AopDemoApplication.class);
	public static void main(String[] args){

		ApplicationContext context = SpringApplication.run(AopDemoApplication.class, args);

		InformationService informationService = (InformationService) context.getBean(InformationService.class);

		String information = informationService.displayInformation();
		System.out.println(information);

		String returnParam = informationService.getReturnValue();
		System.out.println(returnParam);
		System.out.println();

		try {
			informationService.throwException();
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}


}
