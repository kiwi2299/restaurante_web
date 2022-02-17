
package com.ipn.mx.modelo.dto;
import com.ipn.mx.modelo.entidades.Cuenta;

public class CuentaDTO {
    private Cuenta entidad;

    public CuentaDTO( ) {
        this.entidad = new Cuenta();
    }

    public Cuenta getEntidad() {
        return entidad;
    }

    public void setEntidad(Cuenta entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ID: ").append(getEntidad().getIdCuenta());
        sb.append("Id Cuenta: ").append(getEntidad().getIdCuenta());
        sb.append("Nombre cliente: ").append(getEntidad().getNombreCliente());
        sb.append("Id Envio: ").append(getEntidad().getIdEnvio());
        sb.append("Propina: ").append(getEntidad().getPropina());
        sb.append("Total: ").append(getEntidad().getTotal());
        sb.append("Fecha: ").append(getEntidad().getFecha());
        sb.append('}');
        return sb.toString();
    }
    
}
