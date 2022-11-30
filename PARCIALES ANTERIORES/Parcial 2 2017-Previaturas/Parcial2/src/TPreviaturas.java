
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public class TPreviaturas extends TGrafoDirigido implements IPreviaturas{

    public TPreviaturas(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }
  
    
    
    
   
    @Override
    public TCaminos obtenerSecuenciaCritica() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        TCaminos caminos = null;
        if(!tieneCiclos()){
            this.restablecer();
            caminos = todosLosCaminos("Ingreso", "Graduacion");
            TCamino caminoMaximo = null;
            double pMaximo = Integer.MIN_VALUE;
            for (TCamino camino : caminos.getCaminos()) {
                double aux = 0;
                for (TVertice vertice : camino.getOtrosVertices()) {
                    aux += vertice.getDuracion();
                    
                }
                camino.setCostoTotal(aux);
                if (aux>pMaximo) {
                    if ((caminoMaximo!=null)&&caminoMaximo.isCritico()) {
                        caminoMaximo.setCritico(false);
                    }
                    caminoMaximo = camino;
                    pMaximo = aux;
                    caminoMaximo.setCritico(true);
                }
            }
        }
        return caminos;
        
    }
 
}
