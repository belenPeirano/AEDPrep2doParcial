/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCaminos {
    
    private Collection<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public String imprimirCaminos(){
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos){
            //sb.append(camino.imprimirEtiquetas()+"\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }
    
    public void imprimir () {
        for(TCamino camino: caminos){
            String a = (String)camino.getOrigen().getEtiqueta();
            for(TVertice etiqueta: camino.getOtrosVertices()){
                a+="-"+ etiqueta.getEtiqueta();
            }
            if (camino.isCritico()){
                System.out.println("Este es el Camino Crítico: ");
            }
            System.out.println(a);
            System.out.println("Costo: "+ camino.getCostoTotal());
        }
            
    } 
    
    
    
}
