package com.axity.bank.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axity.bank.model.SucursalProducto;

public interface SucursalProductoRepository extends JpaRepository<SucursalProducto, SucursalProducto> {

    @Query("SELECT CASE WHEN COUNT(sp) > 0 THEN true ELSE false END FROM SucursalProducto sp "
        + "WHERE sp.codigoSucursal = :codigoSucursal " +
          "AND sp.codigoProducto = :codigoProducto " +
          "AND sp.codigoOperacion = :codigoOperacion")
    boolean existsByCodigos(@Param("codigoSucursal") BigDecimal codigoSucursal,
            @Param("codigoProducto") String codigoProducto, @Param("codigoOperacion") String codigoOperacion);
}
