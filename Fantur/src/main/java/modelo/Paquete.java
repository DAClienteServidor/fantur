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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "paquete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paquete.findAll", query = "SELECT p FROM Paquete p")
    , @NamedQuery(name = "Paquete.findByIdpaquete", query = "SELECT p FROM Paquete p WHERE p.idpaquete = :idpaquete")
    , @NamedQuery(name = "Paquete.findByNombre", query = "SELECT p FROM Paquete p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Paquete.findByFechaExpiracion", query = "SELECT p FROM Paquete p WHERE p.fechaExpiracion = :fechaExpiracion")
    , @NamedQuery(name = "Paquete.findByPrecio", query = "SELECT p FROM Paquete p WHERE p.precio = :precio")})
public class Paquete implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpaquete")
    private Integer idpaquete;
    
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaExpiracion")
    private Date fechaExpiracion;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paquete1")
    private List<Contrata> contrataList;
    @JoinColumn(name = "alojamiento", referencedColumnName = "id")
    @ManyToOne
    private Alojamiento alojamiento;
    @JoinColumn(name = "entretenimiento", referencedColumnName = "id")
    @ManyToOne
    private Entretenimiento entretenimiento;
    @JoinColumn(name = "pasaje", referencedColumnName = "id")
    @ManyToOne
    private Pasaje pasaje;

    public Paquete() {
    }

    public Paquete(Integer idpaquete) {
        this.idpaquete = idpaquete;
    }

    public Paquete(Integer idpaquete, Date fechaExpiracion, BigDecimal precio) {
        this.idpaquete = idpaquete;
        this.fechaExpiracion = fechaExpiracion;
        this.precio = precio;
    }

    public Integer getIdpaquete() {
        return idpaquete;
    }

    public void setIdpaquete(Integer idpaquete) {
        this.idpaquete = idpaquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    @XmlTransient
    public List<Contrata> getContrataList() {
        return contrataList;
    }

    public void setContrataList(List<Contrata> contrataList) {
        this.contrataList = contrataList;
    }

    public Alojamiento getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public Entretenimiento getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(Entretenimiento entretenimiento) {
        this.entretenimiento = entretenimiento;
    }

    public Pasaje getPasaje() {
        return pasaje;
    }

    public void setPasaje(Pasaje pasaje) {
        this.pasaje = pasaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaquete != null ? idpaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paquete)) {
            return false;
        }
        Paquete other = (Paquete) object;
        if ((this.idpaquete == null && other.idpaquete != null) || (this.idpaquete != null && !this.idpaquete.equals(other.idpaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Paquete[ idpaquete=" + idpaquete + " ]";
    }
    
}
