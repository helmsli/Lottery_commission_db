package com.xinwei.lotteryDb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan ("com.xinwei.lotteryDb")
@MapperScan ("com.xinwei.lotteryDb.mapper")
@ImportResource ({ "classpath:hessian/hessian-client.xml", "classpath:hessian/hessian-server.xml" })
public class LotteryCommissionDbApplication {

	public static void main(String[] args) {
		try {
		SpringApplication.run(LotteryCommissionDbApplication.class, args);
		}
		catch(Throwable e)
		{
		   e.printStackTrace();	
		}
	}
}
