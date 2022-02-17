
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Platillo;
public class PlatilloDTO {
    private Platillo entidad;

    public PlatilloDTO( ) {
        this.entidad = new Platillo();
    }

    public Platillo getEntidad() {
        return entidad;
    }

    public void setEntidad(Platillo entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID: ").append(getEntidad().getIdPlatillo());
        sb.append("Nombre: ").append(getEntidad().getNombrePlatillo());
        sb.append("Descripcion: ").append(getEntidad().getDescripcionPlatillo());
        sb.append("Precio: ").append(getEntidad().getPrecioPlatillo());
        sb.append("Categoria: ").append(getEntidad().getIdCategoria());
        sb.append("Pedidos: ").append(getEntidad().getListaPedidos());
        sb.append('}');
        return sb.toString();
    }
    
    
}
