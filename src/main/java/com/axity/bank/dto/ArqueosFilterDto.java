package com.axity.bank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArqueosFilterDto {
    private String sucursal;
    private Integer mes;
    private Integer anio;
    private String producto;
}
