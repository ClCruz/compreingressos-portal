/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_contrato_cliente_tipo_lancamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoClienteTipoLancamento.findAll", query = "SELECT c FROM ContratoClienteTipoLancamento c"),
    @NamedQuery(name = "ContratoClienteTipoLancamento.findByIdContratoCliente", query = "SELECT c FROM ContratoClienteTipoLancamento c WHERE c.idContratoCliente = :idContrato"),
    @NamedQuery(name = "ContratoClienteTipoLancamento.findByIdContratoClienteTipoLancamento", query = "SELECT c FROM ContratoClienteTipoLancamento c WHERE c.idContratoClienteTipoLancamento = :idContratoClienteTipoLancamento"),
    @NamedQuery(name = "ContratoClienteTipoLancamento.findByVlAplicacaoTipoLancamento", query = "SELECT c FROM ContratoClienteTipoLancamento c WHERE c.vlAplicacaoTipoLancamento = :vlAplicacaoTipoLancamento"),
    @NamedQuery(name = "ContratoClienteTipoLancamento.findByVlMinimoTipoLancamento", query = "SELECT c FROM ContratoClienteTipoLancamento c WHERE c.vlMinimoTipoLancamento = :vlMinimoTipoLancamento")})
public class ContratoClienteTipoLancamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato_cliente_tipo_lancamento")
    private Integer idContratoClienteTipoLancamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vl_aplicacao_tipo_lancamento")
    private BigDecimal vlAplicacaoTipoLancamento;
    @Column(name = "vl_minimo_tipo_lancamento")
    private BigDecimal vlMinimoTipoLancamento;
    @JoinColumn(name = "id_contrato_cliente", referencedColumnName = "id_contrato_cliente")
    @ManyToOne(optional = false)
    private ContratoCliente idContratoCliente;
    @JoinColumn(name = "id_modalidade_cobranca", referencedColumnName = "id_modalidade_cobranca")
    @ManyToOne
    private ModalidadeCobranca idModalidadeCobranca;
    @JoinColumn(name = "id_tipo_lancamento", referencedColumnName = "id_tipo_lancamento")
    @ManyToOne(optional = false)
    private TipoLancamento idTipoLancamento;
    @JoinColumn(name = "id_usuario_insert", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioInsert;
    @Transient
    private Base idBase;
    @Column(name="dt_inicio_vigencia")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dtInicioVigencia;

    public ContratoClienteTipoLancamento(TipoLancamento idTipoLancamento, Date dtInicioVigencia, BigDecimal vlAplicacaoTipoLancamento, BigDecimal vlMinimoTipoLancamento, ModalidadeCobranca idModalidadeCobranca) {
        this.idTipoLancamento = idTipoLancamento;
        this.dtInicioVigencia = dtInicioVigencia;
        this.vlAplicacaoTipoLancamento = vlAplicacaoTipoLancamento;
        this.vlMinimoTipoLancamento = vlMinimoTipoLancamento;
        this.idModalidadeCobranca = idModalidadeCobranca;
    }

    

    public Base getIdBase() {
        return idBase;
    }

    public void setIdBase(Base idBase) {
        this.idBase = idBase;
    }

    public ContratoClienteTipoLancamento() {
    }

    public ContratoClienteTipoLancamento(Integer idContratoClienteTipoLancamento) {
        this.idContratoClienteTipoLancamento = idContratoClienteTipoLancamento;
    }

    public Integer getIdContratoClienteTipoLancamento() {
        return idContratoClienteTipoLancamento;
    }

    public void setIdContratoClienteTipoLancamento(Integer idContratoClienteTipoLancamento) {
        this.idContratoClienteTipoLancamento = idContratoClienteTipoLancamento;
    }
    
    public Date getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public void setDtInicioVigencia(Date dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public BigDecimal getVlAplicacaoTipoLancamento() {
        return vlAplicacaoTipoLancamento;
    }

    public void setVlAplicacaoTipoLancamento(BigDecimal vlAplicacaoTipoLancamento) {
        this.vlAplicacaoTipoLancamento = vlAplicacaoTipoLancamento;
    }

    public BigDecimal getVlMinimoTipoLancamento() {
        return vlMinimoTipoLancamento;
    }

    public void setVlMinimoTipoLancamento(BigDecimal vlMinimoTipoLancamento) {
        this.vlMinimoTipoLancamento = vlMinimoTipoLancamento;
    }

    public ContratoCliente getIdContratoCliente() {
        return idContratoCliente;
    }

    public void setIdContratoCliente(ContratoCliente idContratoCliente) {
        this.idContratoCliente = idContratoCliente;
    }

    public ModalidadeCobranca getIdModalidadeCobranca() {
        return idModalidadeCobranca;
    }

    public void setIdModalidadeCobranca(ModalidadeCobranca idModalidadeCobranca) {
        this.idModalidadeCobranca = idModalidadeCobranca;
    }

    public TipoLancamento getIdTipoLancamento() {
        return idTipoLancamento;
    }

    public void setIdTipoLancamento(TipoLancamento idTipoLancamento) {
        this.idTipoLancamento = idTipoLancamento;
    }

    public Usuario getIdUsuarioInsert() {
        return idUsuarioInsert;
    }

    public void setIdUsuarioInsert(Usuario idUsuarioInsert) {
        this.idUsuarioInsert = idUsuarioInsert;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoClienteTipoLancamento != null ? idContratoClienteTipoLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContratoClienteTipoLancamento)) {
            return false;
        }
        ContratoClienteTipoLancamento other = (ContratoClienteTipoLancamento) object;
        if ((this.idContratoClienteTipoLancamento == null && other.idContratoClienteTipoLancamento != null) || (this.idContratoClienteTipoLancamento != null && !this.idContratoClienteTipoLancamento.equals(other.idContratoClienteTipoLancamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContratoClienteTipoLancamento[ idContratoClienteTipoLancamento=" + idContratoClienteTipoLancamento + " ]";
    }
    
}
