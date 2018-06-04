/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import modelo.Contrata;

/**
 *
 * @author usuario
 */
@Local
public interface ContrataInterface {

    void create(Contrata contrata);

    void edit(Contrata contrata);

    void remove(Contrata contrata);

    Contrata find(Object id);

    List<Contrata> findAll();

    List<Contrata> findRange(int[] range);

    int count();
    
}
