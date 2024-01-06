package dev.sheldan.gw2.drr.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Health {
    @GetMapping("/health-check")
    fun healCheck(): String {
        return "yes"
    }
}