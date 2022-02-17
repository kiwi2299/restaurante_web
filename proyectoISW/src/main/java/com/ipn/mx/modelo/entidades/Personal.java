
package com.ipn.mx.modelo.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "personal",schema = "public")
public class Personal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonal;
    private String nombre;
    private String paterno;
    private String materno;
    private Date fechaNacimiento;
    
    @JoinColumn(name = "idPuesto",
            referencedColumnName = "idPuesto")
    @ManyToOne
    private Puesto idPuesto;
    
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "idPersonal",fetch = FetchType.EAGER)
    private Acceso acceso;
    

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idPersonal",fetch = FetchType.EAGER)
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude  
    private Collection<Pedido> listaPedidos;
    

}
    
