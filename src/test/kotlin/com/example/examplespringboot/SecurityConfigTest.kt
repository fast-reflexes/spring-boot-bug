package com.example.examplespringboot

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest @Autowired constructor(
    val mvc: MockMvc
) {

    @Test
    fun `error page ruins mockmvc test`() {
        // when
        mvc.perform(
            MockMvcRequestBuilders
                .get("/test")
        )
            // then
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.OK.value()))
    }

}