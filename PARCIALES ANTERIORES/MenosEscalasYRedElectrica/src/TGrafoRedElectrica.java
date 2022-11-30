
import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ocamp
 */
public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica {

    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAristas mejorRedElectrica() {
        Collection<Comparable> universo = new LinkedList<>();
        Collection<Comparable> vertices = getVertices().keySet();
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(this.getVertices().values(), new TAristas());
        universo.add(getLasAristas().getFirst().getEtiquetaOrigen());
        while (!vertices.isEmpty()) {
            TArista arista = lasAristas.buscarMin(universo, vertices);
            universo.add(arista.getEtiquetaDestino());
            grafo.insertarArista(arista);
            grafo.lasAristas.add(arista);
            vertices.removeAll(universo);
        }
        return grafo.getLasAristas();
    }

    public TGrafoRedElectrica mejorRedElectricaK() {

        TGrafoRedElectrica arbolAbarcador = new TGrafoRedElectrica(this.getVertices().values(), new TAristas());

        Collection<Comparable> clavesVertices = this.getVertices().keySet();

        TAristas ConjuntoSeleccion = (TAristas) this.lasAristas.clone();

        boolean existeCamino;
        TArista aristaMinima;

        while (arbolAbarcador.lasAristas.size() < this.getVertices().size() - 1) {

            aristaMinima = ConjuntoSeleccion.buscarMin(clavesVertices, clavesVertices);

            ConjuntoSeleccion.remove(aristaMinima);

            existeCamino = arbolAbarcador.todosLosCaminos(aristaMinima.etiquetaOrigen, aristaMinima.etiquetaDestino).getCaminos().size() > 0;

            if (!existeCamino) {
                arbolAbarcador.insertarArista(aristaMinima);
                arbolAbarcador.lasAristas.add(aristaMinima);
            }

        }
        return arbolAbarcador;
    }

}
