package com.example.kinoxp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // This annotation is used to enable the web security configuration in the Spring Security framework.
public class SecurityConfig implements WebSecurityConfigurer<WebSecurity> { // implementerer WebSecurityConfigurer interfacet

    @Override
    public void init(WebSecurity builder) throws Exception { //This method is called during the initialization of the WebSecurity object.
        // It can be used to perform any necessary initialization tasks for web security.
    }

    @Override
    public void configure(WebSecurity web) throws Exception { //
        web.ignoring()
                .anyRequest();
    }       // This method is used to configure specific aspects of web security. In this case, the web.ignoring().anyRequest();
    // configuration allows the application to ignore any request without applying any security restrictions.

}
