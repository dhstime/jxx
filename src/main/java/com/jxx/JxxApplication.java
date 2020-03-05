package com.jxx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.jxx.mapper")
public class JxxApplication {
    public static void main(String[] args) {
        SpringApplication.run(JxxApplication.class, args);
    }

}

