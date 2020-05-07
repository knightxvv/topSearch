package com.tx.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.tx.*"})
@MapperScan(basePackages = {"com.tx.mybatis.mapper;"})
public class TopSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopSearchApplication.class, args);
	}
	
	

}
