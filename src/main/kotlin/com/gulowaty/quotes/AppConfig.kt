package com.gulowaty.quotes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun useCaseFactory(): UseCaseFactory {
        return UseCaseFactory()
    }

}
