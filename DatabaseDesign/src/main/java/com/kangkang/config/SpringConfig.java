package com.kangkang.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.kangkang")
@PropertySource("classpath:jdbc.properties")
@Import({JDBCConfig.class,MybatisConfig.class})
public class SpringConfig {
}
