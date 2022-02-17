
package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Acceso;

public class AccesoDTO {
    private Acceso entidad;

    public AccesoDTO() {
        this.entidad = new Acceso();
    }

    public Acceso getEntidad() {
        return entidad;
    }

    public void setEntidad(Acceso entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{IdEntidad").append(getEntidad().getIdAcceso());
        sb.append("IdPersonal").append(getEntidad().getIdPersonal().getIdPersonal());
        sb.append("NombreUsuario").append(getEntidad().getNombreUsuario());
        sb.append("ClaveUsuario").append(getEntidad().getClavePersonal());
        sb.append('}');
        return sb.toString();
    }
    
}
