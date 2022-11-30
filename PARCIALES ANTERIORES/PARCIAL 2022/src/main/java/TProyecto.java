
import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author ernesto
 */
public class TProyecto extends TGrafoDirigido implements ICompilar {

    public TProyecto(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<Comparable> compilarDependencias(String modulo) {
        this.desvisitarVertices();
        if (!this.getVertices().containsKey(modulo)) {
            return null;    // 
        }
        TVertice vertice = this.getVertices().get(modulo);
        if (this.existeVertice(vertice.getEtiqueta())) {
            TGrafoDirigido grafoInvertido = this.getGrafoInvertido();
            LinkedList<Comparable> listaModulos = new LinkedList<Comparable>();
            grafoInvertido.getVertices().get(vertice.getEtiqueta()).compilarModulo(listaModulos);
            return listaModulos;
        } else {
            System.out.println("Tiene ciclos, no es posible compilar");
            return null;
        }
    }

    private TGrafoDirigido getGrafoInvertido() {    
    // Invierte el grafo para realizar el orden topologico correctamente.
    
        LinkedList<TArista> aristas = new LinkedList<TArista>();
        LinkedList<TVertice> vertices = new LinkedList<TVertice>();
        Collection<TVertice> verticesGrafo = this.getVertices().values();
        for (TVertice vertice : verticesGrafo) {
            LinkedList<TAdyacencia> adyacentes = vertice.getAdyacentes();
            vertices.add(new TVertice<>(vertice.getEtiqueta()));
            for (TAdyacencia adyacencia : adyacentes) {
                aristas.add(new TArista(adyacencia.getDestino().getEtiqueta(), vertice.getEtiqueta(), adyacencia.getCosto()));
            }

        }
        return new TGrafoDirigido(vertices, aristas);
    }

}
