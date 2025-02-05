package net.ausiasmarch.academyapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "taquilla")
public class TaquillaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Digits(integer = 3, fraction = 0) 
    private Integer numero;

    @NotNull(message = "El campo bloque es obligatorio")
    @Pattern(
        regexp = "^[A-Z]{1,2}$",
        message = "El campo bloque debe contener entre 1 y 2 letras mayúsculas"
    )
    private String bloque;
  
    @NotNull
    private Boolean disponible;
    

    @OneToMany(mappedBy = "taquilla", fetch = FetchType.LAZY)
    private java.util.List<AlquilerEntity> alquileres;


    public TaquillaEntity() {
        this.alquileres = new java.util.ArrayList<>();
    }

    public TaquillaEntity(Integer numero,
            String bloque, Boolean disponible) {
        this.numero = numero;
        this.bloque = bloque;
        this.disponible = disponible;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 
    public String getBloque() {
        return bloque;
    }

    public void setBloque(String bloque) {
        this.bloque = bloque;
    }

    

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public int getAlquileres() {
        return alquileres.size();
    }
    
}
