package com.ericklemos.ultimoguia

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NotaRepository: MongoRepository<Nota, ObjectId> {
}