
import java.util.Collection;
import java.util.LinkedList;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    @Override
    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    @Override
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
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
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

    @Override
    public Object getDatos() {
        return datos;
    }

    @Override
    public void compilar() {
        System.out.println("compilando...." + this.getEtiqueta()
        );

    }

    @Override
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        this.setVisitado(true);
        camino.add(this.etiqueta);
        for (TAdyacencia adyacencia : this.adyacentes) {
            Comparable etiquetaAdyacente = adyacencia.getDestino().getEtiqueta();
            if (camino.contains(etiquetaAdyacente)) {
                return true;
            }
            if (!adyacencia.getDestino().getVisitado()) {
                if (adyacencia.getDestino().tieneCiclo(camino)) {
                    return true;
                }
            }
        }
        camino.remove(this.etiqueta);
        return false;
    }

    @Override
    public void compilarModulo(LinkedList<Comparable> listaModulos) {
        this.setVisitado(true);
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            if (!adyacente.getDestino().visitado) {
                adyacente.getDestino().compilarModulo(listaModulos);
                //compilar();
            }
        }
        listaModulos.add("\t" + this.getEtiqueta());
    }

}
//&& adyacente.getEtiqueta() != listaModulos.get(0)
