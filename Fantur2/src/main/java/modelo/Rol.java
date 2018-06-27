/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")
    , @NamedQuery(name = "Rol.findByRol", query = "SELECT r FROM Rol r WHERE r.rol = :rol")
    , @NamedQuery(name = "Rol.findByUsuarioDni", query = "SELECT r FROM Rol r WHERE r.usuarioDni = :usuarioDni")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "rol")
    private String rol;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "usuario_dni")
    private String usuarioDni;
    @JoinColumn(name = "usuario_dni", referencedColumnName = "dni", insertable = false, updatable = false)
    @OneToOne(optional = true)
    private Usuario usuario;

    public Rol() {
    }

    public Rol(String usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public Rol(String usuarioDni, String rol) {
        this.usuarioDni = usuarioDni;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuarioDni() {
        return usuarioDni;
    }

    public void setUsuarioDni(String usuarioDni) {
        this.usuarioDni = usuarioDni;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioDni != null ? usuarioDni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.usuarioDni == null && other.usuarioDni != null) || (this.usuarioDni != null && !this.usuarioDni.equals(other.usuarioDni))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Rol[ usuarioDni=" + usuarioDni + " ]";
    }
    
}
