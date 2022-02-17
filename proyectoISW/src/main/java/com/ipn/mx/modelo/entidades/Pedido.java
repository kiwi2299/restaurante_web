
package com.ipn.mx.modelo.entidades;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "pedido",schema = "public")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;
    
    @JoinColumn(name = "idPersonal",
            referencedColumnName = "idPersonal")
    @ManyToOne
    private Personal idPersonal;
    
    @ManyToOne
    @JoinColumn(name = "idCuenta",referencedColumnName = "idCuenta")
    private Cuenta idCuenta;
    private int numeroMesa;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
        name = "platillopedido", 
        joinColumns = { @JoinColumn(name = "idPedido") }, 
        inverseJoinColumns = { @JoinColumn(name = "idPlatillo") }
    )
    @ManyToMany(cascade =  CascadeType.ALL)
    private Collection<Platillo> listaPlatillosPedido;
    
}
