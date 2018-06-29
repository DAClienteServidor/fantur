/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "pasaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasaje.findAll", query = "SELECT p FROM Pasaje p")
    , @NamedQuery(name = "Pasaje.findById", query = "SELECT p FROM Pasaje p WHERE p.id = :id")
    , @NamedQuery(name = "Pasaje.findByFechaIda", query = "SELECT p FROM Pasaje p WHERE p.fechaIda = :fechaIda")
    , @NamedQuery(name = "Pasaje.findByFechaVuelta", query = "SELECT p FROM Pasaje p WHERE p.fechaVuelta = :fechaVuelta")
    , @NamedQuery(name = "Pasaje.findByOrigen", query = "SELECT p FROM Pasaje p WHERE p.origen = :origen")
    , @NamedQuery(name = "Pasaje.findByHora", query = "SELECT p FROM Pasaje p WHERE p.hora = :hora")
    , @NamedQuery(name = "Pasaje.findByTipo", query = "SELECT p FROM Pasaje p WHERE p.tipo = :tipo")})
public class Pasaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaIda")
    @Temporal(TemporalType.DATE)
    private Date fechaIda;
    @Column(name = "fechaVuelta")
    @Temporal(TemporalType.DATE)
    private Date fechaVuelta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "origen")
    private String origen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "destino")
    private String destino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hora")
    private String hora;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipo")
    private String tipo;
    @OneToMany(mappedBy = "pasaje")
    private List<Paquete> paqueteList;

    public Pasaje() {
    }

    public Pasaje(Integer id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Pasaje(Integer id, Date fechaIda, String origen, String hora, String tipo) {
        this.id = id;
        this.fechaIda = fechaIda;
        this.origen = origen;
        this.hora = hora;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(Date fechaIda) {
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        if (!(object instanceof Pasaje)) {
            return false;
        }
        Pasaje other = (Pasaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Pasaje[ id=" + id + " ]";
    }
    
}
