package com.axity.bank.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArqueoDto {
    private LocalDate fechaArqueo;
    private BigDecimal codigoSucursal;
    private String sucursal;
    private String codigoProducto;
    private BigDecimal diferencia;
    private BigDecimal saldoFinal;
    private String resultado;
}
