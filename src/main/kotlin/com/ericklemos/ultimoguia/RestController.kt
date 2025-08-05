package com.ericklemos.ultimoguia

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notas")
class RestController {

    @PostMapping
    fun save(@RequestBody notaDto: NotaDto): ResponseEntity<NotaDto> {
        return ResponseEntity.ok(
            NotaDto(
                titulo = "${notaDto.titulo}-cadastrado",
                conteudo = notaDto.conteudo
            )
        )
    }

}