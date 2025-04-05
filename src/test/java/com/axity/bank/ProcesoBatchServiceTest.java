package com.axity.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axity.bank.model.Arqueo;
import com.axity.bank.model.ArqueoDescuadrado;
import com.axity.bank.repository.ArqueoDescuadradoRepository;
import com.axity.bank.repository.ArqueoRepository;
import com.axity.bank.repository.SucursalProductoRepository;
import com.axity.bank.service.impl.ProcesoBatchServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProcesoBatchServiceTest {
    @Mock
    private ArqueoRepository arqueoRepository;

    @Mock
    private SucursalProductoRepository sucursalProductoRepository;

    @Mock
    private ArqueoDescuadradoRepository arqueoDescuadradoRepository;

    @InjectMocks
    private ProcesoBatchServiceImpl procesoBatchService;

    @Test
    void testEjecutarProcesoBatch_GuardadoInexistentes() {
        int mes = 1;
        int anio = 2025;
        Arqueo arqueo1 = Arqueo.builder()
                .codigoSucursal(new BigDecimal(103))
                .codigoProducto("PR01")
                .codigoDocumento("DOC001")
                .fechaArqueo(LocalDate.of(2025, 1, 1))
                .saldoFinal(new BigDecimal("1000.00"))
                .diferencia(new BigDecimal(10.00))
                .resultado("D")
                .sucursal("Sucursal F")
                .build();

        Arqueo arqueo2 = Arqueo.builder()
                .codigoSucursal(new BigDecimal(101))
                .codigoProducto("PR02")
                .codigoDocumento("DOC001")
                .fechaArqueo(LocalDate.of(2025, 1, 2))
                .saldoFinal(new BigDecimal("2000.00"))
                .diferencia(new BigDecimal(10.00))
                .resultado("C")
                .sucursal("Sucursal F")
                .build();

        List<Arqueo> arqueos = List.of(arqueo1, arqueo2);

        Mockito.when(arqueoRepository.findByMesAndAnio(mes, anio)).thenReturn(arqueos);
        Mockito.lenient().when(sucursalProductoRepository
                .existsByCodigos(new BigDecimal(102), "PR02", "DOC001"))
                .thenReturn(true);
        Mockito.lenient().when(sucursalProductoRepository
                .existsByCodigos(new BigDecimal(101), "PR01", "DOC001"))
                .thenReturn(true);

        procesoBatchService.ejecutarProcesoBatch(anio, mes);

        ArgumentCaptor<List<ArqueoDescuadrado>> captor = ArgumentCaptor.forClass(List.class);
        verify(arqueoDescuadradoRepository).saveAll(captor.capture());

        List<ArqueoDescuadrado> guardados = captor.getValue();

        guardados.forEach(g -> {
            System.out.println(g.getNombreSucursal());
        });

        System.out.println(captor.getValue());
        assertEquals(2, guardados.size());

        ArqueoDescuadrado saved = guardados.get(0);
        assertEquals(new BigDecimal(103.00), saved.getCodigoSucursal());
        assertEquals("Sucursal F", saved.getNombreSucursal());
        assertEquals("PR01", saved.getCodigoProducto());
        assertEquals("DOC001", saved.getCodigoDocumento());
        assertEquals(LocalDate.of(2025, 1, 1), saved.getFechaArqueo());
        assertEquals(new BigDecimal(10.00), saved.getDiferencia());
    }
}
