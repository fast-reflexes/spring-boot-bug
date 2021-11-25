package com.example.examplespringboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest @Autowired constructor(
    val mvc: MockMvc
) {

    @Test
    fun `access api with correct username password and expect ok`() {
        // when
        mvc.perform(
            MockMvcRequestBuilders
                .get("/api/bogus")
                .with(httpBasic("user", "password"))
        )
            // then
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.OK.value()))
    }

    @Test
    fun `access api with authenticated user with appropriate role and expect ok`() {
        // when
        mvc.perform(
            MockMvcRequestBuilders
                .get("/api/bogus")
                .with(user("any").authorities(listOf(SimpleGrantedAuthority("ROLE_API_USER"))))
        )
            // then
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.OK.value()))
    }

    /*@Test
    fun `access api with authenticated user with appropriate role and expect unauthorized`() {
        // when
        mvc.perform(
            MockMvcRequestBuilders
                .get("/api/bogus")
                .with(user("any").authorities(listOf(SimpleGrantedAuthority("ROLE_API_NONUSER"))))
        )
            // then
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.FORBIDDEN.value()))
    }*/
}