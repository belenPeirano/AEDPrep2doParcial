package ut7.tdagrafo;


import java.util.Collection;
import java.util.LinkedList;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

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
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
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

    public void bpf(Collection<TVertice> visitados) {
        this.visitado = true;
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertticeAdy = adyacente.getDestino();
            if (!vertticeAdy.visitado){
                vertticeAdy.bpf(visitados);
            }
        }
    }

    /*
    Dado un cierto vértice (la etiqueta) de origen y uno de 
    destino, se encuentran y listea todos los caminos existentes del origen al destino, indicando también el costo asociado.
    */
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.visitado = true;
        caminoPrevio.agregarAdyacencia(new TAdyacencia(0, this));

        for (TAdyacencia adyacente : adyacentes) {
            if (adyacente.getDestino().equals(etVertDest)) {
                caminoPrevio.agregarAdyacencia(new TAdyacencia(0, new TVertice(etVertDest)));
                todosLosCaminos.getCaminos().add(caminoPrevio.copiar());
            } else if (!adyacente.getDestino().visitado) {
                adyacente.getDestino().todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
            }
        }
        caminoPrevio.eliminarAdyacencia(new TAdyacencia(0, this));
        return todosLosCaminos;
    }

    public boolean conectadoCon(TVertice destino) {
        if (this.etiqueta.compareTo(destino.getEtiqueta()) == 0) {
            return true;
        }

        this.setVisitado(true);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice verticeAdyacente = adyacencia.getDestino();
            if (!verticeAdyacente.getVisitado()) {
                if (verticeAdyacente.conectadoCon(destino)) {
                    return true;
                }
            }
        }

        return false;
    }
    
    public void ordenTopologico(LinkedList<TVertice> ordenTopologico) {
        this.setVisitado(true);
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            if (!adyacente.getDestino().visitado) {
                adyacente.getDestino().ordenTopologico(ordenTopologico);
            }
        }
        ordenTopologico.add(this);
    }
    
    public boolean tieneCiclo(LinkedList<Comparable> camino) {
        this.setVisitado(true);
        camino.add(this.etiqueta);
        for (TAdyacencia adyacencia : this.adyacentes) {
            Comparable etiquetaAdyacente = adyacencia.getDestino().getEtiqueta();
            if (camino.contains(etiquetaAdyacente)) {
                System.out.println(camino);
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
    
    public T getDatos() {
        return datos;
    }

}
