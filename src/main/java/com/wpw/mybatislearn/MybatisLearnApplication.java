package com.wpw.mybatislearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MybatisLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisLearnApplication.class, args);
    }

}
