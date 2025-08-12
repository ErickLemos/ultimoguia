package com.ericklemos.ultimoguia

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface NotaRepository: MongoRepository<NotaEntity, ObjectId>