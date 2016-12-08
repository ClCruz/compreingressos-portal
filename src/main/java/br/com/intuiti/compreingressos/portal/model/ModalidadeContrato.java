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
@Table(name = "mw_modalidade_contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModalidadeContrato.findAll", query = "SELECT m FROM ModalidadeContrato m ORDER BY m.dsModalidadeContrato"),
    @NamedQuery(name = "ModalidadeContrato.findByIdModalidadeContrato", query = "SELECT m FROM ModalidadeContrato m WHERE m.idModalidadeContrato = :idModalidadeContrato"),
    @NamedQuery(name = "ModalidadeContrato.findByDsModalidadeContrato", query = "SELECT m FROM ModalidadeContrato m WHERE m.dsModalidadeContrato = :dsModalidadeContrato"),
    @NamedQuery(name = "ModalidadeContrato.findByDsModalidadeContratoId", query = "SELECT m FROM ModalidadeContrato m WHERE m.dsModalidadeContrato = :dsModalidadeContrato AND m.idModalidadeContrato <> :idModalidadeContrato")})
public class ModalidadeContrato implements Serializable {

    @OneToMany(mappedBy = "idModalidadeContrato")
    private Collection<ContratoCliente> contratoClienteCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Basic(optional = false)
    @Column(name = "id_modalidade_contrato", columnDefinition = "integer")
    private Integer idModalidadeContrato;
    @Size(max = 30)
    @Column(name = "ds_modalidade_contrato")
    private String dsModalidadeContrato;

    public ModalidadeContrato() {
    }

    public ModalidadeContrato(Integer idModalidadeContrato) {
        this.idModalidadeContrato = idModalidadeContrato;
    }

    public Integer getIdModalidadeContrato() {
        return idModalidadeContrato;
    }

    public void setIdModalidadeContrato(Integer idModalidadeContrato) {
        this.idModalidadeContrato = idModalidadeContrato;
    }

    public String getDsModalidadeContrato() {
        return dsModalidadeContrato;
    }

    public void setDsModalidadeContrato(String dsModalidadeContrato) {
        this.dsModalidadeContrato = dsModalidadeContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModalidadeContrato != null ? idModalidadeContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ModalidadeContrato)) {
            return false;
        }
        ModalidadeContrato other = (ModalidadeContrato) object;
        if ((this.idModalidadeContrato == null && other.idModalidadeContrato != null) || (this.idModalidadeContrato != null && !this.idModalidadeContrato.equals(other.idModalidadeContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsModalidadeContrato;
    }

    @XmlTransient
    public Collection<ContratoCliente> getContratoClienteCollection() {
        return contratoClienteCollection;
    }

    public void setContratoClienteCollection(Collection<ContratoCliente> contratoClienteCollection) {
        this.contratoClienteCollection = contratoClienteCollection;
    }
    
}
