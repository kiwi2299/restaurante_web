
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Pedido;

public class PedidoDTO {
    private Pedido entidad;

    public PedidoDTO( ) {
        this.entidad = new Pedido();
    }

    public Pedido getEntidad() {
        return entidad;
    }

    public void setEntidad(Pedido entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID:").append(getEntidad().getIdPedido());
        sb.append("Id Personal:").append(getEntidad().getIdPersonal());
        sb.append("Id Cuenta:").append(getEntidad().getIdCuenta());
        sb.append("Numero Mesa:").append(getEntidad().getNumeroMesa());
        sb.append("Platillos:").append(getEntidad().getListaPlatillosPedido());
        sb.append('}');
        return sb.toString();
    }
    
    
}
