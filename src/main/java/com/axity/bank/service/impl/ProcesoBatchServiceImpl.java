package com.axity.bank.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axity.bank.model.Arqueo;
import com.axity.bank.model.ArqueoDescuadrado;
import com.axity.bank.repository.ArqueoDescuadradoRepository;
import com.axity.bank.repository.ArqueoRepository;
import com.axity.bank.repository.SucursalProductoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProcesoBatchServiceImpl implements ProcesoBatchService {

    @Autowired
    private final ArqueoRepository arqueoRepository;

    @Autowired
    private final SucursalProductoRepository sucursalProductoRepository;

    @Autowired
    private final ArqueoDescuadradoRepository arqueoDescuadradoRepository;

    @Override
    public void ejecutarProcesoBatch(int ano, int mes) {
        List<Arqueo> arqueos = arqueoRepository.findByMesAndAnio(mes, ano);
        List<ArqueoDescuadrado> descuadrados = new ArrayList<>();

        for (Arqueo arqueo : arqueos) {
            boolean exist = sucursalProductoRepository
                    .existsByCodigos(arqueo.getCodigoSucursal(), arqueo.getCodigoProducto(),
                            arqueo.getCodigoDocumento());

            if (!exist) {
                var arqDescuadre = ArqueoDescuadrado
                        .builder()
                        .anioProceso(ano)
                        .mesProceso(mes)
                        .consecutivo(LocalDate.now().getDayOfMonth())
                        .codigoSucursal(arqueo.getCodigoSucursal())
                        .nombreSucursal(arqueo.getSucursal())
                        .codigoProducto(arqueo.getCodigoProducto())
                        .codigoDocumento(arqueo.getCodigoDocumento())
                        .fechaArqueo(arqueo.getFechaArqueo())
                        .diferencia(arqueo.getDiferencia())
                        .saldoFisico(arqueo.getSaldoFinal())
                        .resultado(arqueo.getResultado())
                        .fechaRegistro(LocalDate.now())
                        .build();
                descuadrados.add(arqDescuadre);
            }            
        }

        arqueoDescuadradoRepository.saveAll(descuadrados);
    }
}
