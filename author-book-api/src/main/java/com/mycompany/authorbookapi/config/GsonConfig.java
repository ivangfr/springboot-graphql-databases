package com.mycompany.authorbookapi.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    Gson gson() {
        return new Gson();
    }
}
