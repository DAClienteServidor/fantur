/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Local;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
@Local
public interface UsuarioInterface {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    
    Usuario findByUsuario(String username);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    public Usuario iniciarSesion(String usu, String pas) throws Exception;
      
}
