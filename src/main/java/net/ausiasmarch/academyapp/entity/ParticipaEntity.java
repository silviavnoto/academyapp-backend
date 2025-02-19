package net.ausiasmarch.academyapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor


@Entity
@Table(name = "participa")
public class ParticipaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioEntity usuario;

    @ManyToOne (fetch = jakarta.persistence.FetchType.EAGER)
    @JoinColumn(name = "id_clase", nullable = false)
    @JsonIgnoreProperties("participas") 
    private ClaseEntity clase;

    
    public ParticipaEntity() {
    }


    public ParticipaEntity(Long id, UsuarioEntity usuario, ClaseEntity clase) {
        this.id = id;
        this.usuario = usuario;
        this.clase = clase;
    }


    public ParticipaEntity(UsuarioEntity usuario, ClaseEntity clase) {
        this.usuario = usuario;
        this.clase = clase;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public UsuarioEntity getUsuario() {
        return usuario;
    }


    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }


    public ClaseEntity getClase() {
        return clase;
    }


    public void setClase(ClaseEntity clase) {
        this.clase = clase;
    }


   /*  public void setParticipa(ParticipaEntity oParticipaEntity) {
     
        throw new UnsupportedOperationException("Unimplemented method 'setParticipa'");
    } */

}
