package com.namja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.namja.collector.SportsMetaDataCollectorServiceImpl;

@SpringBootApplication
public class SportsCollectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsMetaDataCollectorServiceImpl.class);
    }
}
