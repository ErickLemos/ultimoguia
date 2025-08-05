package com.ericklemos.ultimoguia

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class NotaDto(

    @field:Min(1)
    @field:Max(20)
    val titulo: String,

    @field:Min(10)
    @field:Max(100)
    val conteudo: String

)