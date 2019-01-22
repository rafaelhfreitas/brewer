package com.bigworks.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.bigworks.brewer.service.RegisterBeerService;

@Configuration
@ComponentScan(basePackageClasses= RegisterBeerService.class)
public class ServiceConfig {

}
