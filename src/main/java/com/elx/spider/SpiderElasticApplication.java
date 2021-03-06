package com.elx.spider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.elx.spider.entity")
@EnableScheduling //开启定时任务
public class SpiderElasticApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpiderElasticApplication.class, args);
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpiderElasticApplication.class);
	}
}
