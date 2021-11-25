package com.example.examplespringboot

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityAssets {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}

@EnableWebSecurity(debug = true)
@Order(1)
class Config(
    val encoder: PasswordEncoder
): WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password(encoder.encode("password"))
            .roles("API_USER")
    }

    override fun configure(http: HttpSecurity) {
        http
            .requestMatchers()
                .antMatchers("/api/**")
                .and()
            .authorizeRequests()
                .anyRequest().hasAnyRole("API_USER")
            .and()
                .httpBasic()
    }
}

@EnableWebSecurity(debug = true)
@Order(2)
class ClosedConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest().denyAll()
    }

}
