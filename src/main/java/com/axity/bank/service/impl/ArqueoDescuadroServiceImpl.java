package com.axity.bank.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.axity.bank.dto.ArqueoDto;
import com.axity.bank.model.ArqueoDescuadrado;
import com.axity.bank.repository.ArqueoDescuadradoRepository;
import com.axity.bank.util.ArqueoMapper;

@Service
public class ArqueoDescuadroServiceImpl implements ArqueoDescuadroService{

    @Autowired
    private ArqueoDescuadradoRepository arqueoDescuadradoRepository;

    @Override
    public Page<ArqueoDto> filtrarDescuadrados(LocalDate fecha, Integer sucursal, String producto, Pageable pageable) {
        List<ArqueoDescuadrado> arqueos = arqueoDescuadradoRepository.findArqueos("D", sucursal,fecha,producto);        

        List<ArqueoDto> contenidoPaginado = arqueos
            .stream()
            .map(ArqueoMapper::toDto)            
            .collect(Collectors.toList());

        return new PageImpl<>(contenidoPaginado, pageable, arqueos.size());
    }

}
