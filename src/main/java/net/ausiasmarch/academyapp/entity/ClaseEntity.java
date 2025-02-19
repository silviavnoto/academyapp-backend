package net.ausiasmarch.academyapp.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "clase")
public class ClaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String asignatura;

    @NotNull
    @Digits(integer = 3, fraction = 2)
    private BigDecimal precio;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    private BigDecimal hora;

    @JsonIgnoreProperties("clase")
    @OneToMany(mappedBy = "clase", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private java.util.List<ParticipaEntity> participas;

    public ClaseEntity() {
    }

    public ClaseEntity(Long id, String asignatura,
            BigDecimal precio, BigDecimal hora) {
        this.id = id;
        this.asignatura = asignatura;
        this.precio = precio;
        this.hora = hora;

    }

    public ClaseEntity(String asignatura, 
            BigDecimal precio, BigDecimal hora) {
        this.asignatura = asignatura;
        this.precio = precio;
        this.hora = hora;
   
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getHora() {
        return hora;
    }

    public void setHora(BigDecimal hora) {
        this.hora = hora;
    }

    public java.util.List<ParticipaEntity> getParticipas() {
        return participas;
    }

    public void setParticipas(java.util.List<ParticipaEntity> participas) {
        this.participas = participas;
    }

 


}
