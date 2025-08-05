package com.ericklemos.ultimoguia

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "notas")
data class Nota(
    @Id val id: ObjectId? = null,
    @Field("titulo") val titulo: String,
    @Field("conteudo") val conteudo: String
)
