package org.example.panacheauth.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //number of rounds passed
        return new BCryptPasswordEncoder(16);
    }
}