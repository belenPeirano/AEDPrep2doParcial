

import java.util.Collection;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class TCamino {

    private final TVertice origen;
    private final Collection<Comparable> otrosVertices;
    // es una lista de etiquetas de los vertices
    // ATENCIÓN: PONER LA CLASE CONCRETA QUE
    // SEA MÁS APROPIADA
    private Double costoTotal;

    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    public String imprimirEtiquetas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Origen: ").append(getOrigen().getEtiqueta());
        for (Comparable adyacente : getOtrosVertices()) {
            sb.append(" -> ").append(adyacente);
        }
        return sb.toString();
    }

    public TCamino(TVertice v) {
        this.costoTotal = 0.0d;
        this.origen = v;
        this.otrosVertices = new LinkedList();

    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal += adyacenciaActual.getCosto();
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal -= adyacenciaActual.getCosto();
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public TVertice getOrigen() {
        return origen;
    }

    public Collection<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public TCamino copiar() {
        TVertice origenCopia = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origenCopia);
        copia.setCostoTotal(this.getCostoTotal());
        // origenCopia.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());

        return copia;
    }

    public String imprimirDesdeClave(Comparable clave) {
        StringBuilder sb = new StringBuilder();
        boolean start = false;
        Collection<Comparable> listaDefinitiva = new LinkedList<>();
        listaDefinitiva.add(this.getOrigen().getEtiqueta());
        listaDefinitiva.addAll(this.getOtrosVertices());

        for (Comparable next : listaDefinitiva) {
            if (next.compareTo(clave) == 0) {
                start = true;
            }
            if (start) {
                sb.append(" ").append(next).append(" ");
            }
        }
        return sb.toString();
    }

}
