/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ut9.tdagrafodirigido;

/**
 *
 * @author belu_
 */
public class TDAGrafoDirigido {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo(".\\src\\main\\java\\ut9\\tdagrafodirigido\\aeropuertos_1.txt", "\\src\\main\\java\\ut9\\tdagrafodirigido\\conexiones_1.txt",
                false, TGrafoDirigido.class);

        
        //TODOS LOS CAMINOS
        TCaminos caminos = gd.todosLosCaminos("Santos", "Curitiba");
        caminos.imprimirCaminosConsola();    
        System.out.println();
        
        // FLOYD
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }

        //CENTRO DEL GRAFO
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());   
    }
}
