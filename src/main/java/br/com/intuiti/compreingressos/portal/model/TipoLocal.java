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
@Table(name = "mw_tipo_local")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLocal.findAll", query = "SELECT t FROM TipoLocal t"),
    @NamedQuery(name = "TipoLocal.findByIdTipoLocal", query = "SELECT t FROM TipoLocal t WHERE t.idTipoLocal = :idTipoLocal"),
    @NamedQuery(name = "TipoLocal.findByDsTipoLocal", query = "SELECT t FROM TipoLocal t WHERE t.dsTipoLocal = :dsTipoLocal"),
    @NamedQuery(name = "TipoLocal.findByDsTipoLocalId", query = "SELECT t FROM TipoLocal t WHERE t.dsTipoLocal = :dsTipoLocal AND t.idTipoLocal <> :idTipoLocal"),
    @NamedQuery(name = "TipoLocal.findByInAtivo", query = "SELECT t FROM TipoLocal t WHERE t.inAtivo = :inAtivo")})
public class TipoLocal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_local")
    private Integer idTipoLocal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ds_tipo_local")
    private String dsTipoLocal;
    @Column(name = "in_ativo")
    private Boolean inAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoLocal")
    private Collection<LocalEvento> localEventoCollection;

    public TipoLocal() {
    }

    public TipoLocal(Integer idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }

    public TipoLocal(Integer idTipoLocal, String dsTipoLocal) {
        this.idTipoLocal = idTipoLocal;
        this.dsTipoLocal = dsTipoLocal;
    }

    public Integer getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setIdTipoLocal(Integer idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }

    public String getDsTipoLocal() {
        return dsTipoLocal;
    }

    public void setDsTipoLocal(String dsTipoLocal) {
        this.dsTipoLocal = dsTipoLocal;
    }

    public Boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Boolean inAtivo) {
        this.inAtivo = inAtivo;
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
        hash += (idTipoLocal != null ? idTipoLocal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoLocal)) {
            return false;
        }
        TipoLocal other = (TipoLocal) object;
        if ((this.idTipoLocal == null && other.idTipoLocal != null) || (this.idTipoLocal != null && !this.idTipoLocal.equals(other.idTipoLocal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsTipoLocal;
    }
    
}
