
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Envio;

public class EnvioDTO {
    private Envio entidad;

    public EnvioDTO( ) {
        this.entidad = new Envio();
    }

    public Envio getEntidad() {
        return entidad;
    }

    public void setEntidad(Envio entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID: ").append(getEntidad().getIdEnvio());
        sb.append("Calle: ").append(getEntidad().getCalle());
        sb.append("Numero: ").append(getEntidad().getNumeroCalle());
        sb.append("Numero interior: ").append(getEntidad().getNumeroInterior());
        sb.append("Colonia: ").append(getEntidad().getColonia());
        sb.append("Codigo Postal: ").append(getEntidad().getCodigoPostal());
        sb.append("Municipio: ").append(getEntidad().getMunicipio());
        sb.append("Estado: ").append(getEntidad().getEstado());
        sb.append("Pais: ").append(getEntidad().getPais());
        sb.append('}');
        return sb.toString();
    }
    
    
}
