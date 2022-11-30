/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lithium582
 */
public class OrdenadorTest {
    public static int kant = 0;
    public static TProducto[] productosArray;
    
    
    
    public OrdenadorTest() {
        String[] cosaLeida = ManejadorArchivosGenerico.leerArchivo("src/datos_productos.txt", false);
        
        LinkedList<TProducto> productos = new LinkedList();
        
        for (String s : cosaLeida) {
            try {
                String[] variosCampos = s.split(",");
                
                if (variosCampos.length == 4) {
                    long codigo = Long.parseLong(variosCampos[0]);
                    String nombre = variosCampos[1];
                    double precio = Double.parseDouble(variosCampos[2]);
                    int cantidad = Integer.parseInt(variosCampos[3]);
                    
                    TProducto productoO = new TProducto(codigo, nombre, precio, cantidad);
                    productos.add(productoO);
                }
            } catch (Exception ex) {
                
            }
        }
        
        kant = productos.size();
        productosArray = new TProducto[kant];
        productos.toArray(productosArray);
    }
    
    /**
     * Test of heapSort method, of class Ordenador.
     */
    @org.junit.Test
    public void testHeapSort() {
        TProducto[] productosOrdenados = Ordenador.heapSort(productosArray, true);
        assertEquals(productosOrdenados.length, kant);
        
        TProducto[] productosOrdenadosD = Ordenador.heapSort(productosArray, false);
        assertEquals(productosOrdenadosD.length, kant);
        
        assertTrue(Ordenador.estaOrdenado(productosOrdenados));
        assertTrue(Ordenador.estaOrdenadoDesc(productosOrdenadosD));
        
        assertEquals(productosOrdenados.length, productosArray.length);
        assertEquals(productosOrdenadosD.length, productosArray.length);
    }
}
