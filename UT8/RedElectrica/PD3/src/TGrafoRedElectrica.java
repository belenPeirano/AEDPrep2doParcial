
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ocamp
 */
public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica{
    
    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAristas mejorRedElectrica() {
        if (!this.esConexo()) {
            return null;
        }

        Map<Comparable, TVertice> v = new HashMap<>(this.getVertices());
        Map<Comparable, TVertice> u = new HashMap<>();

        TVertice vInicial = this.getVertices().values().iterator().next();
        u.put(vInicial.getEtiqueta(), v.remove(vInicial.getEtiqueta()));
        TAristas aristas = new TAristas();

        while (!v.isEmpty()) {
            TArista aristaMin = this.lasAristas.buscarMin(u.keySet(), v.keySet());
            aristas.add(aristaMin);
            u.put(aristaMin.getEtiquetaDestino(), v.remove(aristaMin.getEtiquetaDestino()));
        }
        return aristas;
    }

}