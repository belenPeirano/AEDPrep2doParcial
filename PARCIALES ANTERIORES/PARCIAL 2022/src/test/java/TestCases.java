
import java.util.Collection;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestCases {

    public TestCases() {
    }

    @Test
    public void testCompilarConCilcos() {
        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();
        TVertice a = new TVertice("a");
        TVertice b = new TVertice("b");
        TVertice c = new TVertice("c");

        vertices.add(a);
        vertices.add(b);
        vertices.add(c);

        TArista ab = new TArista("a", "b", 10);
        TArista bc = new TArista("b", "c", 15);
        TArista ca = new TArista("c", "a", 5);
        aristas.add(ab);
        aristas.add(bc);
        aristas.add(ca);

        TProyecto proyecto = new TProyecto(vertices, aristas);

        LinkedList<Comparable> res = proyecto.compilarDependencias("a");
        //assertEquals(null, res); // Hay un ciclo, devuelve null.
    }

    @Test
    public void testCompilarSinCiclos() {
        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();
        TVertice a = new TVertice("a");
        TVertice b = new TVertice("b");
        TVertice c = new TVertice("c");

        vertices.add(a);
        vertices.add(b);

        TArista ab = new TArista("a", "b", 10);
        aristas.add(ab);
        aristas.add(new TArista("b", "c", 10));

        TProyecto proyecto = new TProyecto(vertices, aristas);

        LinkedList<Comparable> res = proyecto.compilarDependencias("a");
        LinkedList<Comparable> expected = new LinkedList<>();
        expected.add("c");
        expected.add("b");
        
        System.out.println(res);

    }

    @Test
    public void testElementoNoEnGrafo() {
        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();
        TVertice a = new TVertice("a");
        TVertice b = new TVertice("b");
        TVertice c = new TVertice("c");

        vertices.add(a);
        vertices.add(b);

        TArista ab = new TArista("a", "b", 10);
        aristas.add(ab);
        TProyecto proyecto = new TProyecto(vertices, aristas);
        
        assertEquals(null,proyecto.compilarDependencias("r")); // r no esta en el proyecto
    }
}
