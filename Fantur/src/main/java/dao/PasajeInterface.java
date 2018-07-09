/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import modelo.Pasaje;

/**
 *
 * @author usuario
 */
@Local
public interface PasajeInterface {

    void create(Pasaje pasaje);

    void edit(Pasaje pasaje);

    void remove(Pasaje pasaje);

    Pasaje find(Object id);

    List<Pasaje> findAll();
    
    List<Pasaje> findByAlgo(String consulta);

    List<Pasaje> findRange(int[] range);

    int count();
    
}
