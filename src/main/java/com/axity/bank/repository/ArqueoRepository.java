package com.axity.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axity.bank.model.Arqueo;

public interface ArqueoRepository extends JpaRepository<Arqueo, Long> {
    @Query("SELECT a FROM Arqueo a WHERE YEAR(a.fechaArqueo) = :anio AND MONTH(a.fechaArqueo) = :mes AND a.resultado = 'D'")
    List<Arqueo> findByMesAndAnio( @Param("mes") int mes, @Param("anio") int anio);
}
