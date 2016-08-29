/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m"),
    @NamedQuery(name = "Municipio.findByIdMunicipio", query = "SELECT m FROM Municipio m WHERE m.idMunicipio = :idMunicipio"),
    @NamedQuery(name = "Municipio.findByDsMunicipio", query = "SELECT m FROM Municipio m WHERE m.dsMunicipio = :dsMunicipio")})
public class Municipio implements Serializable {

    @OneToMany(mappedBy = "idMunicipio")
    private Collection<Contratante> contratanteCollection;
    @OneToMany(mappedBy = "idMunicipioRepresLegal")
    private Collection<Contratante> contratanteCollection1;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_municipio")
    private Integer idMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_municipio")
    private String dsMunicipio;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMunicipio")
    private Collection<LocalEvento> localEventoCollection;

    public Municipio() {
    }

    public Municipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Municipio(Integer idMunicipio, String dsMunicipio) {
        this.idMunicipio = idMunicipio;
        this.dsMunicipio = dsMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDsMunicipio() {
        return dsMunicipio;
    }

    public void setDsMunicipio(String dsMunicipio) {
        this.dsMunicipio = dsMunicipio;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @XmlTransient
    public Collection<LocalEvento> getLocalEventoCollection() {
        return localEventoCollection;
    }

    public void setLocalEventoCollection(Collection<LocalEvento> localEventoCollection) {
        this.localEventoCollection = localEventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMunicipio != null ? idMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.idMunicipio == null && other.idMunicipio != null) || (this.idMunicipio != null && !this.idMunicipio.equals(other.idMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.Municipio[ idMunicipio=" + idMunicipio + " ]";
    }

    @XmlTransient
    public Collection<Contratante> getContratanteCollection() {
        return contratanteCollection;
    }

    public void setContratanteCollection(Collection<Contratante> contratanteCollection) {
        this.contratanteCollection = contratanteCollection;
    }

    @XmlTransient
    public Collection<Contratante> getContratanteCollection1() {
        return contratanteCollection1;
    }

    public void setContratanteCollection1(Collection<Contratante> contratanteCollection1) {
        this.contratanteCollection1 = contratanteCollection1;
    }
    
}
