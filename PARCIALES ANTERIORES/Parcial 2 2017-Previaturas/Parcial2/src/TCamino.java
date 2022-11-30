
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCamino {
    


    private final TVertice origen; 
    public boolean critico;
    public LinkedList<TVertice> otrosVertices; // ATENCIÓN: PONER LA CLASE CONCRETA QUE
     // SEA MÁS APROPIADA
    public Double costoTotal = 0.0d;
    
// es una lista de etiquetas de los vertices
// ATENCIÓN: PONER LA CLASE CONCRETA QUE									     	     
// SEA MÁS APROPIADA
    
    public TCamino(TVertice v){
        this.origen = v;
        this.otrosVertices = new LinkedList<TVertice>();
        this.costoTotal = 0.0;
        this.critico = false;
    }
    
    public boolean isCritico() {
        return critico;
    }

    public void setCritico(boolean critico) {
        this.critico = critico;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    public TVertice getOrigen(){
        return origen;
    }
    
    public LinkedList<TVertice> getOtrosVertices(){
        return otrosVertices;
    }
    
    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + ((Number)adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino());
        }
        return false;
    }
    
    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino())) {
            costoTotal = costoTotal - ((Number)adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino()));
        }
        return false;
    }
    private void setCosto(double unCosto){
        costoTotal = unCosto;
    }
    public TCamino copiar(){
        TCamino copia = new TCamino(origen);
        //copia.getOrigen().getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        return copia;
    }
    
}
