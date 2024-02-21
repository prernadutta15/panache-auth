package org.example.panacheauth.configs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /**
         * This commented part means allow everything, don't put restrictions on anything
         */
//        http.authorizeHttpRequests((requests) ->
//                        requests.anyRequest().permitAll()
//        );

        /**
         * This commented part shows the default config as enabled by spring security
         */
//        http.authorizeHttpRequests((requests) ->
//                        requests.anyRequest().authenticated()
//        );

        /**
         * Spring security creates problem with POST for which disable cors and csrf as below
         */
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .anyRequest().permitAll()
                                        .and().cors().disable()
                                        .csrf().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }
}