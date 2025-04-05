package com.axity.bank.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axity.bank.model.ArqueoDescuadrado;

public interface ArqueoDescuadradoRepository extends JpaRepository<ArqueoDescuadrado, Long>{   

    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM ArqueoDescuadrado r
            WHERE r.codigoSucursal = :sucursal AND
                  r.codigoProducto = :producto AND
                  r.codigoDocumento = :documento AND
                  r.fechaArqueo = :fecha
            """)
    boolean existsByCamposUnicos(
        @Param("sucursal") Integer sucursal,
        @Param("producto") String producto,
        @Param("documento") String documento,
        @Param("fecha") LocalDate fecha
    );     

    
    @Query("SELECT a FROM ArqueoDescuadrado a WHERE " +
       "a.resultado = :resultado " +
       "AND (CAST(:fecha AS LocalDate) IS NULL OR a.fechaArqueo = :fecha) " +
       "AND (CAST(:sucursal AS integer) IS NULL OR a.codigoSucursal = :sucursal) " +
       "AND (CAST(:producto AS string) IS NULL OR a.codigoProducto = :producto)")
    List<ArqueoDescuadrado> findArqueos(
        @Param("resultado") String resultado, 
        @Param("sucursal") Integer codigoSucursal, 
        @Param("fecha") LocalDate fecha, 
        @Param("producto") String producto);
}
