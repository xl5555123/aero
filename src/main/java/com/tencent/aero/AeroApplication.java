package com.tencent.aero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot的入口程序
 * @author larryxing
 */
@SpringBootApplication
@EnableAutoConfiguration
public class AeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeroApplication.class, args);
	}
}
