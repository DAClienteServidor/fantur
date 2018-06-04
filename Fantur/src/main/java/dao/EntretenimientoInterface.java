/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import modelo.Entretenimiento;

/**
 *
 * @author usuario
 */
@Local
public interface EntretenimientoInterface {

    void create(Entretenimiento entretenimiento);

    void edit(Entretenimiento entretenimiento);

    void remove(Entretenimiento entretenimiento);

    Entretenimiento find(Object id);

    List<Entretenimiento> findAll();

    List<Entretenimiento> findRange(int[] range);

    int count();
    
}
