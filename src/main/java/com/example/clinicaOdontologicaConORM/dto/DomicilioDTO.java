package com.example.clinicaOdontologicaConORM.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DomicilioDTO {
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
}
