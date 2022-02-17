
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Categoria;

public class CategoriaDTO {
    private Categoria entidad;
    public CategoriaDTO() {
        this.entidad = new Categoria();
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID: ").append(getEntidad().getIdCategoria());
        sb.append("Nombre: ").append(getEntidad().getNombreCategoria());
        sb.append("Descripcion: ").append(getEntidad().getDescripcionCategoria());
        sb.append('}');
        return sb.toString();
    }
    
    
}
