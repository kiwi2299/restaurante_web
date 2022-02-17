
package com.ipn.mx.modelo.entidades;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categoria",schema = "public")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idPlatillo",fetch = FetchType.EAGER)
    @ToString.Exclude  
    private Collection<Platillo> listaPlatillos;
}
