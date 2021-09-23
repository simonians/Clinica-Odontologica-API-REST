package com.example.clinicaOdontologicaConORM.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "domicilios")
@Getter @Setter
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "domicilio_sequence")
    @SequenceGenerator(name = "domicilio_sequence", sequenceName = "domicilio_sequence", allocationSize = 1)
    private Integer id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;

    @OneToOne(mappedBy = "domicilio", fetch = FetchType.LAZY)
    @JsonIgnore
    private Paciente paciente;

    public Domicilio() {
    }

    public Domicilio(String calle, String numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

}
