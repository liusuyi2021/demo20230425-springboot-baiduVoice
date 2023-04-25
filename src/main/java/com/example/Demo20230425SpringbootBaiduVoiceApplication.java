package com.example;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ForestScan("com.example.forest")
public class Demo20230425SpringbootBaiduVoiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo20230425SpringbootBaiduVoiceApplication.class, args);
    }

}
