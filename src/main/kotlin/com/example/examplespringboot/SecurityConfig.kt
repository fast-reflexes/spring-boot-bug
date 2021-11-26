package com.example.examplespringboot

import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@EnableWebSecurity(debug = true)
@Order(0)
class Config: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .requestMatchers()
            .antMatchers("/error/**", "/test")
            .and()
            .authorizeRequests()
            .anyRequest().permitAll()
    }
}

@EnableWebSecurity(debug = true)
@Order(1)
class ClosedConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .requestMatchers()
                .antMatchers("/non-existing")
                .and()
            .authorizeRequests()
                .anyRequest().hasRole("PRIVILEGED_USER")
    }
}

@EnableWebSecurity(debug = true)
@Order(2)
class CatchAllConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().denyAll()
    }

}
