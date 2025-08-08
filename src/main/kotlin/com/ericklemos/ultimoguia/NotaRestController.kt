package com.ericklemos.ultimoguia

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notas")
class NotaRestController(
    private val objectMapper: ObjectMapper
) {
    val logger: Logger = LoggerFactory.getLogger(NotaRestController::class.java)

    @PostMapping
    fun save(@RequestBody notaDto: NotaDto): ResponseEntity<NotaDto> {
        logger.info(objectMapper.writeValueAsString(notaDto))
        return ResponseEntity.ok(
            NotaDto(
                titulo = "${notaDto.titulo}-cadastrado",
                conteudo = notaDto.conteudo
            )
        )
    }

}