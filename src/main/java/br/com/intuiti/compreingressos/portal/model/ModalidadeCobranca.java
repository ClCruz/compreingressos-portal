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
@Table(name = "mw_modalidade_cobranca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModalidadeCobranca.findAll", query = "SELECT m FROM ModalidadeCobranca m"),
    @NamedQuery(name = "ModalidadeCobranca.findByIdModalidadeCobranca", query = "SELECT m FROM ModalidadeCobranca m WHERE m.idModalidadeCobranca = :idModalidadeCobranca"),
    @NamedQuery(name = "ModalidadeCobranca.findByDsModalidadeCobranca", query = "SELECT m FROM ModalidadeCobranca m WHERE m.dsModalidadeCobranca = :dsModalidadeCobranca")})
public class ModalidadeCobranca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_modalidade_cobranca")
    private Integer idModalidadeCobranca;
    @Size(max = 30)
    @Column(name = "ds_modalidade_cobranca")
    private String dsModalidadeCobranca;
    @OneToMany(mappedBy = "idModalidadeCobranca")
    private Collection<ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection;

    public ModalidadeCobranca() {
    }

    public ModalidadeCobranca(Integer idModalidadeCobranca) {
        this.idModalidadeCobranca = idModalidadeCobranca;
    }

    public Integer getIdModalidadeCobranca() {
        return idModalidadeCobranca;
    }

    public void setIdModalidadeCobranca(Integer idModalidadeCobranca) {
        this.idModalidadeCobranca = idModalidadeCobranca;
    }

    public String getDsModalidadeCobranca() {
        return dsModalidadeCobranca;
    }

    public void setDsModalidadeCobranca(String dsModalidadeCobranca) {
        this.dsModalidadeCobranca = dsModalidadeCobranca;
    }

    @XmlTransient
    public Collection<ContratoClienteTipoLancamento> getContratoClienteTipoLancamentoCollection() {
        return contratoClienteTipoLancamentoCollection;
    }

    public void setContratoClienteTipoLancamentoCollection(Collection<ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection) {
        this.contratoClienteTipoLancamentoCollection = contratoClienteTipoLancamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idModalidadeCobranca != null ? idModalidadeCobranca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModalidadeCobranca)) {
            return false;
        }
        ModalidadeCobranca other = (ModalidadeCobranca) object;
        if ((this.idModalidadeCobranca == null && other.idModalidadeCobranca != null) || (this.idModalidadeCobranca != null && !this.idModalidadeCobranca.equals(other.idModalidadeCobranca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ModalidadeCobranca[ idModalidadeCobranca=" + idModalidadeCobranca + " ]";
    }
    
}
