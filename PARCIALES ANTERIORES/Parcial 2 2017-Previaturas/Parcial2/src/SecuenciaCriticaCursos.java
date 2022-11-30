public class SecuenciaCriticaCursos {

    public static void main(String[] args) {
        TPreviaturas proceso  = (TPreviaturas) UtilGrafos.cargarGrafo("src/cursos.txt","src/previas.txt",
                false, TPreviaturas.class);

        TCaminos c = proceso.obtenerSecuenciaCritica();
        c.imprimir();
        
        

    }
}
