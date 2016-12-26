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
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_tipo_meio_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoMeioPagamento.findAll", query = "SELECT t FROM TipoMeioPagamento t ORDER BY t.dsTipoMeioPagamento"),
    @NamedQuery(name = "TipoMeioPagamento.findDsTipoMeioPagamentoId", query = "SELECT t FROM TipoMeioPagamento t WHERE t.inTipoMeioPagamento = :inTipoMeioPagamento AND t.dsTipoMeioPagamento = :dsTipoMeioPagamento"),
    @NamedQuery(name = "TipoMeioPagamento.findByInTipoMeioPagamento", query = "SELECT t FROM TipoMeioPagamento t WHERE t.inTipoMeioPagamento = :inTipoMeioPagamento"),
    @NamedQuery(name = "TipoMeioPagamento.findByDsTipoMeioPagamento", query = "SELECT t FROM TipoMeioPagamento t WHERE t.dsTipoMeioPagamento = :dsTipoMeioPagamento")})
public class TipoMeioPagamento implements Serializable {

    @OneToMany(mappedBy = "inTipoMeioPagamento")
    private Collection<FormaPagamento> formaPagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "in_tipo_meio_pagamento")
    private String inTipoMeioPagamento;
    @Size(max = 30)
    @Column(name = "ds_tipo_meio_pagamento")
    private String dsTipoMeioPagamento;

    public TipoMeioPagamento() {
    }

    public TipoMeioPagamento(String inTipoMeioPagamento) {
        this.inTipoMeioPagamento = inTipoMeioPagamento;
    }

    public String getInTipoMeioPagamento() {
        return inTipoMeioPagamento;
    }

    public void setInTipoMeioPagamento(String inTipoMeioPagamento) {
        this.inTipoMeioPagamento = inTipoMeioPagamento;
    }

    public String getDsTipoMeioPagamento() {
        return dsTipoMeioPagamento;
    }

    public void setDsTipoMeioPagamento(String dsTipoMeioPagamento) {
        this.dsTipoMeioPagamento = dsTipoMeioPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inTipoMeioPagamento != null ? inTipoMeioPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoMeioPagamento)) {
            return false;
        }
        TipoMeioPagamento other = (TipoMeioPagamento) object;
        if ((this.inTipoMeioPagamento == null && other.inTipoMeioPagamento != null) || (this.inTipoMeioPagamento != null && !this.inTipoMeioPagamento.equals(other.inTipoMeioPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsTipoMeioPagamento;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<FormaPagamento> getFormaPagamentoCollection() {
        return formaPagamentoCollection;
    }

    public void setFormaPagamentoCollection(Collection<FormaPagamento> formaPagamentoCollection) {
        this.formaPagamentoCollection = formaPagamentoCollection;
    }
    
}
