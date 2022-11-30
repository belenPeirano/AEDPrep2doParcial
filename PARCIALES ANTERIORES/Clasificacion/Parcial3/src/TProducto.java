/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public class TProducto implements IProducto {
    private long codigo;
    private String descripcion;
    private double precio;
    private int cantidad;

    @Override
    public long getCodigo() {
        return this.codigo;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public double valor() {
       return (this.cantidad * this.precio);
    }
    
    public TProducto(long codigo, String nombre, double precio, int cantidad){
        this.codigo = codigo;
        this.descripcion = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
    
    @Override
    public String toString(){
        return this.codigo + "-" + this.descripcion + "-" + this.precio + "-" + this.cantidad;
    }
            
}
