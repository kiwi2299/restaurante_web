
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Personal;

public class PersonalDTO {
    private Personal entidad;

    public PersonalDTO() {
        this.entidad = new Personal();
    }

    public Personal getEntidad() {
        return entidad;
    }

    public void setEntidad(Personal entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID: ").append(getEntidad().getIdPersonal());
        sb.append("Nombre: ").append(getEntidad().getNombre());
        sb.append("Paterno: ").append(getEntidad().getPaterno());
        sb.append("Materno: ").append(getEntidad().getMaterno());
        sb.append("Fecha Nacimiento: ").append(getEntidad().getFechaNacimiento());
        sb.append("Nombre Puesto: ").append(getEntidad().getIdPuesto().getNombrePuesto());
        sb.append('}');
        return sb.toString();
    }
    
    
}
