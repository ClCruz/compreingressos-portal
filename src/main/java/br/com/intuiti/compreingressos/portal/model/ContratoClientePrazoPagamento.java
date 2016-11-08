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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_contrato_cliente_prazo_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoClientePrazoPagamento.findAll", query = "SELECT c FROM ContratoClientePrazoPagamento c"),
    @NamedQuery(name = "ContratoClientePrazoPagamento.findByIdContratoCliente", query = "SELECT c FROM ContratoClientePrazoPagamento c WHERE c.idContratoCliente = :idContrato"),
    @NamedQuery(name = "ContratoClientePrazoPagamento.findByIdContratoClientePrazoPagamento", query = "SELECT c FROM ContratoClientePrazoPagamento c WHERE c.idContratoClientePrazoPagamento = :idContratoClientePrazoPagamento")})
public class ContratoClientePrazoPagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato_cliente_prazo_pagamento")
    private Integer idContratoClientePrazoPagamento;
    @JoinColumn(name = "id_contrato_cliente", referencedColumnName = "id_contrato_cliente")
    @ManyToOne(optional = false)
    private ContratoCliente idContratoCliente;
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id_forma_pagamento")
    @ManyToOne
    private FormaPagamento idFormaPagamento;
    @JoinColumn(name = "id_prazo_pagamento", referencedColumnName = "id_prazo_pagamento")
    @ManyToOne(optional = false)
    private PrazoPagamento idPrazoPagamento;
    @Transient
    private int codigoProvisorio;

    public ContratoClientePrazoPagamento() {
    }
    
    public ContratoClientePrazoPagamento(Integer idContratoClientePrazoPagamento) {
        this.idContratoClientePrazoPagamento = idContratoClientePrazoPagamento;
    }

    public ContratoClientePrazoPagamento(PrazoPagamento idPrazoPagamento, FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
        this.idPrazoPagamento = idPrazoPagamento;
    }

    public ContratoClientePrazoPagamento(ContratoCliente get) {
        this.idContratoCliente = get;
    }

    public ContratoClientePrazoPagamento(ContratoCliente id, PrazoPagamento idPrazoPagamento, FormaPagamento idFormaPagamento) {
        this.idContratoCliente = id;
        this.idPrazoPagamento = idPrazoPagamento;
        this.idFormaPagamento = idFormaPagamento;
    }
    
    public ContratoClientePrazoPagamento(int codigoProvisorio, PrazoPagamento idPrazoPagamento, FormaPagamento idFormaPagamento) {
        this.codigoProvisorio = codigoProvisorio;
        this.idPrazoPagamento = idPrazoPagamento;
        this.idFormaPagamento = idFormaPagamento;
    }

    public Integer getIdContratoClientePrazoPagamento() {
        return idContratoClientePrazoPagamento;
    }

    public void setIdContratoClientePrazoPagamento(Integer idContratoClientePrazoPagamento) {
        this.idContratoClientePrazoPagamento = idContratoClientePrazoPagamento;
    }

    public ContratoCliente getIdContratoCliente() {
        return idContratoCliente;
    }

    public void setIdContratoCliente(ContratoCliente idContratoCliente) {
        this.idContratoCliente = idContratoCliente;
    }

    public FormaPagamento getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public PrazoPagamento getIdPrazoPagamento() {
        return idPrazoPagamento;
    }

    public void setIdPrazoPagamento(PrazoPagamento idPrazoPagamento) {
        this.idPrazoPagamento = idPrazoPagamento;
    }
    
    public int getCodigoProvisorio() {
        return codigoProvisorio;
    }

    public void setCodigoProvisorio(int codigoProvisorio) {
        this.codigoProvisorio = codigoProvisorio;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoClientePrazoPagamento != null ? idContratoClientePrazoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContratoClientePrazoPagamento)) {
            return false;
        }
        ContratoClientePrazoPagamento other = (ContratoClientePrazoPagamento) object;
        if ((this.idContratoClientePrazoPagamento == null && other.idContratoClientePrazoPagamento != null) || (this.idContratoClientePrazoPagamento != null && !this.idContratoClientePrazoPagamento.equals(other.idContratoClientePrazoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContratoClientePrazoPagamento[ idContratoClientePrazoPagamento=" + idContratoClientePrazoPagamento + " ]";
    }
    
}
