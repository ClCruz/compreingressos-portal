/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_tipo_lancamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoLancamento.findAll", query = "SELECT t FROM TipoLancamento t"),
    @NamedQuery(name = "TipoLancamento.findByIdTipoLancamento", query = "SELECT t FROM TipoLancamento t WHERE t.idTipoLancamento = :idTipoLancamento"),
    @NamedQuery(name = "TipoLancamento.findByDsTipoLancamento", query = "SELECT t FROM TipoLancamento t WHERE t.dsTipoLancamento = :dsTipoLancamento"),
    @NamedQuery(name = "TipoLancamento.findByInAtivo", query = "SELECT t FROM TipoLancamento t WHERE t.inAtivo = :inAtivo"),
    @NamedQuery(name = "TipoLancamento.findByDtInsert", query = "SELECT t FROM TipoLancamento t WHERE t.dtInsert = :dtInsert"),
    @NamedQuery(name = "TipoLancamento.findByDtUpdate", query = "SELECT t FROM TipoLancamento t WHERE t.dtUpdate = :dtUpdate"),
    @NamedQuery(name = "TipoLancamento.findByInPcValor", query = "SELECT t FROM TipoLancamento t WHERE t.inPcValor = :inPcValor"),
    @NamedQuery(name = "TipoLancamento.findByInAplicacaoRegra", query = "SELECT t FROM TipoLancamento t WHERE t.inAplicacaoRegra = :inAplicacaoRegra"),
    @NamedQuery(name = "TipoLancamento.findByInValidoBordero", query = "SELECT t FROM TipoLancamento t WHERE t.inValidoBordero = :inValidoBordero"),
    @NamedQuery(name = "TipoLancamento.findByInValidoExtrato", query = "SELECT t FROM TipoLancamento t WHERE t.inValidoExtrato = :inValidoExtrato"),
    @NamedQuery(name = "TipoLancamento.findByInAcrescentaSubstrai", query = "SELECT t FROM TipoLancamento t WHERE t.inAcrescentaSubstrai = :inAcrescentaSubstrai"),
    @NamedQuery(name = "TipoLancamento.findByIdFormaPagamento", query = "SELECT t FROM TipoLancamento t WHERE t.idFormaPagamento = :idFormaPagamento")})
public class TipoLancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_lancamento")
    private Integer idTipoLancamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_tipo_lancamento")
    private String dsTipoLancamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @Column(name = "dt_insert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInsert;
    @Column(name = "dt_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;
    @Size(max = 20)
    @Column(name = "in_pc_valor")
    private String inPcValor;
    @Column(name = "in_aplicacao_regra")
    private Character inAplicacaoRegra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_valido_bordero")
    private boolean inValidoBordero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_valido_extrato")
    private boolean inValidoExtrato;
    @Size(max = 20)
    @Column(name = "in_acrescenta_substrai")
    private String inAcrescentaSubstrai;
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id_forma_pagamento")
    @ManyToOne
    private FormaPagamento idFormaPagamento;
    @JoinColumn(name = "id_canal_venda", referencedColumnName = "id_canal_venda")
    @ManyToOne
    private CanalVenda idCanalVenda;
    @JoinColumn(name = "id_conta_contabil_deb", referencedColumnName = "id_conta_contabil")
    @ManyToOne
    private ContaContabil idContaContabilDeb;
    @JoinColumn(name = "id_conta_contabil_cre", referencedColumnName = "id_conta_contabil")
    @ManyToOne
    private ContaContabil idContaContabilCre;

    public TipoLancamento() {
    }

    public TipoLancamento(Integer idTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
    }

    public TipoLancamento(Integer idTipoLancamento, String dsTipoLancamento, boolean inAtivo, boolean inValidoBordero, boolean inValidoExtrato) {
        this.idTipoLancamento = idTipoLancamento;
        this.dsTipoLancamento = dsTipoLancamento;
        this.inAtivo = inAtivo;
        this.inValidoBordero = inValidoBordero;
        this.inValidoExtrato = inValidoExtrato;
    }

    public Integer getIdTipoLancamento() {
        return idTipoLancamento;
    }

    public void setIdTipoLancamento(Integer idTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
    }

    public String getDsTipoLancamento() {
        return dsTipoLancamento;
    }

    public void setDsTipoLancamento(String dsTipoLancamento) {
        this.dsTipoLancamento = dsTipoLancamento;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Date getDtInsert() {
        return dtInsert;
    }

    public void setDtInsert(Date dtInsert) {
        this.dtInsert = dtInsert;
    }

    public Date getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(Date dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getInPcValor() {
        return inPcValor;
    }

    public void setInPcValor(String inPcValor) {
        this.inPcValor = inPcValor;
    }

    public Character getInAplicacaoRegra() {
        return inAplicacaoRegra;
    }

    public void setInAplicacaoRegra(Character inAplicacaoRegra) {
        this.inAplicacaoRegra = inAplicacaoRegra;
    }

    public boolean getInValidoBordero() {
        return inValidoBordero;
    }

    public void setInValidoBordero(boolean inValidoBordero) {
        this.inValidoBordero = inValidoBordero;
    }

    public boolean getInValidoExtrato() {
        return inValidoExtrato;
    }

    public void setInValidoExtrato(boolean inValidoExtrato) {
        this.inValidoExtrato = inValidoExtrato;
    }

    public String getInAcrescentaSubstrai() {
        return inAcrescentaSubstrai;
    }

    public void setInAcrescentaSubstrai(String inAcrescentaSubstrai) {
        this.inAcrescentaSubstrai = inAcrescentaSubstrai;
    }

    public FormaPagamento getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public CanalVenda getIdCanalVenda() {
        return idCanalVenda;
    }

    public void setIdCanalVenda(CanalVenda idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }

    public ContaContabil getIdContaContabilDeb() {
        return idContaContabilDeb;
    }

    public void setIdContaContabilDeb(ContaContabil idContaContabilDeb) {
        this.idContaContabilDeb = idContaContabilDeb;
    }

    public ContaContabil getIdContaContabilCre() {
        return idContaContabilCre;
    }

    public void setIdContaContabilCre(ContaContabil idContaContabilCre) {
        this.idContaContabilCre = idContaContabilCre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoLancamento != null ? idTipoLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoLancamento)) {
            return false;
        }
        TipoLancamento other = (TipoLancamento) object;
        if ((this.idTipoLancamento == null && other.idTipoLancamento != null) || (this.idTipoLancamento != null && !this.idTipoLancamento.equals(other.idTipoLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsTipoLancamento;
    }
    
}
