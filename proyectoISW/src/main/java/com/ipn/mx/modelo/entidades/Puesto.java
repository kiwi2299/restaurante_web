
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "puesto",schema = "public")
public class Puesto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPuesto;
    private String nombrePuesto;
    private String descripcionPuesto;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idPuesto")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude   
    private Collection<Personal> listaPersonal = new ArrayList<>();
    
    
}
