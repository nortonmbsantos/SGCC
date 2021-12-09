package br.edu.utfpr.sgcc;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//
//    @PostConstruct
//    public void init(){
//    	try {
//    		TimeZone.setDefault(TimeZone.getTimeZone("GMT-3:00"));   // It will set UTC timezone
//    		System.out.println("Spring boot application running in timezone :" + new Date());   // It will print UTC timezone
//			
//		} catch (Exception e) {
//			System.out.println("Could not load Spring boot application running in UTC timezone :" + new Date());   // It will print UTC timezone
//			
//		}
//    }
//	
 public static void main(String[] args) {
  SpringApplication.run(Application.class, args);
 }
}
