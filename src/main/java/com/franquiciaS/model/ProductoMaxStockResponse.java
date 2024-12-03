package com.franquiciaS.model;

public class ProductoMaxStockResponse {
    private String sucursal;
    private Producto producto;

    public ProductoMaxStockResponse() {
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
