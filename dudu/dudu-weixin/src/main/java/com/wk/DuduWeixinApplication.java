package com.wk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"com.wk.mapper"})
public class DuduWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuduWeixinApplication.class, args);
    }

}
