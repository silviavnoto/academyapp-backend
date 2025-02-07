package net.ausiasmarch.academyapp.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_alumno")
    private AlumnoEntity alumno;

    @ManyToOne(fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
    private ProfesorEntity profesor;

    public ClaseEntity() {
    }

    public ClaseEntity(Long id, String asignatura,
            BigDecimal precio, BigDecimal hora,
            AlumnoEntity id_alumno, ProfesorEntity id_profesor) {
        this.id = id;
        this.asignatura = asignatura;
        this.precio = precio;
        this.hora = hora;
        this.alumno = id_alumno;
        this.profesor = id_profesor;
    }

    public ClaseEntity(String asignatura, 
            BigDecimal precio, BigDecimal hora,
            AlumnoEntity id_alumno, ProfesorEntity id_profesor) {
        this.asignatura = asignatura;
        this.precio = precio;
        this.hora = hora;
        this.alumno = id_alumno;
        this.profesor = id_profesor;
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

    public AlumnoEntity getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoEntity alumno) {
        this.alumno = alumno;
    }

    public ProfesorEntity getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorEntity profesor) {
        this.profesor = profesor;
    }

}
