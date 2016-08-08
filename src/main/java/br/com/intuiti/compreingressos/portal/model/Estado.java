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
@Table(name = "mw_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estado.findAll", query = "SELECT e FROM Estado e"),
    @NamedQuery(name = "Estado.findByIdEstado", query = "SELECT e FROM Estado e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "Estado.findByDsEstado", query = "SELECT e FROM Estado e WHERE e.dsEstado = :dsEstado"),
    @NamedQuery(name = "Estado.findBySgEstado", query = "SELECT e FROM Estado e WHERE e.sgEstado = :sgEstado")})
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_estado", columnDefinition = "integer")
    private Short idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ds_estado")
    private String dsEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "sg_estado")
    private String sgEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<Municipio> municipioCollection;
    @JoinColumn(name = "id_regiao_geografica", referencedColumnName = "id_regiao_geografica")
    @ManyToOne(optional = false)
    private RegiaoGeografica idRegiaoGeografica;

    public Estado() {
    }

    public Estado(Short idEstado) {
        this.idEstado = idEstado;
    }

    public Estado(Short idEstado, String dsEstado, String sgEstado) {
        this.idEstado = idEstado;
        this.dsEstado = dsEstado;
        this.sgEstado = sgEstado;
    }

    public Short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Short idEstado) {
        this.idEstado = idEstado;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public String getSgEstado() {
        return sgEstado;
    }

    public void setSgEstado(String sgEstado) {
        this.sgEstado = sgEstado;
    }

    @XmlTransient
    public Collection<Municipio> getMunicipioCollection() {
        return municipioCollection;
    }

    public void setMunicipioCollection(Collection<Municipio> municipioCollection) {
        this.municipioCollection = municipioCollection;
    }

    public RegiaoGeografica getIdRegiaoGeografica() {
        return idRegiaoGeografica;
    }

    public void setIdRegiaoGeografica(RegiaoGeografica idRegiaoGeografica) {
        this.idRegiaoGeografica = idRegiaoGeografica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.Estado[ idEstado=" + idEstado + " ]";
    }
    
}
