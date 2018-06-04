/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "entretenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entretenimiento.findAll", query = "SELECT e FROM Entretenimiento e")
    , @NamedQuery(name = "Entretenimiento.findById", query = "SELECT e FROM Entretenimiento e WHERE e.id = :id")
    , @NamedQuery(name = "Entretenimiento.findByNombre", query = "SELECT e FROM Entretenimiento e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Entretenimiento.findByFecha", query = "SELECT e FROM Entretenimiento e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "Entretenimiento.findByHora", query = "SELECT e FROM Entretenimiento e WHERE e.hora = :hora")
    , @NamedQuery(name = "Entretenimiento.findByLugar", query = "SELECT e FROM Entretenimiento e WHERE e.lugar = :lugar")
    , @NamedQuery(name = "Entretenimiento.findByDescripcion", query = "SELECT e FROM Entretenimiento e WHERE e.descripcion = :descripcion")})
public class Entretenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "entretenimiento")
    private List<Paquete> paqueteList;

    public Entretenimiento() {
    }

    public Entretenimiento(Integer id) {
        this.id = id;
    }

    public Entretenimiento(Integer id, String nombre, Date fecha, Date hora, String lugar, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Paquete> getPaqueteList() {
        return paqueteList;
    }

    public void setPaqueteList(List<Paquete> paqueteList) {
        this.paqueteList = paqueteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entretenimiento)) {
            return false;
        }
        Entretenimiento other = (Entretenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Entretenimiento[ id=" + id + " ]";
    }
    
}
