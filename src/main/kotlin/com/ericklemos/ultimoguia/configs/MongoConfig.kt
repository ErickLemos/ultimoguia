package com.ericklemos.ultimoguia.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import java.util.*


@Configuration
@EnableMongoAuditing
class MongoConfig {

    @Bean
    fun myAuditorProvider(): AuditorAware<String> = AuditorAware<String> {
        Optional.ofNullable(SecurityContextHolder.getContext().authentication)
            .map { it.principal }
            .map { it as Jwt }
            .map { it.subject }
    }

}