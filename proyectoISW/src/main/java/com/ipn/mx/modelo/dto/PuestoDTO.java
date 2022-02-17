
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Puesto;

public class PuestoDTO {
    private Puesto entidad;

    public PuestoDTO( ) {
        this.entidad = new Puesto();
    }

    public Puesto getEntidad() {
        return entidad;
    }

    public void setEntidad(Puesto entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID:").append(getEntidad().getIdPuesto());
        sb.append("Nombre:").append(getEntidad().getNombrePuesto());
        sb.append("Descripcion:").append(getEntidad().getDescripcionPuesto());
        sb.append('}');
        return sb.toString();
    }
    
}
