
import java.util.Collection;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernesto
 */
public class TGrafoAerolinea extends TGrafoDirigido implements IGrafoAerolinea {

    public TGrafoAerolinea(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<TVertice> menosEscalas(Comparable origen, Comparable destino) {
        this.desvisitarVertices();
        TVertice vOrigen = this.buscarVertice(origen);
        TVertice vDestino = this.buscarVertice(destino);

        LinkedList<TVertice> lista = null;

        if (!(vOrigen == null) && !(vDestino == null)) {
            lista = new LinkedList<>();
            vOrigen.menosEscalas(origen, destino);
            TVertice aux = vDestino;

            while (!(aux.getEtiqueta()).equals(origen)) {
                lista.addFirst(aux);
                aux = aux.getPredecesor();
            }
            lista.addFirst(vOrigen);
        }
        return lista;
    }

}
