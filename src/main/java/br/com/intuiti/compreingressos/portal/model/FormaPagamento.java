/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_forma_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaPagamento.findAll", query = "SELECT f FROM FormaPagamento f"),
    @NamedQuery(name = "FormaPagamento.findByIdFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.idFormaPagamento = :idFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByDsFormaPagamento", query = "SELECT f FROM FormaPagamento f WHERE f.dsFormaPagamento = :dsFormaPagamento"),
    @NamedQuery(name = "FormaPagamento.findByInAtivo", query = "SELECT f FROM FormaPagamento f WHERE f.inAtivo = :inAtivo")})
public class FormaPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_forma_pagamento")
    private Integer idFormaPagamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = "O campo Descrição deve ter entre 1 e 30 caracteres")
    @Column(name = "ds_forma_pagamento")
    private String dsFormaPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @JoinColumn(name = "in_tipo_meio_pagamento", referencedColumnName = "in_tipo_meio_pagamento")
    @ManyToOne
    private TipoMeioPagamento inTipoMeioPagamento;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public FormaPagamento(Integer idFormaPagamento, String dsFormaPagamento, boolean inAtivo) {
        this.idFormaPagamento = idFormaPagamento;
        this.dsFormaPagamento = dsFormaPagamento;
        this.inAtivo = inAtivo;
    }

    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getDsFormaPagamento() {
        return dsFormaPagamento;
    }

    public void setDsFormaPagamento(String dsFormaPagamento) {
        this.dsFormaPagamento = dsFormaPagamento;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public TipoMeioPagamento getInTipoMeioPagamento() {
        return inTipoMeioPagamento;
    }

    public void setInTipoMeioPagamento(TipoMeioPagamento inTipoMeioPagamento) {
        this.inTipoMeioPagamento = inTipoMeioPagamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormaPagamento != null ? idFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaPagamento)) {
            return false;
        }
        FormaPagamento other = (FormaPagamento) object;
        if ((this.idFormaPagamento == null && other.idFormaPagamento != null) || (this.idFormaPagamento != null && !this.idFormaPagamento.equals(other.idFormaPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.FormaPagamento[ idFormaPagamento=" + idFormaPagamento + " ]";
    }
    
}
