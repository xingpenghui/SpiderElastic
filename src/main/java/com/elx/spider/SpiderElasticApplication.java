package com.elx.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.elx.spider.entity")
public class SpiderElasticApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpiderElasticApplication.class, args);
	}

}
