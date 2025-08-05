package com.ericklemos.ultimoguia

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notas")
class RestController(
    private val notaRepository: NotaRepository
) {

    @PostMapping
    fun save(@RequestBody notaDto: NotaDto): ResponseEntity<NotaDto> {
        val nota = Nota(
            titulo = notaDto.titulo,
            conteudo = notaDto.conteudo
        )
        val notaSalva = notaRepository.save(nota)
        return ResponseEntity.ok(NotaDto(
            titulo = notaSalva.titulo,
            conteudo = notaSalva.conteudo
        ))
    }

}