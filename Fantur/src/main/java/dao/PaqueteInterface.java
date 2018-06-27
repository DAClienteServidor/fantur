/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import modelo.Paquete;

/**
 *
 * @author usuario
 */
@Local
public interface PaqueteInterface {

    void create(Paquete paquete);

    void edit(Paquete paquete);

    void remove(Paquete paquete);

    Paquete find(Object id);
    
    Paquete findById(int id);

    List<Paquete> findAll();

    List<Paquete> findRange(int[] range);

    int count();
    
}
