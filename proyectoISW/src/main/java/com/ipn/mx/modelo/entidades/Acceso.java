
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "acceso",schema = "public")
public class Acceso implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAcceso;
    @OneToOne
    @JoinColumn(name = "idPersonal",referencedColumnName = "idPersonal")
    private Personal idPersonal;
    private String nombreUsuario;
    private String clavePersonal;
}
