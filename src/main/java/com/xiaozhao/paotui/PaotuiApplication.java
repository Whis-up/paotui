package com.xiaozhao.paotui;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.xiaozhao.paotui.intf.mapper"})
public class PaotuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaotuiApplication.class, args);
    }

}
