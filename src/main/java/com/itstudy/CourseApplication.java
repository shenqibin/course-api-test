package com.itstudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan(basePackages = { "com.itstudy.dao" })

public class CourseApplication {

	public static void main(String[] args) {

		SpringApplication.run(CourseApplication.class, args);

	}

}
