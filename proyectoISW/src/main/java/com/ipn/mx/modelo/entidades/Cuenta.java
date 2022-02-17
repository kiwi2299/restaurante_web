
package com.ipn.mx.modelo.entidades;

import java.sql.Date;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cuenta",schema = "public")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuenta;
    private String nombreCliente;
    private float propina;
    private float total;
    private Date fecha;
    @OneToOne
    @JoinColumn(name = "idEnvio",referencedColumnName = "idEnvio")
    private Envio idEnvio;
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idCuenta",fetch = FetchType.EAGER)
    private Collection<Pedido> idPedido;
}
