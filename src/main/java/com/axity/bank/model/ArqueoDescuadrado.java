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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arqueo_descuadrado")
public class ArqueoDescuadrado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "anio_proceso", nullable = false)
    private Integer anioProceso;

    @Column(name = "mes_proceso", nullable = false)
    private Integer mesProceso;

    @Column(name = "consecutivo", nullable = false)
    private Integer consecutivo;

    @Column(name = "nombre_sucursal", nullable = false, length = 15)
    private String nombreSucursal;

    @Column(name = "codigo_sucursal", nullable = false)
    private BigDecimal codigoSucursal;

    @Column(name = "codigo_producto", nullable = false)
    private String codigoProducto;

    @Column(name = "codigo_documento", nullable = false)
    private String codigoDocumento;

    @Column(name = "fecha_arqueo", nullable = false)
    private LocalDate fechaArqueo;

    @Column(name = "diferencia", nullable = false)
    private BigDecimal diferencia;

    @Column(name = "saldo_fisico", nullable = false)
    private BigDecimal saldoFisico;

    @Column(name = "resultado", nullable = false, length = 2)
    private String resultado;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;    
}
