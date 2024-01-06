package dev.sheldan.gw2.drr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class])
class ChanceTrackerApplication

fun main(args: Array<String>) {
	runApplication<ChanceTrackerApplication>(*args)
}
