package com.example.clinicaOdontologicaConORM.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class TurnoDTO {
    private Integer id;
    private LocalDate fecha;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
}
