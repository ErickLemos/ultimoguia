package com.ericklemos.ultimoguia

fun NotaDto.toEntity() = NotaEntity(
    id = null,
    titulo = titulo,
    conteudo = conteudo
)

fun NotaEntity.toDto() = NotaDto(
    titulo = titulo,
    conteudo = conteudo
)