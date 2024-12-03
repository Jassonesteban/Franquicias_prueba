package com.franquiciaS.model;

public class SucursalRequest {
    private String franquiciaId;
    private Sucursal sucursal;

    public SucursalRequest() {
    }

    public SucursalRequest(String franquiciaId, Sucursal sucursal) {
        this.franquiciaId = franquiciaId;
        this.sucursal = sucursal;
    }

    public String getFranquiciaId() {
        return franquiciaId;
    }

    public void setFranquiciaId(String franquiciaId) {
        this.franquiciaId = franquiciaId;
    }
    
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

}
