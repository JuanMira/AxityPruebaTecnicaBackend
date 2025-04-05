package com.axity.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.bank.dto.ProcesoBatchDto;
import com.axity.bank.dto.ProcesoBatchResponse;
import com.axity.bank.service.impl.ProcesoBatchService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/proceso-batch")
public class ProcesoBatchController {

    private final ProcesoBatchService batchService;

    public ProcesoBatchController(ProcesoBatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<ProcesoBatchResponse> ejecutarProceso(
            @RequestBody ProcesoBatchDto body) {
        batchService.ejecutarProcesoBatch(body.getAnio(), body.getMes());
        return ResponseEntity.ok(ProcesoBatchResponse.builder().message("Proceso Batch Ejecutado").build());
    }

}
