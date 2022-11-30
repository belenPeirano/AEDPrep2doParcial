package ut9.mavenproject1;

import java.util.Collection;
import java.util.LinkedList;


public class TCaminos {
    
    private LinkedList<TCamino> caminos;

    public TCaminos() {
        this.caminos = new LinkedList<>();
    }
    
    public String imprimirCaminos(){
        StringBuilder sb = new StringBuilder();
        for (TCamino camino : caminos){
            sb.append(camino.imprimirEtiquetas()+"\n");
        }
        return sb.toString();
    }

    public void imprimirCaminosConsola(){
        System.out.println(imprimirCaminos());
    }

    public Collection<TCamino> getCaminos() {
        return caminos;
    }
    
    public TCamino getCaminoMenorCosto() {
        TCamino menorCamino = caminos.getFirst();
        for (TCamino tCamino : caminos) {
            if (tCamino.getCostoTotal() < menorCamino.getCostoTotal())
                menorCamino = tCamino;
        }
        return menorCamino;
    }

    public TCamino getCaminoMayorCosto() {
        TCamino mayorCamino = caminos.getFirst();
        for (TCamino tCamino : caminos) {
            if (tCamino.getCostoTotal() > mayorCamino.getCostoTotal()) {
                mayorCamino = tCamino;
            }
        }
        return mayorCamino;
    }
    
}
