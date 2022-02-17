
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "platillo",schema = "public")
public class Platillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlatillo;
    private String nombrePlatillo;
    private String descripcionPlatillo;
    private float precioPlatillo;
    
    @JoinColumn(name = "idCategoria",
            referencedColumnName = "idCategoria")
    @ManyToOne
    private Categoria idCategoria;
    @LazyCollection(LazyCollectionOption.FALSE)
    
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "listaPlatillosPedido")
    private Collection<Pedido> listaPedidos;
}
