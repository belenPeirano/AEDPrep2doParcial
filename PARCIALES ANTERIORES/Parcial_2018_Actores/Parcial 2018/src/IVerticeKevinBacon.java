
import java.util.Collection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface IVerticeKevinBacon {
    int getNumBacon();
    void setNumBacon(int numBacon);
    void listarContactos (Collection<TVertice> visitados, int maxSaltos);
    
        
}
