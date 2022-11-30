
import java.util.LinkedList;

public class Ordenador {
    
    public static void main(String[] args) {
        // cargar los datos utilizando el Manejador de Archivos
        // utilizar la clase TProducto

        // Ordenar en forma ascendente
        // EMITIR POR CONSOLA EL VALOR TOTAL DE STOCK DE LOS PRIMEROS 500 ELEMENTOS
        // escribir el archivo de salida con los datos ordenados en forma ascendente, 
        //Y AL FINAL ESCRIBIR EL VALOR DE STOCK DE LOS PRIMEROS 500 ELEMENTOS
        // Ordenar en forma descendente
        // EMITIR POR CONSOLA EL VALOR TOTAL DE STOCK DE LOS PRIMEROS 300 ELEMENTOS
        // escribir el archivo de salida con los datos ordenados en forma descendente
        // Y AL FINAL ESCRIBIR EL VALOR DE STOCK DE LOS PRIMEROS 300 ELEMENTOS
        String[] cosaLeida = ManejadorArchivosGenerico.leerArchivo("src/datos_productos.txt", false);
        
        LinkedList<TProducto> productos = new LinkedList();
        
        for (String s : cosaLeida) {
            try {
                String[] variosCampos = s.split(",");
                
                if (variosCampos.length == 4) {
                    long codigo = Long.parseLong(variosCampos[0]);
                    String nombre = variosCampos[1];
                    double precio = Double.parseDouble(variosCampos[2]);
                    int cantidad = Integer.parseInt(variosCampos[3]);
                    
                    TProducto productoO = new TProducto(codigo, nombre, precio, cantidad);
                    productos.add(productoO);
                }
            } catch (Exception ex) {
                
            }
        }
        
        int kant = productos.size();
        TProducto[] productosArray = new TProducto[kant];
        productos.toArray(productosArray);
        
        TProducto[] productosOrdenados = heapSort(productosArray, true);
        
        //System.out.println(strArray(productosOrdenados));
        
        Double cantStock = 0D;
        for (int i = 0; i < 500; i++) {
            cantStock += productosOrdenados[i].valor();
        }
        
        System.out.println("Valor de los primeros 500: " + cantStock);
        
        String[] cosasParaEscribir = new String[2];
        cosasParaEscribir[0] = strArray(productosOrdenados);
        cosasParaEscribir[1] = cantStock.toString();
        
        ManejadorArchivosGenerico.escribirArchivo("src/salidaNombresAscendentes.txt", cosasParaEscribir);
        System.out.println("Ya escribí los ascendentes");
        
        TProducto[] productosOrdenadosDescendentes = heapSort(productosArray, false);
        
        //System.out.println(strArray(productosOrdenadosDescendentes));
        
        Double cantStockDesc = 0D;
        for (int i = 0; i < 300; i++) {
            cantStockDesc += productosOrdenadosDescendentes[i].valor();
        }
        
        System.out.println("Valor de los primeros 300: " + cantStockDesc);
        
        String[] cosasParaEscribirDesc = new String[2];
        cosasParaEscribirDesc[0] = strArray(productosOrdenadosDescendentes);
        cosasParaEscribirDesc[1] = cantStockDesc.toString();
        
        ManejadorArchivosGenerico.escribirArchivo("src/salidaNombresDescendentes.txt", cosasParaEscribirDesc);
        System.out.println("Ya escribí los descendentes también");
    }

    //-------------
    //5 - HeapSort:
    //-------------
    public static TProducto[] heapSort(TProducto[] vector, boolean ascendentes) {
        TProducto[] copia = vector.clone();
        for (int i = copia.length / 2 - 1; i >= 0; i--) {
            if(ascendentes){
                hippieficar(copia, copia.length, i);
            } else{
                hippieDecadente(copia, copia.length, i);
                //hippieDecadente(copia, i, copia.length);
            }
            
        }
        for (int i = copia.length - 1; i >= 0; i--) {
            //int aux = copia[0];
            TProducto aux = copia[0];
            copia[0] = copia[i];
            copia[i] = aux;
            if(ascendentes){
                hippieficar(copia, i, 0);
            } else{
                hippieDecadente(copia, i, 0);
                //hippieDecadente(copia, 0, i);
            }
        }
        return copia;
    }
    
    private static void hippieficar(TProducto[] arr, int n, int i) {
        int maximo = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        
        if (l < n && arr[l].getCodigo() > arr[maximo].getCodigo()) {
            maximo = l;
        }
        
        if (r < n && arr[r].getCodigo() > arr[maximo].getCodigo()) {
            maximo = r;
        }
        
        if (maximo != i) {
            TProducto swap = arr[i];
            arr[i] = arr[maximo];
            arr[maximo] = swap;
            hippieficar(arr, n, maximo);
        }
    }
    
    private static void hippieDecadente(TProducto[] arr, int n, int i) {
        int maximo = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        
        //if (l < n && arr[l].getCodigo() > arr[maximo].getCodigo()) {
        if (l < n && arr[l].getCodigo() < arr[maximo].getCodigo()) {
            maximo = l;
        }
        
        //if (r < n && arr[r].getCodigo() > arr[maximo].getCodigo()) {
        if (r < n && arr[r].getCodigo() < arr[maximo].getCodigo()) {
            maximo = r;
        }
        
        if (maximo != i) {
            TProducto swap = arr[i];
            arr[i] = arr[maximo];
            arr[maximo] = swap;
            hippieDecadente(arr, n, maximo);
        }
    }
    
    public static String strArray(TProducto[] vector) {
        String strRetorno = "";
        if (vector.length > 0) {
            strRetorno = String.valueOf(vector[0].toString());
            for (int i = 1; i < vector.length; i++) {
                strRetorno += "\n" + String.valueOf(vector[i].toString());
            }
        }
        return strRetorno;
    }
    
    public static boolean estaOrdenado(TProducto[] vector) {
        boolean ordenado = true;
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i].getCodigo() > vector[i + 1].getCodigo()) {
                ordenado = false;
                break;
            }
        }
        return ordenado;
    }
    
    public static boolean estaOrdenadoDesc(TProducto[] vector) {
        boolean ordenado = true;
        for (int i = 0; i < vector.length - 1; i++) {
            if (vector[i].getCodigo() < vector[i + 1].getCodigo()) {
                ordenado = false;
                break;
            }
        }
        return ordenado;
    }
}
