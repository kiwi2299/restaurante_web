
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "envio", schema = "public")
public class Envio implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnvio;
    private String calle;
    private String numeroCalle;
    private String numeroInterior;
    private String colonia;
    private String codigoPostal;
    private String municipio;
    private String estado;
    private String pais;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "idEnvio",fetch = FetchType.EAGER)
    private Cuenta cuenta;
}
