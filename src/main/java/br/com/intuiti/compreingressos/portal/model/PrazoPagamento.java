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
@Table(name = "mw_prazo_pagamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrazoPagamento.findAll", query = "SELECT p FROM PrazoPagamento p ORDER BY p.dsPrazoPagamento"),
    @NamedQuery(name = "PrazoPagamento.findByIdPrazoPagamento", query = "SELECT p FROM PrazoPagamento p WHERE p.idPrazoPagamento = :idPrazoPagamento"),
    @NamedQuery(name = "PrazoPagamento.findByDsPrazoPagamento", query = "SELECT p FROM PrazoPagamento p WHERE p.dsPrazoPagamento = :dsPrazoPagamento"),
    @NamedQuery(name = "PrazoPagamento.findByInDiaSemana", query = "SELECT p FROM PrazoPagamento p WHERE p.inDiaSemana = :inDiaSemana"),
    @NamedQuery(name = "PrazoPagamento.findByQtDiasPrazo", query = "SELECT p FROM PrazoPagamento p WHERE p.qtDiasPrazo = :qtDiasPrazo"),
    @NamedQuery(name = "PrazoPagamento.findByDsDiasFixos", query = "SELECT p FROM PrazoPagamento p WHERE p.dsDiasFixos = :dsDiasFixos")})
public class PrazoPagamento implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrazoPagamento")
    private Collection<ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prazo_pagamento")
    private Integer idPrazoPagamento;
    @Size(max = 60)
    @Column(name = "ds_prazo_pagamento")
    private String dsPrazoPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_dia_semana")
    private Integer inDiaSemana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt_dias_prazo")
    private Integer qtDiasPrazo;
    @Size(max = 20)
    @Column(name = "ds_dias_fixos")
    private String dsDiasFixos;

    public PrazoPagamento() {
    }

    public PrazoPagamento(Integer idPrazoPagamento) {
        this.idPrazoPagamento = idPrazoPagamento;
    }

    public PrazoPagamento(Integer idPrazoPagamento, Integer inDiaSemana, Integer qtDiasPrazo) {
        this.idPrazoPagamento = idPrazoPagamento;
        this.inDiaSemana = inDiaSemana;
        this.qtDiasPrazo = qtDiasPrazo;
    }
    
    public Integer getIdPrazoPagamento() {
        return idPrazoPagamento;
    }

    public void setIdPrazoPagamento(Integer idPrazoPagamento) {
        this.idPrazoPagamento = idPrazoPagamento;
    }

    public String getDsPrazoPagamento() {
        return dsPrazoPagamento;
    }

    public void setDsPrazoPagamento(String dsPrazoPagamento) {
        this.dsPrazoPagamento = dsPrazoPagamento;
    }

    public Integer getInDiaSemana() {
        return inDiaSemana;
    }

    public void setInDiaSemana(Integer inDiaSemana) {
        this.inDiaSemana = inDiaSemana;
    }

    public Integer getQtDiasPrazo() {
        return qtDiasPrazo;
    }

    public void setQtDiasPrazo(Integer qtDiasPrazo) {
        this.qtDiasPrazo = qtDiasPrazo;
    }

    public String getDsDiasFixos() {
        return dsDiasFixos;
    }

    public void setDsDiasFixos(String dsDiasFixos) {
        this.dsDiasFixos = dsDiasFixos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrazoPagamento != null ? idPrazoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PrazoPagamento)) {
            return false;
        }
        PrazoPagamento other = (PrazoPagamento) object;
        if ((this.idPrazoPagamento == null && other.idPrazoPagamento != null) || (this.idPrazoPagamento != null && !this.idPrazoPagamento.equals(other.idPrazoPagamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsPrazoPagamento;
    }

    @XmlTransient
    public Collection<ContratoClientePrazoPagamento> getContratoClientePrazoPagamentoCollection() {
        return contratoClientePrazoPagamentoCollection;
    }

    public void setContratoClientePrazoPagamentoCollection(Collection<ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection) {
        this.contratoClientePrazoPagamentoCollection = contratoClientePrazoPagamentoCollection;
    }

}
