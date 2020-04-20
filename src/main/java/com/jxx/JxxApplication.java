package com.jxx;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@SpringBootApplication()
@MapperScan(basePackages = {"com.jxx.crawler.mapper","com.jxx.mapper"})
public class JxxApplication {
    public static void main(String[] args) {
        SpringApplication.run(JxxApplication.class, args);
    }

}

