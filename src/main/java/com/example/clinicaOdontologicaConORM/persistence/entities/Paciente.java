package com.example.clinicaOdontologicaConORM.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="pacientes")
@Getter
@Setter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaIngreso;


    //Relacion de UN Paciente tienen UN Domicilio
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="domicilio_id", referencedColumnName = "id",nullable = false)
    private Domicilio domicilio;


    //Relacion de UN paciente tiene MUCHOS turnos
    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> turnos;


    // Constructores
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
    }

    public Paciente() {
    }




}
