package com.axity.bank;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axity.bank.model.ArqueoDescuadrado;
import com.axity.bank.repository.ArqueoDescuadradoRepository;

@ExtendWith(MockitoExtension.class)
public class ArqueoDescuadreServiceTest {
    @Mock
    private ArqueoDescuadradoRepository arqueoDescuadradoRepository;

    @Test
    void testFiltrarDescuadrados_Filtros(){
        String fecha = "2025-01-01";
        Integer sucural = 104;

        ArqueoDescuadrado arqueoDescuadrado = ArqueoDescuadrado.builder()
            .anioProceso(2025)
            .codigoDocumento("DOC001")
            .codigoProducto("PR02")
            .codigoSucursal(new BigDecimal(104))
            .consecutivo(5)
            .diferencia(new BigDecimal(0))
            .fechaArqueo(LocalDate.of(2025, 1, 1))
            .fechaRegistro(LocalDate.of(2025, 4, 5))
            .mesProceso(1)
            .nombreSucursal("Sucursal F")
            .resultado("D")
            .saldoFisico(new BigDecimal(0))
            .build();

        List<ArqueoDescuadrado> listArcDesc = List.of(arqueoDescuadrado);

        Mockito.lenient()
            .when(arqueoDescuadradoRepository.findArqueos(fecha, sucural, null, null))
            .thenReturn(listArcDesc);
    }
}
