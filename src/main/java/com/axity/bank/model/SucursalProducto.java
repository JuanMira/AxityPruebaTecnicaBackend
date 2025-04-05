package com.axity.bank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sucursal_producto")
@IdClass(SucursalProducto.class)
public class SucursalProducto {
    
    @Id
    @Column(name = "codigo_sucursal", nullable = false)
    private Integer codigoSucursal;
    
    // operacion
    @Id
    @Column(name = "codigo_operacion", nullable = false)
    private String codigoOperacion;
    

    //documento 
    @Id
    @Column(name = "codigo_producto", nullable = false)
    private String codigoProducto;
    
}
