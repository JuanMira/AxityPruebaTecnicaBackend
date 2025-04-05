package com.axity.bank.service.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.axity.bank.dto.ArqueoDto;

public interface ArqueoDescuadroService {
    Page<ArqueoDto> filtrarDescuadrados(LocalDate fecha, Integer sucursal,String producto, Pageable pageable);    
}
