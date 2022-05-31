package com.qzhp;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
@MapperScan("com.qzhp.dao")
public class QzhpApplication {

	public static void main(String[] args) {
		SpringApplication.run(QzhpApplication.class, args);
	}

}
