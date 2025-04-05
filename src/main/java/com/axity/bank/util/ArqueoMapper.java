package com.axity.bank.util;

import com.axity.bank.dto.ArqueoDto;
import com.axity.bank.model.ArqueoDescuadrado;

public class ArqueoMapper {
    public static ArqueoDto toDto(ArqueoDescuadrado arqueo){
        return ArqueoDto.builder()
            .fechaArqueo(arqueo.getFechaArqueo())
            .codigoSucursal(arqueo.getCodigoSucursal())
            .sucursal(arqueo.getNombreSucursal())
            .codigoProducto(arqueo.getCodigoProducto())
            .diferencia(arqueo.getDiferencia())
            .saldoFinal(arqueo.getSaldoFisico())
            .resultado(arqueo.getResultado())
            .build();
    }
}
