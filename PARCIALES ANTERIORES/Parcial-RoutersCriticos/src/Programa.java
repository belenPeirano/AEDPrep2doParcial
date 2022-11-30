
import java.util.LinkedList;

public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // cargar grafo con actores y relaciones

        TGrafoConexionesRed red = (TGrafoConexionesRed) UtilGrafos.cargarGrafo(
                "src/routers.txt",
                "src/conexiones.txt",
                false, TGrafoConexionesRed.class);

        // invocar a criticos como indica la letra y mostrar en consola el resultado
        // comprobar con diferentes id de routers de arranque del algoritmo
        
        LinkedList<TVertice> resultado = red.routersCriticos("R1");
        
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println(resultado.get(i).getEtiqueta());
        }
        
        System.out.println(" ");
        
        LinkedList<TVertice> resultado2 = red.routersCriticos("R5");
        
        for (int i = 0; i < resultado2.size(); i++) {
            System.out.println(resultado2.get(i).getEtiqueta());
        }
        
        
    }

}
