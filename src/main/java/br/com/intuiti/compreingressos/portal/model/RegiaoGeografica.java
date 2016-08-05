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
@Table(name = "mw_regiao_geografica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegiaoGeografica.findAll", query = "SELECT r FROM RegiaoGeografica r"),
    @NamedQuery(name = "RegiaoGeografica.findByIdRegiaoGeografica", query = "SELECT r FROM RegiaoGeografica r WHERE r.idRegiaoGeografica = :idRegiaoGeografica"),
    @NamedQuery(name = "RegiaoGeografica.findByDsRegiaoGeografica", query = "SELECT r FROM RegiaoGeografica r WHERE r.dsRegiaoGeografica = :dsRegiaoGeografica")})
public class RegiaoGeografica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_regiao_geografica")
    private Integer idRegiaoGeografica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ds_regiao_geografica")
    private String dsRegiaoGeografica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRegiaoGeografica")
    private Collection<Estado> estadoCollection;

    public RegiaoGeografica() {
    }

    public RegiaoGeografica(Integer idRegiaoGeografica) {
        this.idRegiaoGeografica = idRegiaoGeografica;
    }

    public RegiaoGeografica(Integer idRegiaoGeografica, String dsRegiaoGeografica) {
        this.idRegiaoGeografica = idRegiaoGeografica;
        this.dsRegiaoGeografica = dsRegiaoGeografica;
    }

    public Integer getIdRegiaoGeografica() {
        return idRegiaoGeografica;
    }

    public void setIdRegiaoGeografica(Integer idRegiaoGeografica) {
        this.idRegiaoGeografica = idRegiaoGeografica;
    }

    public String getDsRegiaoGeografica() {
        return dsRegiaoGeografica;
    }

    public void setDsRegiaoGeografica(String dsRegiaoGeografica) {
        this.dsRegiaoGeografica = dsRegiaoGeografica;
    }

    @XmlTransient
    public Collection<Estado> getEstadoCollection() {
        return estadoCollection;
    }

    public void setEstadoCollection(Collection<Estado> estadoCollection) {
        this.estadoCollection = estadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegiaoGeografica != null ? idRegiaoGeografica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RegiaoGeografica)) {
            return false;
        }
        RegiaoGeografica other = (RegiaoGeografica) object;
        if ((this.idRegiaoGeografica == null && other.idRegiaoGeografica != null) || (this.idRegiaoGeografica != null && !this.idRegiaoGeografica.equals(other.idRegiaoGeografica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.RegiaoGeografica[ idRegiaoGeografica=" + idRegiaoGeografica + " ]";
    }
    
}
