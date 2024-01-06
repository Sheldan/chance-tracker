package dev.sheldan.gw2.drr.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class HeaderInterceptorConfig : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiKeyInterceptor())
    }

    @Bean
    fun apiKeyInterceptor(): ApiKeyInterceptor {
        return ApiKeyInterceptor(apiContainer())
    }

    @Bean
    @RequestScope
    fun apiContainer(): ApiKeyContainer {
        return ApiKeyContainer()
    }
}