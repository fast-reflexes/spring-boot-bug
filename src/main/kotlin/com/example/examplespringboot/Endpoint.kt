package com.example.examplespringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleEndpoint {

    @GetMapping(value = ["/api/bogus"])
    fun bogus(): String {
        return "bogus"
    }

}