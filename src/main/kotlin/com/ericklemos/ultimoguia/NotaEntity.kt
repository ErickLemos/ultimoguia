package com.ericklemos.ultimoguia

import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "notas")
class NotaEntity(
    @Id val id: ObjectId?,
    val titulo: String,
    val conteudo: String,
    @CreatedBy val criador: String? = null,
    @LastModifiedBy val modificador: String? = null,
    @CreatedDate val criadoEm: Instant? = null,
    @LastModifiedDate val modificadoEm: Instant? = null
)