package com.ericklemos.ultimoguia.audit

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.audit.AuditEvent
import org.springframework.boot.actuate.audit.AuditEventRepository
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class AuditEventRepositoryImpl : AuditEventRepository {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(this::class.java)
    }
    class NotaAuditEvent(type: String) : AuditEvent("", type, mapOf<String, Any>())

    override fun add(event: AuditEvent) {
        log.info("Adding audit event: $event")
    }

    override fun find(
        principal: String?,
        after: Instant?,
        type: String?
    ): List<AuditEvent?>? {
        log.info("Finding audit events for principal: $principal, after: $after, type: $type")
        return listOf(NotaAuditEvent(type ?: "NOTA_SALVA"))
    }
}