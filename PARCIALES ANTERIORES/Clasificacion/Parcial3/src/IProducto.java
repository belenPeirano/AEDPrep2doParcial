/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface IProducto {
    long getCodigo();
    String getDescripcion();
    double getPrecio();
    int getCantidad();
    
    double valor();
    
}
