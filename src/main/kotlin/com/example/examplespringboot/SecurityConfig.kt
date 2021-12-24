package com.example.examplespringboot

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity(debug = true)
class Config: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/error/**").hasIpAddress("127.0.0.1")
            .antMatchers("/non-existing/**").denyAll()
            .antMatchers("/test/**", "/favicon.ico").permitAll()
            .anyRequest().denyAll()
    }
}
