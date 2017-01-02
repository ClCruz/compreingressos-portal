/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author gabrielqueiroz
 */
@Entity
@Table(name = "mw_conta_corrente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaCorrente.findAll", query = "SELECT c FROM ContaCorrente c")
    , @NamedQuery(name = "ContaCorrente.findByDtHoraTransacao", query = "SELECT c FROM ContaCorrente c WHERE c.dtHoraTransacao = :dtHoraTransacao")
    , @NamedQuery(name = "ContaCorrente.findByIdTransacao", query = "SELECT c FROM ContaCorrente c WHERE c.contaCorrentePK.idTransacao = :idTransacao")
    , @NamedQuery(name = "ContaCorrente.findByCdTransacao", query = "SELECT c FROM ContaCorrente c WHERE c.cdTransacao = :cdTransacao")
    , @NamedQuery(name = "ContaCorrente.findByIdContratoCliente", query = "SELECT c FROM ContaCorrente c WHERE c.contaCorrentePK.idContratoCliente = :idContratoCliente")
    , @NamedQuery(name = "ContaCorrente.findByVlMovimento", query = "SELECT c FROM ContaCorrente c WHERE c.vlMovimento = :vlMovimento")
    , @NamedQuery(name = "ContaCorrente.findByQtMovimento", query = "SELECT c FROM ContaCorrente c WHERE c.qtMovimento = :qtMovimento")
    , @NamedQuery(name = "ContaCorrente.findByDsObservacao", query = "SELECT c FROM ContaCorrente c WHERE c.dsObservacao = :dsObservacao")
    , @NamedQuery(name = "ContaCorrente.maxIdTransacao", query = "SELECT MAX(c.contaCorrentePK.idTransacao) + 1 FROM ContaCorrente c")})
public class ContaCorrente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContaCorrentePK contaCorrentePK;
    @Size(max = 18)
    @Column(name = "dt_hora_transacao")
    private String dtHoraTransacao;
    @Size(max = 36)
    @Column(name = "cd_transacao")
    private String cdTransacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vl_movimento")
    private int vlMovimento;
    @Column(name = "qt_movimento")
    private Integer qtMovimento;
    @Size(max = 100)
    @Column(name = "ds_observacao")
    private String dsObservacao;
    @JoinColumn(name = "id_conta_contabil_debito", referencedColumnName = "id_conta_contabil")
    @ManyToOne(optional = false)
    private ContaContabil idContaContabilDebito;
    @JoinColumn(name = "id_apresentacao", referencedColumnName = "id_apresentacao")
    @ManyToOne
    private Apresentacao idApresentacao;
    @JoinColumn(name = "id_conta_contabil_credito", referencedColumnName = "id_conta_contabil")
    @ManyToOne(optional = false)
    private ContaContabil idContaContabilCredito;
    @JoinColumn(name = "id_contrato_cliente", referencedColumnName = "id_contrato_cliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ContratoCliente contratoCliente;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    @ManyToOne
    private Evento idEvento;
    @JoinColumn(name = "id_forma_pagamento", referencedColumnName = "id_forma_pagamento")
    @ManyToOne
    private FormaPagamento idFormaPagamento;
    @JoinColumn(name = "id_pedido_venda", referencedColumnName = "id_pedido_venda")
    @ManyToOne
    private PedidoVenda idPedidoVenda;
    @JoinColumn(name = "id_tipo_lancamento", referencedColumnName = "id_tipo_lancamento")
    @ManyToOne
    private TipoLancamento idTipoLancamento;
    @JoinColumn(name = "id_tipo_transacao", referencedColumnName = "id_tipo_transacao")
    @ManyToOne(optional = false)
    private TipoTransacao idTipoTransacao;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public ContaCorrente() {
    }

    public ContaCorrente(ContaCorrentePK contaCorrentePK) {
        this.contaCorrentePK = contaCorrentePK;
    }

    public ContaCorrente(ContaCorrentePK contaCorrentePK, int vlMovimento) {
        this.contaCorrentePK = contaCorrentePK;
        this.vlMovimento = vlMovimento;
    }

    public ContaCorrente(int idTransacao, int idContratoCliente) {
        this.contaCorrentePK = new ContaCorrentePK(idTransacao, idContratoCliente);
    }

    public ContaCorrentePK getContaCorrentePK() {
        return contaCorrentePK;
    }

    public void setContaCorrentePK(ContaCorrentePK contaCorrentePK) {
        this.contaCorrentePK = contaCorrentePK;
    }

    public String getDtHoraTransacao() {
        return dtHoraTransacao;
    }

    public void setDtHoraTransacao(String dtHoraTransacao) {
        this.dtHoraTransacao = dtHoraTransacao;
    }

    public String getCdTransacao() {
        return cdTransacao;
    }

    public void setCdTransacao(String cdTransacao) {
        this.cdTransacao = cdTransacao;
    }

    public int getVlMovimento() {
        return vlMovimento;
    }

    public void setVlMovimento(int vlMovimento) {
        this.vlMovimento = vlMovimento;
    }

    public Integer getQtMovimento() {
        return qtMovimento;
    }

    public void setQtMovimento(Integer qtMovimento) {
        this.qtMovimento = qtMovimento;
    }

    public String getDsObservacao() {
        return dsObservacao;
    }

    public void setDsObservacao(String dsObservacao) {
        this.dsObservacao = dsObservacao;
    }

    public ContaContabil getIdContaContabilDebito() {
        return idContaContabilDebito;
    }

    public void setIdContaContabilDebito(ContaContabil idContaContabilDebito) {
        this.idContaContabilDebito = idContaContabilDebito;
    }

    public Apresentacao getIdApresentacao() {
        return idApresentacao;
    }

    public void setIdApresentacao(Apresentacao idApresentacao) {
        this.idApresentacao = idApresentacao;
    }

    public ContaContabil getIdContaContabilCredito() {
        return idContaContabilCredito;
    }

    public void setIdContaContabilCredito(ContaContabil idContaContabilCredito) {
        this.idContaContabilCredito = idContaContabilCredito;
    }

    public ContratoCliente getContratoCliente() {
        return contratoCliente;
    }

    public void setContratoCliente(ContratoCliente contratoCliente) {
        this.contratoCliente = contratoCliente;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    public FormaPagamento getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(FormaPagamento idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public PedidoVenda getIdPedidoVenda() {
        return idPedidoVenda;
    }

    public void setIdPedidoVenda(PedidoVenda idPedidoVenda) {
        this.idPedidoVenda = idPedidoVenda;
    }

    public TipoLancamento getIdTipoLancamento() {
        return idTipoLancamento;
    }

    public void setIdTipoLancamento(TipoLancamento idTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
    }

    public TipoTransacao getIdTipoTransacao() {
        return idTipoTransacao;
    }

    public void setIdTipoTransacao(TipoTransacao idTipoTransacao) {
        this.idTipoTransacao = idTipoTransacao;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contaCorrentePK != null ? contaCorrentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaCorrente)) {
            return false;
        }
        ContaCorrente other = (ContaCorrente) object;
        if ((this.contaCorrentePK == null && other.contaCorrentePK != null) || (this.contaCorrentePK != null && !this.contaCorrentePK.equals(other.contaCorrentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContaCorrente[ contaCorrentePK=" + contaCorrentePK + " ]";
    }
    
}
