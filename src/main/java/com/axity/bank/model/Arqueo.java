package com.axity.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "arqueos")
public class Arqueo {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_arqueo", nullable = false)
    private LocalDate fechaArqueo;

    @Column(name = "sucursal", length = 100, nullable = false)
    private String sucursal;

    @Column(name = "codigo_sucursal", nullable = false)
    private BigDecimal codigoSucursal;

    @Column(name = "codigo_producto", length = 10, nullable = false)
    private String codigoProducto;

    @Column(name = "codigo_documento", length = 20, nullable = false)
    private String codigoDocumento;

    @Column(name = "diferencia", precision = 15, scale = 2)
    private BigDecimal diferencia;

    @Column(name = "saldo_final", precision = 15, scale = 2)
    private BigDecimal saldoFinal;

    @Column(name = "resultado", length = 1, nullable = false)
    private String resultado;
}
