
package ut9.mavenproject1;

/**
 *
 * @author belu_
 */
public class TDAGrafoNoDirigido {

    public static void main(String[] args) {
        TGrafoNoDirigido grafo = UtilGrafos.cargarGrafo(".\\src\\main\\java\\ut9\\mavenproject1\\vertices.txt", ".\\src\\main\\java\\ut9\\mavenproject1\\aristas.txt",
                false, TGrafoNoDirigido.class);
        
        //BEA
        System.out.println(grafo.bea("Santos"));
        
        //NUMBACON
        TGrafoNoDirigido grafo2 = UtilGrafos.cargarGrafo(".\\src\\main\\java\\ut9\\mavenproject1\\actores.csv", ".\\src\\main\\java\\ut9\\mavenproject1\\en_pelicula.csv",
                false, TGrafoNoDirigido.class);
        
        // invocar a numBacon como indica la letra y mostrar en consola el resultado
        System.out.println("El numBacon de John_Goodman es: " + grafo2.numBacon("John_Goodman"));
        System.out.println("El numBacon de Tom_Cruise es: " + grafo2.numBacon("Tom_Cruise"));
        System.out.println("El numBacon de Jason_Stathman es: " + grafo2.numBacon("Jason_Statham"));
        System.out.println("El numBacon de Lukas_Haas es: " + grafo2.numBacon("Lukas_Haas"));
        System.out.println("El numBacon de Djimon_Hounsou es: " + grafo2.numBacon("Djimon_Hounsou"));
        
        //KRUSKAL
        System.out.println("\n-----------KRUSKAL-----------");
        grafo.Kruskal();

        //PRIM
        System.out.println("\n------------PRIM-------------");
        grafo.Prim();
    }
}
