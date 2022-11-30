package ut9.clasificador;

import static ut9.clasificador.TClasificador.METODO_CLASIFICACION_BURBUJA;
import static ut9.clasificador.TClasificador.METODO_CLASIFICACION_HEAP;

/**
 *
 * @author belu_
 */
public class Clasificador {

    static TClasificador clasificador = new TClasificador();

    public static void main(String args[]) {
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos(100);
        int[] vectorAleatorio = gdg.generarDatosAleatorios();
        int[] vectorAscendente = gdg.generarDatosAscendentes();
        int[] vectorDescendente = gdg.generarDatosDescendentes();

        int[] resAleatorioInsercion = clasificador.clasificar(vectorAleatorio,
                METODO_CLASIFICACION_BURBUJA);

        System.out.println(obtenerTiempoDeEjecucion(vectorAleatorio, METODO_CLASIFICACION_HEAP) + " nanoseconds");
    }

    public static long obtenerTiempoDeEjecucion(int[] arreglo, int metodoDeClasificacion) {
        long t1 = System.nanoTime();
        long total = 0;
        int cantLlamadas = 0;
        long tiempoResolucion = 1000000000; // 1 segundo, en nanosegundos

        while (total < tiempoResolucion) {
            cantLlamadas++;
            int[] datosCopia = arreglo.clone();
            clasificador.cascara(datosCopia);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoMedioAlgoritmoBase = total / cantLlamadas;

        t1 = System.nanoTime();
        total = 0;
        cantLlamadas = 0;
        while (total < tiempoResolucion) {
            cantLlamadas++;
            int[] datosCopia = arreglo.clone();
            clasificador.cascara(datosCopia);
            long t2 = System.nanoTime();
            total = t2 - t1;
        }

        long tiempoCascara = total / cantLlamadas;

        long tiempoMedioAlgoritmo = tiempoMedioAlgoritmoBase - tiempoCascara;

        return tiempoMedioAlgoritmo;

    }
}
