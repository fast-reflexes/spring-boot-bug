package com.example.examplespringboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleEndpoint {

    @GetMapping(value = ["/test"])
    fun test(): String =
        "test"

}