
import java.util.Collection;
import java.util.LinkedList;


public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int numBajo = 0;
    private int numBpf = 0;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public T getDatos() {
        return datos;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }


    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

   

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }
    
    public void puntosDeArticulacion(LinkedList<TVertice> vertices, int[] numBpf, Comparable padre) {
        this.visitado = true;
        this.numBpf = numBpf[0];
        numBpf[0]++;
        this.numBajo = this.numBpf;
        int cantHijosRaiz = 0;
        for(TAdyacencia adyacente : adyacentes){
            TVertice vertice = adyacente.getDestino();
            if(!vertice.visitado){
                vertice.puntosDeArticulacion(vertices, numBpf, this.etiqueta);
                if(vertice.numBajo<this.numBajo){
                    this.numBajo = vertice.numBajo;
                }
                if(!vertices.contains(this) && vertice.numBajo > this.numBpf){
                    vertices.add(this);
                }
            }else{  //SI YA ESTÁ VISITADO ES UN ARCO DE RETROCESO
                if(vertice.etiqueta != padre){    
                    if(vertice.numBpf < this.numBajo){
                        this.numBajo = vertice.numBpf;
                    }
                }
            }
            cantHijosRaiz++;    //LA RAIZ NO PUEDE TENER ARCOS DE RETROCESO
        }
        if(!vertices.contains(this) && padre==null && (cantHijosRaiz > 1)){
            vertices.add(this);
        }
    }

 
   

   
}
