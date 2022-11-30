package ut9.mavenproject1;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

    private int numBacon = -1;
    private int numBp;
    private int numBajo;

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
    public void bpf(Collection<TVertice> visitados) {
        this.setVisitado(true);
        System.out.println(this.etiqueta);
        visitados.add(this);
        for (TAdyacencia adyacencia : adyacentes) {
            TVertice verticeAdyacente = adyacencia.getDestino();
            if (!verticeAdyacente.getVisitado()) {
                verticeAdyacente.bpf(visitados);
            }
        }
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        for (TAdyacencia adyacencia : getAdyacentes()) {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        }
        setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        this.setVisitado(true);
        TVertice x = null;
        StringBuilder sb = new StringBuilder();
        Queue<TVertice> C = new LinkedList<>();
        C.add(this);
        sb.append(this.etiqueta + " ");
        while (!C.isEmpty()) {
            x = C.remove();
            for (TAdyacencia y : (LinkedList<TAdyacencia>) x.getAdyacentes()) {
                TVertice actual = y.getDestino();
                if (!actual.getVisitado()) {
                    actual.setVisitado(true);
                    C.add(actual);
                    visitados.add(actual);
                    sb.append(actual.getEtiqueta() + " ");
                }
            }
        }
        //System.out.println(sb.toString());
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

    @Override
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

    public int getBacon() {
        return this.numBacon;
    }

    public void setBacon(int newBacon) {
        this.numBacon = newBacon;
    }

    public int numBacon(Comparable actor) {
        this.setBacon(0);
        this.setVisitado(true);
        TVertice x;
        Queue<TVertice> cola = new LinkedList<TVertice>();
        cola.add(this);
        while (!cola.isEmpty()) {
            x = cola.remove();
            LinkedList<TAdyacencia> adyacencias = x.getAdyacentes();
            for (TAdyacencia tAdyacencia : adyacencias) {
                TVertice y = tAdyacencia.getDestino();
                if (!y.getVisitado()) {
                    y.setBacon(x.getBacon() + 1);
                    y.setVisitado(true);
                    if (y.getEtiqueta().compareTo(actor) == 0) {
                        return y.getBacon();
                    }
                    cola.add(y);

                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public void puntosArt(Collection<TVertice> puntos, int[] cont) {
        setVisitado(true);
        cont[0]++;
        this.numBp = cont[0];
        this.numBajo = cont[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia ady : this.getAdyacentes()) {
            TVertice adyacente = ady.getDestino();
            if (!adyacente.getVisitado()) {
                adyacente.puntosArt(puntos, cont);
                hijos.add(adyacente);
                this.numBajo = Math.min(this.numBajo, adyacente.numBajo);
            } else {
                this.numBajo = Math.min(this.numBajo, adyacente.numBp);
            }
        }

        if (this.numBp > 1) {
            for (TVertice hijo : hijos) {
                if (hijo.numBajo >= this.numBp) // Si para más de un hijo se cumple esto, se va a agregar más de 1 vez a "puntos"
                {
                    puntos.add(this);           //Esto se podría mejorar con otra Collection, un Set por ej.
                }
            }
        } else {
            if (hijos.size() > 1) {
                puntos.add(this);
            }
        }
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
}
