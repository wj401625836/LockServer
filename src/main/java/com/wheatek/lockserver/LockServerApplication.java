package com.wheatek.lockserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wheatek.lockserver.mapper")
public class LockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockServerApplication.class, args);
    }
}
