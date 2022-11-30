
import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // cargar grafo con casas y distancias
        TGrafoRedElectrica laRed = (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
                ".\\src\\barrio.txt",
                ".\\src\\distancias.txt",
                false, TGrafoRedElectrica.class);

        /*
        calcular la mejor red electrica\
        listar en el archivo "redelectrica.txt" el costo total del cableado
        y las conexiones establecidas, una por linea (origen, destino, costo)
         */
        TAristas con = laRed.mejorRedElectrica();
        String[] conexiones = new String[con.size()];

        for (int i = 0; i < con.size(); i++) {
            TArista arista = con.get(i);
            conexiones[i] = arista.etiquetaOrigen + ", " + arista.etiquetaDestino + ", " + arista.getCosto();
        }

        ManejadorArchivosGenerico.escribirArchivo(".\\src\\redelectrica.txt", conexiones);
    }
}
