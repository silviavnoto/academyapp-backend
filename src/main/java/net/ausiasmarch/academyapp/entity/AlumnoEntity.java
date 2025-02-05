package net.ausiasmarch.academyapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "alumno")
public class AlumnoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 255)
    private String nombre;

    @NotNull
    @Size(min = 3, max = 255)
    private String apellido1;

    @Size(min = 0, max = 255)
    private String apellido2;

    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "^[967]\\d{8}$", message = "El número debe tener 9 dígitos y comenzar con 9, 6 o 7")    @Pattern(regexp = "^[679]\\d{8}$", message = "El número de teléfono debe contener 9 dígitos y comenzar con 6, 7 o 9")
    private String telefono;
    

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private java.util.List<ClaseEntity> clases;

    @OneToMany(mappedBy = "alumno", fetch = FetchType.LAZY)
    private java.util.List<AlquilerEntity> alquileres;


    public AlumnoEntity() {
        this.clases = new java.util.ArrayList<>();
        this.alquileres = new java.util.ArrayList<>();
    }


    public AlumnoEntity(String nombre, String apellido1, String apellido2, String email, String telefono) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.telefono = telefono;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido1() {
        return apellido1;
    }


    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }


    public String getApellido2() {
        return apellido2;
    }


    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public int getClases() {
        return clases.size();
    }

    public int getAlquileres() {
        return alquileres.size();
    }
    
}
