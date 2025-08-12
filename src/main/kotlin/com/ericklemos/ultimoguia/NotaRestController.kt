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
    private val notaRepository: NotaRepository,
    private val auditEventRepository: AuditEventRepository
) {
    val logger: Logger = LoggerFactory.getLogger(NotaRestController::class.java)

    @PostMapping
    fun save(@RequestBody notaDto: NotaDto): ResponseEntity<NotaDto> {
        logger.info(objectMapper.writeValueAsString(notaDto))
        MDC.put("request-id", notaDto.titulo)

        val notaEntity = notaRepository.save(notaDto.toEntity())

        meterRegistry.counter("notas_salvas").increment()
        auditEventRepository.add(AuditEventRepositoryImpl.NotaAuditEvent("NOTA_SALVA"))
        return ResponseEntity.ok(notaEntity.toDto())
    }

}