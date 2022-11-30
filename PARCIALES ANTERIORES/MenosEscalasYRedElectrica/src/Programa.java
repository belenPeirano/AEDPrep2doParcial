
import java.util.LinkedList;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// cargar grafo aerolínea con aeropuertos y vuelos
        TGrafoAerolinea aerolinea = UtilGrafos.cargarGrafo("src/aeropuertos_3.txt", "src/vuelos_3.txt", false, TGrafoAerolinea.class);
        
       //TGrafoAerolinea aerolinea = ... 
        
        String elOrigen = "J";
        String elDestino = "T";
        
        // obtener el itinerario con menos escalas entre elOrigen y elDestino;
         LinkedList<TVertice> lasEscalas = aerolinea.menosEscalas(elOrigen, elDestino);
        
        /*
        mostrar las escalas y listar en archivo de salida 
        */
        
      
        
        System.out.println();
        
        String[] claves = new String[lasEscalas.size()];
 
        int c = 0;
        System.out.println("itinerario mejor para vuelo entre "+ elOrigen+ " y "+ elDestino +": ");
        for (TVertice v : lasEscalas) {
            claves[c] = (String)v.getEtiqueta();
            System.out.println(v.getEtiqueta());
            c++;
        }
        ManejadorArchivosGenerico.escribirArchivo("src/escalas.txt", claves);
        
      /*----------------------------------------------------------------------------------------------*/
      
        //cargar grafo con casas y distancias
        TGrafoRedElectrica barrio = UtilGrafos.cargarGrafo("src/barrio.txt", "src/distancias.txt",
                false, TGrafoRedElectrica.class);
        
        //calcular la mejor red electrica
        TAristas mapaBarrio = barrio.mejorRedElectrica();
        
        String[] aristas = new String[mapaBarrio.size() + 1];
 
        int a = 0;
        int costoTotal = 0;
        for (TArista v : mapaBarrio) {
            costoTotal += v.getCosto();
            aristas[a] = v.etiquetaOrigen + "-" + v.etiquetaDestino + "-" + v.costo;
            a++;
        }
        aristas[mapaBarrio.size()] = String.valueOf(costoTotal);
        
        ManejadorArchivosGenerico.escribirArchivo("src/redelectrica.txt", aristas);
        /*----------------------------------------------------------------------------------------------*/
        //listar en el archivo "redelectrica.txt" el costo total del cableado
        //y las conexiones establecidas, una por linea (origen, destino, costo)
        
        
    }
}
