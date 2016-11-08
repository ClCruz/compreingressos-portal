/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "mw_doc_estrangeiro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocEstrangeiro.findAll", query = "SELECT d FROM DocEstrangeiro d"),
    @NamedQuery(name = "DocEstrangeiro.findByIdDocEstrangeiro", query = "SELECT d FROM DocEstrangeiro d WHERE d.idDocEstrangeiro = :idDocEstrangeiro"),
    @NamedQuery(name = "DocEstrangeiro.findByDsDocEstrangeiro", query = "SELECT d FROM DocEstrangeiro d WHERE d.dsDocEstrangeiro = :dsDocEstrangeiro")})
public class DocEstrangeiro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_doc_estrangeiro")
    private Integer idDocEstrangeiro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ds_doc_estrangeiro")
    private String dsDocEstrangeiro;
    @OneToMany(mappedBy = "idDocEstrangeiro")
    private Collection<Cliente> clienteCollection;

    public DocEstrangeiro() {
    }

    public DocEstrangeiro(Integer idDocEstrangeiro) {
        this.idDocEstrangeiro = idDocEstrangeiro;
    }

    public DocEstrangeiro(Integer idDocEstrangeiro, String dsDocEstrangeiro) {
        this.idDocEstrangeiro = idDocEstrangeiro;
        this.dsDocEstrangeiro = dsDocEstrangeiro;
    }

    public Integer getIdDocEstrangeiro() {
        return idDocEstrangeiro;
    }

    public void setIdDocEstrangeiro(Integer idDocEstrangeiro) {
        this.idDocEstrangeiro = idDocEstrangeiro;
    }

    public String getDsDocEstrangeiro() {
        return dsDocEstrangeiro;
    }

    public void setDsDocEstrangeiro(String dsDocEstrangeiro) {
        this.dsDocEstrangeiro = dsDocEstrangeiro;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocEstrangeiro != null ? idDocEstrangeiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DocEstrangeiro)) {
            return false;
        }
        DocEstrangeiro other = (DocEstrangeiro) object;
        if ((this.idDocEstrangeiro == null && other.idDocEstrangeiro != null) || (this.idDocEstrangeiro != null && !this.idDocEstrangeiro.equals(other.idDocEstrangeiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.DocEstrangeiro[ idDocEstrangeiro=" + idDocEstrangeiro + " ]";
    }
    
}
