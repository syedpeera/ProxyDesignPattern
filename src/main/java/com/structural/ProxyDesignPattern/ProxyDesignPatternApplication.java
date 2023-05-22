package com.structural.ProxyDesignPattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyDesignPatternApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyDesignPatternApplication.class, args);
		Image image1 = new ProxyImage("car.png");
		Image image2 = new ProxyImage("bike.png");
		//Image will be loaded from disk and displayed whenever requested
		image1.display();
		image2.display();
		//Image is already loaded so it won't load again
		image2.display();
	}
}
