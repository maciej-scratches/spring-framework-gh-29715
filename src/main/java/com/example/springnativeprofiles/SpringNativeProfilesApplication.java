package com.example.springnativeprofiles;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringNativeProfilesApplication {

    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(SpringNativeProfilesApplication.class);

        if (Arrays.asList(args).contains("serve")) {
            app.setWebApplicationType(WebApplicationType.SERVLET);
        } else {
            app.setWebApplicationType(WebApplicationType.NONE);
        }

        app.run(args);
    }
}

@RestController
class AppController {

    @GetMapping("/")
    String hello() {
        return "hello";
    }
}

@Component
class CliRunner implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CliRunner.class);

    @Override public void run(ApplicationArguments args) {
        if (args.getSourceArgs() == null || !Arrays.asList(args.getSourceArgs()).contains("serve")) {
            LOGGER.info("CLI variant");
        }
    }
}
