package com.axity.bank.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.bank.dto.ArqueoDto;
import com.axity.bank.service.impl.ArqueoDescuadroService;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/arqueos")
public class ArqueoController {

    private final ArqueoDescuadroService arqueoDescService;

    public ArqueoController(ArqueoDescuadroService arqueoDescService){
        this.arqueoDescService = arqueoDescService;
    }

    @GetMapping("/descuadrados")
    public ResponseEntity<Page<ArqueoDto>> getMethodName(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
        @RequestParam(required = false) Integer sucursal,
        @RequestParam(required = false) String producto, 
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ArqueoDto> result = arqueoDescService.filtrarDescuadrados(fecha, sucursal, producto, pageable);
        return ResponseEntity.ok(result);
    }
    
}
