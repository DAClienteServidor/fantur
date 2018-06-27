/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "contrata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrata.findAll", query = "SELECT c FROM Contrata c")
    , @NamedQuery(name = "Contrata.findByFechaContrato", query = "SELECT c FROM Contrata c WHERE c.fechaContrato = :fechaContrato")
    , @NamedQuery(name = "Contrata.findByPermitido", query = "SELECT c FROM Contrata c WHERE c.permitido = :permitido")
    , @NamedQuery(name = "Contrata.findByPagado", query = "SELECT c FROM Contrata c WHERE c.pagado = :pagado")
    , @NamedQuery(name = "Contrata.findByUsuario", query = "SELECT c FROM Contrata c WHERE c.contrataPK.usuario = :usuario")
    , @NamedQuery(name = "Contrata.findByPaquete", query = "SELECT c FROM Contrata c WHERE c.contrataPK.paquete = :paquete")})
public class Contrata implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContrataPK contrataPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaContrato")
    @Temporal(TemporalType.DATE)
    private Date fechaContrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "permitido")
    private String permitido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "pagado")
    private String pagado;
    @JoinColumn(name = "paquete", referencedColumnName = "idpaquete", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Paquete paquete1;
    @JoinColumn(name = "usuario", referencedColumnName = "dni", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario1;

    public Contrata() {
    }

    public Contrata(ContrataPK contrataPK) {
        this.contrataPK = contrataPK;
    }

    public Contrata(ContrataPK contrataPK, Date fechaContrato, String permitido, String pagado) {
        this.contrataPK = contrataPK;
        this.fechaContrato = fechaContrato;
        this.permitido = permitido;
        this.pagado = pagado;
    }

    public Contrata(String usuario, int paquete) {
        this.contrataPK = new ContrataPK(usuario, paquete);
    }

    public ContrataPK getContrataPK() {
        return contrataPK;
    }

    public void setContrataPK(ContrataPK contrataPK) {
        this.contrataPK = contrataPK;
    }

    public Date getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public String getPermitido() {
        return permitido;
    }

    public void setPermitido(String permitido) {
        this.permitido = permitido;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public Paquete getPaquete1() {
        return paquete1;
    }

    public void setPaquete1(Paquete paquete1) {
        this.paquete1 = paquete1;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contrataPK != null ? contrataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrata)) {
            return false;
        }
        Contrata other = (Contrata) object;
        if ((this.contrataPK == null && other.contrataPK != null) || (this.contrataPK != null && !this.contrataPK.equals(other.contrataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Contrata[ contrataPK=" + contrataPK + " ]";
    }
    
}
