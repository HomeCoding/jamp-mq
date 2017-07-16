package com.jamp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import com.jamp.app.config.RabbitConfiguration;

//ho 1
//ho 2
@SpringBootApplication
@EnableAutoConfiguration
@Import(RabbitConfiguration.class)
public class JampBootApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JampBootApp.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JampBootApp.class);
    }

}
