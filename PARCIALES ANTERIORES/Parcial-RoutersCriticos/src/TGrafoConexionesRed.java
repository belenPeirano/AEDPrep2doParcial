
import java.util.Collection;
import java.util.LinkedList;


public class TGrafoConexionesRed extends TGrafoNoDirigido implements IGrafoConexionesRed {

    public TGrafoConexionesRed(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public LinkedList<TVertice> routersCriticos(Comparable etRouter) {
        super.desvisitarVertices();
        LinkedList<TVertice> puntosArt = new LinkedList<TVertice>();
        TVertice vertice = super.getVertices().get(etRouter);
        int[] numbpf = new int[1];
        numbpf[0]=1;
        vertice.puntosDeArticulacion(puntosArt, numbpf, null);
        return puntosArt;
    }

       
  

}
