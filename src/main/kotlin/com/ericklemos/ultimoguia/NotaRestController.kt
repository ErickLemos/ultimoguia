package com.ericklemos.ultimoguia

import com.ericklemos.ultimoguia.audit.AuditEventRepositoryImpl
import com.fasterxml.jackson.databind.ObjectMapper
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.boot.actuate.audit.AuditEventRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notas")
class NotaRestController(
    private val objectMapper: ObjectMapper,
    private val meterRegistry: MeterRegistry,
    private val auditEventRepository: AuditEventRepository
) {
    val logger: Logger = LoggerFactory.getLogger(NotaRestController::class.java)

    @PostMapping
    fun save(@RequestBody notaDto: NotaDto): ResponseEntity<NotaDto> {
        MDC.put("request-id", notaDto.titulo)

        // Observability
        logger.info(objectMapper.writeValueAsString(notaDto))
        auditEventRepository.add(AuditEventRepositoryImpl.NotaAuditEvent("NOTA_SALVA"))
        meterRegistry.counter("notas_salvas").increment()

        return ResponseEntity.ok(
            NotaDto(
                titulo = "${notaDto.titulo}-cadastrado",
                conteudo = notaDto.conteudo
            )
        )
    }

}