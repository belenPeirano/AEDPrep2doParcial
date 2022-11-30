package ut9.mavenproject1;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);

    }

    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        if (!this.esConexo()) {
            return null;
        }

        Map<Comparable, TVertice> v = new HashMap<>(this.getVertices());
        Map<Comparable, TVertice> u = new HashMap<>();

        TVertice vInicial = this.getVertices().values().iterator().next();
        u.put(vInicial.getEtiqueta(), v.remove(vInicial.getEtiqueta()));
        TAristas aristas = new TAristas();

        while (!v.isEmpty()) {
            TArista minima = this.lasAristas.buscarMin(u.keySet(), v.keySet());
            aristas.add(minima);
            u.put(minima.getEtiquetaDestino(), v.remove(minima.getEtiquetaDestino()));
        }

        return new TGrafoNoDirigido(u.values(), aristas);
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        TGrafoNoDirigido arbolCostoMinimo = new TGrafoNoDirigido(this.getVertices().values(), new TAristas());
        TAristas aristasOrdenadas = new TAristas();
        aristasOrdenadas.addAll(this.lasAristas);
        aristasOrdenadas.ordenarPorCostoCreciente();
        int aristasAgregadas = 0;

        while (aristasAgregadas <= getVertices().size() - 1) {
            TArista aristaMin = aristasOrdenadas.removeFirst();
            TVertice verticeOrigen = arbolCostoMinimo.getVertices().get(aristaMin.getEtiquetaOrigen());
            TVertice verticeDestino = arbolCostoMinimo.getVertices().get(aristaMin.getEtiquetaDestino());
            if (!arbolCostoMinimo.conectados(verticeOrigen, verticeDestino)) {
                arbolCostoMinimo.insertarArista(aristaMin);
                aristasAgregadas++;
            }
        }
        return arbolCostoMinimo;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        desvisitarVertices();
        LinkedList<TVertice> listaVisitados = new LinkedList<>();
        TVertice origen = this.getVertices().get(etiquetaOrigen);

        if (origen != null) {
            origen.bea(listaVisitados);
        } else {
            System.out.println("No existe.");
        }
        return listaVisitados;
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        if (esConexo()) {
            desvisitarVertices();
            int[] cont = {0};
            LinkedList<TVertice> puntos = new LinkedList<>();
            TVertice vert = this.buscarVertice(etOrigen);
            if (vert != null) {
                vert.puntosArt(puntos, cont);
            }
            return puntos;
        }
        return null;
    }

    @Override
    public boolean esConexo() {
        return this.getVertices().values().size() == bpf(this.getVertices().values().iterator().next()).size();
    }

    public int numBacon(Comparable actor) {
        desvisitarVertices();
        int numBacon;
        TVertice kBacon = getVertices().get("Kevin_Bacon");
        TVertice vActor = getVertices().get(actor);
        if (vActor != null) {
            //Si ya tiene un numero de bacon (ya se corrio el algoritmo una vez).
            if (vActor.getBacon() > -1) {
                numBacon = vActor.getBacon();
            } else {
                numBacon = kBacon.numBacon(actor);
                if (numBacon == Integer.MAX_VALUE) {
                    vActor.setBacon(numBacon);
                }
            }
        } else {
            numBacon = -1;
        }
        return numBacon;
    }
}
