/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Agustina
 */
public class TGrafoNoDirigidoTest {
    
    
    TGrafoConexionesRed gnd;
    
    @Test
    public void testPuntosDeArticulacionVacio() {
        Collection<TVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        Collection<TArista> aristas = new ArrayList<>();
                
        this.gnd = new TGrafoConexionesRed(vertices,aristas);
        
        assertEquals(gnd.routersCriticos("A").size(),0);

    }
    
    @Test
    public void testPuntosDeArticulacionSinPuntosDeArticulacion() {
        Collection<TVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        Collection<TArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("A", "C", 1));
        
        this.gnd = new TGrafoConexionesRed(vertices,aristas);
        
        assertEquals(gnd.routersCriticos("A").size(),1);

    }
    
    @Test
    public void testPuntosDeArticulacionSoloDos() {
        Collection<TVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));
        Collection<TArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("D", "C", 1));
        
        
        this.gnd = new TGrafoConexionesRed(vertices,aristas);
        
        assertEquals(gnd.routersCriticos("B").size(),2);

    }
    
}
