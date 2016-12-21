/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author gabrielqueiroz
 */
@Entity
@Table(name = "mw_pedido_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoVenda.findAll", query = "SELECT p FROM PedidoVenda p")
    , @NamedQuery(name = "PedidoVenda.findByIdPedidoVenda", query = "SELECT p FROM PedidoVenda p WHERE p.idPedidoVenda = :idPedidoVenda")
    , @NamedQuery(name = "PedidoVenda.findByDtPedidoVenda", query = "SELECT p FROM PedidoVenda p WHERE p.dtPedidoVenda = :dtPedidoVenda")
    , @NamedQuery(name = "PedidoVenda.findByVlTotalPedidoVenda", query = "SELECT p FROM PedidoVenda p WHERE p.vlTotalPedidoVenda = :vlTotalPedidoVenda")
    , @NamedQuery(name = "PedidoVenda.findByInSituacao", query = "SELECT p FROM PedidoVenda p WHERE p.inSituacao = :inSituacao")
    , @NamedQuery(name = "PedidoVenda.findByInRetiraEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.inRetiraEntrega = :inRetiraEntrega")
    , @NamedQuery(name = "PedidoVenda.findByVlTotalIngressos", query = "SELECT p FROM PedidoVenda p WHERE p.vlTotalIngressos = :vlTotalIngressos")
    , @NamedQuery(name = "PedidoVenda.findByVlFrete", query = "SELECT p FROM PedidoVenda p WHERE p.vlFrete = :vlFrete")
    , @NamedQuery(name = "PedidoVenda.findByVlTotalTaxaConveniencia", query = "SELECT p FROM PedidoVenda p WHERE p.vlTotalTaxaConveniencia = :vlTotalTaxaConveniencia")
    , @NamedQuery(name = "PedidoVenda.findByDsEnderecoEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.dsEnderecoEntrega = :dsEnderecoEntrega")
    , @NamedQuery(name = "PedidoVenda.findByDsComplEnderecoEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.dsComplEnderecoEntrega = :dsComplEnderecoEntrega")
    , @NamedQuery(name = "PedidoVenda.findByDsBairroEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.dsBairroEntrega = :dsBairroEntrega")
    , @NamedQuery(name = "PedidoVenda.findByDsCidadeEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.dsCidadeEntrega = :dsCidadeEntrega")
    , @NamedQuery(name = "PedidoVenda.findByDsCuidadosDe", query = "SELECT p FROM PedidoVenda p WHERE p.dsCuidadosDe = :dsCuidadosDe")
    , @NamedQuery(name = "PedidoVenda.findByInSituacaoDespacho", query = "SELECT p FROM PedidoVenda p WHERE p.inSituacaoDespacho = :inSituacaoDespacho")
    , @NamedQuery(name = "PedidoVenda.findByDtDespacho", query = "SELECT p FROM PedidoVenda p WHERE p.dtDespacho = :dtDespacho")
    , @NamedQuery(name = "PedidoVenda.findByDtHoraCancelamento", query = "SELECT p FROM PedidoVenda p WHERE p.dtHoraCancelamento = :dtHoraCancelamento")
    , @NamedQuery(name = "PedidoVenda.findByDsMotivoCancelamento", query = "SELECT p FROM PedidoVenda p WHERE p.dsMotivoCancelamento = :dsMotivoCancelamento")
    , @NamedQuery(name = "PedidoVenda.findByCdCepEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.cdCepEntrega = :cdCepEntrega")
    , @NamedQuery(name = "PedidoVenda.findByIdPedidoIpagare", query = "SELECT p FROM PedidoVenda p WHERE p.idPedidoIpagare = :idPedidoIpagare")
    , @NamedQuery(name = "PedidoVenda.findByCdNumeroAutorizacao", query = "SELECT p FROM PedidoVenda p WHERE p.cdNumeroAutorizacao = :cdNumeroAutorizacao")
    , @NamedQuery(name = "PedidoVenda.findByCdNumeroTransacao", query = "SELECT p FROM PedidoVenda p WHERE p.cdNumeroTransacao = :cdNumeroTransacao")
    , @NamedQuery(name = "PedidoVenda.findByCdBinCartao", query = "SELECT p FROM PedidoVenda p WHERE p.cdBinCartao = :cdBinCartao")
    , @NamedQuery(name = "PedidoVenda.findByDtEntregaIngresso", query = "SELECT p FROM PedidoVenda p WHERE p.dtEntregaIngresso = :dtEntregaIngresso")
    , @NamedQuery(name = "PedidoVenda.findByIdIP", query = "SELECT p FROM PedidoVenda p WHERE p.idIP = :idIP")
    , @NamedQuery(name = "PedidoVenda.findByIdTransactionBraspag", query = "SELECT p FROM PedidoVenda p WHERE p.idTransactionBraspag = :idTransactionBraspag")
    , @NamedQuery(name = "PedidoVenda.findByIdMeioPagamento", query = "SELECT p FROM PedidoVenda p WHERE p.idMeioPagamento = :idMeioPagamento")
    , @NamedQuery(name = "PedidoVenda.findByNrParcelasPgto", query = "SELECT p FROM PedidoVenda p WHERE p.nrParcelasPgto = :nrParcelasPgto")
    , @NamedQuery(name = "PedidoVenda.findByNrBeneficio", query = "SELECT p FROM PedidoVenda p WHERE p.nrBeneficio = :nrBeneficio")
    , @NamedQuery(name = "PedidoVenda.findByInPacote", query = "SELECT p FROM PedidoVenda p WHERE p.inPacote = :inPacote")
    , @NamedQuery(name = "PedidoVenda.findByIdPedidoPai", query = "SELECT p FROM PedidoVenda p WHERE p.idPedidoPai = :idPedidoPai")
    , @NamedQuery(name = "PedidoVenda.findByNmClienteVoucher", query = "SELECT p FROM PedidoVenda p WHERE p.nmClienteVoucher = :nmClienteVoucher")
    , @NamedQuery(name = "PedidoVenda.findByDsEmailVoucher", query = "SELECT p FROM PedidoVenda p WHERE p.dsEmailVoucher = :dsEmailVoucher")
    , @NamedQuery(name = "PedidoVenda.findByDsEstornoPos", query = "SELECT p FROM PedidoVenda p WHERE p.dsEstornoPos = :dsEstornoPos")
    , @NamedQuery(name = "PedidoVenda.findByNrEnderecoEntrega", query = "SELECT p FROM PedidoVenda p WHERE p.nrEnderecoEntrega = :nrEnderecoEntrega")
    , @NamedQuery(name = "PedidoVenda.findByIdOrigem", query = "SELECT p FROM PedidoVenda p WHERE p.idOrigem = :idOrigem")
    , @NamedQuery(name = "PedidoVenda.findByNmTitularCartao", query = "SELECT p FROM PedidoVenda p WHERE p.nmTitularCartao = :nmTitularCartao")})
public class PedidoVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pedido_venda")
    private Integer idPedidoVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_pedido_venda")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtPedidoVenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "vl_total_pedido_venda")
    private BigDecimal vlTotalPedidoVenda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_situacao")
    private Character inSituacao;
    @Size(max = 20)
    @Column(name = "in_retira_entrega")
    private String inRetiraEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vl_total_ingressos")
    private BigDecimal vlTotalIngressos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vl_frete")
    private BigDecimal vlFrete;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vl_total_taxa_conveniencia")
    private BigDecimal vlTotalTaxaConveniencia;
    @Size(max = 150)
    @Column(name = "ds_endereco_entrega")
    private String dsEnderecoEntrega;
    @Size(max = 50)
    @Column(name = "ds_compl_endereco_entrega")
    private String dsComplEnderecoEntrega;
    @Size(max = 50)
    @Column(name = "ds_bairro_entrega")
    private String dsBairroEntrega;
    @Size(max = 50)
    @Column(name = "ds_cidade_entrega")
    private String dsCidadeEntrega;
    @Size(max = 50)
    @Column(name = "ds_cuidados_de")
    private String dsCuidadosDe;
    @Column(name = "in_situacao_despacho")
    private Character inSituacaoDespacho;
    @Column(name = "dt_despacho")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtDespacho;
    @Column(name = "dt_hora_cancelamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHoraCancelamento;
    @Size(max = 250)
    @Column(name = "ds_motivo_cancelamento")
    private String dsMotivoCancelamento;
    @Size(max = 8)
    @Column(name = "cd_cep_entrega")
    private String cdCepEntrega;
    @Size(max = 36)
    @Column(name = "id_pedido_ipagare")
    private String idPedidoIpagare;
    @Size(max = 50)
    @Column(name = "cd_numero_autorizacao")
    private String cdNumeroAutorizacao;
    @Size(max = 50)
    @Column(name = "cd_numero_transacao")
    private String cdNumeroTransacao;
    @Size(max = 16)
    @Column(name = "cd_bin_cartao")
    private String cdBinCartao;
    @Column(name = "dt_entrega_ingresso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEntregaIngresso;
    @Size(max = 50)
    @Column(name = "id_IP")
    private String idIP;
    @Size(max = 36)
    @Column(name = "id_transaction_braspag")
    private String idTransactionBraspag;
    @Column(name = "id_meio_pagamento")
    private Integer idMeioPagamento;
    @Column(name = "nr_parcelas_pgto")
    private Integer nrParcelasPgto;
    @Size(max = 20)
    @Column(name = "nr_beneficio")
    private String nrBeneficio;
    @Column(name = "in_pacote")
    private Character inPacote;
    @Column(name = "id_pedido_pai")
    private Integer idPedidoPai;
    @Size(max = 60)
    @Column(name = "nm_cliente_voucher")
    private String nmClienteVoucher;
    @Size(max = 100)
    @Column(name = "ds_email_voucher")
    private String dsEmailVoucher;
    @Size(max = 255)
    @Column(name = "ds_estorno_pos")
    private String dsEstornoPos;
    @Size(max = 15)
    @Column(name = "nr_endereco_entrega")
    private String nrEnderecoEntrega;
    @Column(name = "id_origem")
    private Integer idOrigem;
    @Size(max = 60)
    @Column(name = "nm_titular_cartao")
    private String nmTitularCartao;
    @OneToMany(mappedBy = "idPedidoVenda")
    private Collection<ContaCorrente> contaCorrenteCollection;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne
    private Estado idEstado;
    @JoinColumn(name = "id_usuario_estorno", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioEstorno;
    @JoinColumn(name = "id_usuario_callcenter", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioCallcenter;
    @JoinColumn(name = "id_usuario_itau", referencedColumnName = "id_usuario")
    @ManyToOne
    private UsuarioItau idUsuarioItau;

    public PedidoVenda() {
    }

    public PedidoVenda(Integer idPedidoVenda) {
        this.idPedidoVenda = idPedidoVenda;
    }

    public PedidoVenda(Integer idPedidoVenda, Date dtPedidoVenda, BigDecimal vlTotalPedidoVenda, Character inSituacao, BigDecimal vlTotalIngressos, BigDecimal vlFrete, BigDecimal vlTotalTaxaConveniencia) {
        this.idPedidoVenda = idPedidoVenda;
        this.dtPedidoVenda = dtPedidoVenda;
        this.vlTotalPedidoVenda = vlTotalPedidoVenda;
        this.inSituacao = inSituacao;
        this.vlTotalIngressos = vlTotalIngressos;
        this.vlFrete = vlFrete;
        this.vlTotalTaxaConveniencia = vlTotalTaxaConveniencia;
    }

    public Integer getIdPedidoVenda() {
        return idPedidoVenda;
    }

    public void setIdPedidoVenda(Integer idPedidoVenda) {
        this.idPedidoVenda = idPedidoVenda;
    }

    public Date getDtPedidoVenda() {
        return dtPedidoVenda;
    }

    public void setDtPedidoVenda(Date dtPedidoVenda) {
        this.dtPedidoVenda = dtPedidoVenda;
    }

    public BigDecimal getVlTotalPedidoVenda() {
        return vlTotalPedidoVenda;
    }

    public void setVlTotalPedidoVenda(BigDecimal vlTotalPedidoVenda) {
        this.vlTotalPedidoVenda = vlTotalPedidoVenda;
    }

    public Character getInSituacao() {
        return inSituacao;
    }

    public void setInSituacao(Character inSituacao) {
        this.inSituacao = inSituacao;
    }

    public String getInRetiraEntrega() {
        return inRetiraEntrega;
    }

    public void setInRetiraEntrega(String inRetiraEntrega) {
        this.inRetiraEntrega = inRetiraEntrega;
    }

    public BigDecimal getVlTotalIngressos() {
        return vlTotalIngressos;
    }

    public void setVlTotalIngressos(BigDecimal vlTotalIngressos) {
        this.vlTotalIngressos = vlTotalIngressos;
    }

    public BigDecimal getVlFrete() {
        return vlFrete;
    }

    public void setVlFrete(BigDecimal vlFrete) {
        this.vlFrete = vlFrete;
    }

    public BigDecimal getVlTotalTaxaConveniencia() {
        return vlTotalTaxaConveniencia;
    }

    public void setVlTotalTaxaConveniencia(BigDecimal vlTotalTaxaConveniencia) {
        this.vlTotalTaxaConveniencia = vlTotalTaxaConveniencia;
    }

    public String getDsEnderecoEntrega() {
        return dsEnderecoEntrega;
    }

    public void setDsEnderecoEntrega(String dsEnderecoEntrega) {
        this.dsEnderecoEntrega = dsEnderecoEntrega;
    }

    public String getDsComplEnderecoEntrega() {
        return dsComplEnderecoEntrega;
    }

    public void setDsComplEnderecoEntrega(String dsComplEnderecoEntrega) {
        this.dsComplEnderecoEntrega = dsComplEnderecoEntrega;
    }

    public String getDsBairroEntrega() {
        return dsBairroEntrega;
    }

    public void setDsBairroEntrega(String dsBairroEntrega) {
        this.dsBairroEntrega = dsBairroEntrega;
    }

    public String getDsCidadeEntrega() {
        return dsCidadeEntrega;
    }

    public void setDsCidadeEntrega(String dsCidadeEntrega) {
        this.dsCidadeEntrega = dsCidadeEntrega;
    }

    public String getDsCuidadosDe() {
        return dsCuidadosDe;
    }

    public void setDsCuidadosDe(String dsCuidadosDe) {
        this.dsCuidadosDe = dsCuidadosDe;
    }

    public Character getInSituacaoDespacho() {
        return inSituacaoDespacho;
    }

    public void setInSituacaoDespacho(Character inSituacaoDespacho) {
        this.inSituacaoDespacho = inSituacaoDespacho;
    }

    public Date getDtDespacho() {
        return dtDespacho;
    }

    public void setDtDespacho(Date dtDespacho) {
        this.dtDespacho = dtDespacho;
    }

    public Date getDtHoraCancelamento() {
        return dtHoraCancelamento;
    }

    public void setDtHoraCancelamento(Date dtHoraCancelamento) {
        this.dtHoraCancelamento = dtHoraCancelamento;
    }

    public String getDsMotivoCancelamento() {
        return dsMotivoCancelamento;
    }

    public void setDsMotivoCancelamento(String dsMotivoCancelamento) {
        this.dsMotivoCancelamento = dsMotivoCancelamento;
    }

    public String getCdCepEntrega() {
        return cdCepEntrega;
    }

    public void setCdCepEntrega(String cdCepEntrega) {
        this.cdCepEntrega = cdCepEntrega;
    }

    public String getIdPedidoIpagare() {
        return idPedidoIpagare;
    }

    public void setIdPedidoIpagare(String idPedidoIpagare) {
        this.idPedidoIpagare = idPedidoIpagare;
    }

    public String getCdNumeroAutorizacao() {
        return cdNumeroAutorizacao;
    }

    public void setCdNumeroAutorizacao(String cdNumeroAutorizacao) {
        this.cdNumeroAutorizacao = cdNumeroAutorizacao;
    }

    public String getCdNumeroTransacao() {
        return cdNumeroTransacao;
    }

    public void setCdNumeroTransacao(String cdNumeroTransacao) {
        this.cdNumeroTransacao = cdNumeroTransacao;
    }

    public String getCdBinCartao() {
        return cdBinCartao;
    }

    public void setCdBinCartao(String cdBinCartao) {
        this.cdBinCartao = cdBinCartao;
    }

    public Date getDtEntregaIngresso() {
        return dtEntregaIngresso;
    }

    public void setDtEntregaIngresso(Date dtEntregaIngresso) {
        this.dtEntregaIngresso = dtEntregaIngresso;
    }

    public String getIdIP() {
        return idIP;
    }

    public void setIdIP(String idIP) {
        this.idIP = idIP;
    }

    public String getIdTransactionBraspag() {
        return idTransactionBraspag;
    }

    public void setIdTransactionBraspag(String idTransactionBraspag) {
        this.idTransactionBraspag = idTransactionBraspag;
    }

    public Integer getIdMeioPagamento() {
        return idMeioPagamento;
    }

    public void setIdMeioPagamento(Integer idMeioPagamento) {
        this.idMeioPagamento = idMeioPagamento;
    }

    public Integer getNrParcelasPgto() {
        return nrParcelasPgto;
    }

    public void setNrParcelasPgto(Integer nrParcelasPgto) {
        this.nrParcelasPgto = nrParcelasPgto;
    }

    public String getNrBeneficio() {
        return nrBeneficio;
    }

    public void setNrBeneficio(String nrBeneficio) {
        this.nrBeneficio = nrBeneficio;
    }

    public Character getInPacote() {
        return inPacote;
    }

    public void setInPacote(Character inPacote) {
        this.inPacote = inPacote;
    }

    public Integer getIdPedidoPai() {
        return idPedidoPai;
    }

    public void setIdPedidoPai(Integer idPedidoPai) {
        this.idPedidoPai = idPedidoPai;
    }

    public String getNmClienteVoucher() {
        return nmClienteVoucher;
    }

    public void setNmClienteVoucher(String nmClienteVoucher) {
        this.nmClienteVoucher = nmClienteVoucher;
    }

    public String getDsEmailVoucher() {
        return dsEmailVoucher;
    }

    public void setDsEmailVoucher(String dsEmailVoucher) {
        this.dsEmailVoucher = dsEmailVoucher;
    }

    public String getDsEstornoPos() {
        return dsEstornoPos;
    }

    public void setDsEstornoPos(String dsEstornoPos) {
        this.dsEstornoPos = dsEstornoPos;
    }

    public String getNrEnderecoEntrega() {
        return nrEnderecoEntrega;
    }

    public void setNrEnderecoEntrega(String nrEnderecoEntrega) {
        this.nrEnderecoEntrega = nrEnderecoEntrega;
    }

    public Integer getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(Integer idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getNmTitularCartao() {
        return nmTitularCartao;
    }

    public void setNmTitularCartao(String nmTitularCartao) {
        this.nmTitularCartao = nmTitularCartao;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ContaCorrente> getContaCorrenteCollection() {
        return contaCorrenteCollection;
    }

    public void setContaCorrenteCollection(Collection<ContaCorrente> contaCorrenteCollection) {
        this.contaCorrenteCollection = contaCorrenteCollection;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public Usuario getIdUsuarioEstorno() {
        return idUsuarioEstorno;
    }

    public void setIdUsuarioEstorno(Usuario idUsuarioEstorno) {
        this.idUsuarioEstorno = idUsuarioEstorno;
    }

    public Usuario getIdUsuarioCallcenter() {
        return idUsuarioCallcenter;
    }

    public void setIdUsuarioCallcenter(Usuario idUsuarioCallcenter) {
        this.idUsuarioCallcenter = idUsuarioCallcenter;
    }

    public UsuarioItau getIdUsuarioItau() {
        return idUsuarioItau;
    }

    public void setIdUsuarioItau(UsuarioItau idUsuarioItau) {
        this.idUsuarioItau = idUsuarioItau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedidoVenda != null ? idPedidoVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoVenda)) {
            return false;
        }
        PedidoVenda other = (PedidoVenda) object;
        if ((this.idPedidoVenda == null && other.idPedidoVenda != null) || (this.idPedidoVenda != null && !this.idPedidoVenda.equals(other.idPedidoVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.PedidoVenda[ idPedidoVenda=" + idPedidoVenda + " ]";
    }
    
}
