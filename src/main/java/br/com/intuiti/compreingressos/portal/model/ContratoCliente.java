package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_contrato_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContratoCliente.findAll", query = "SELECT c FROM ContratoCliente c"),
    @NamedQuery(name = "ContratoCliente.exist", query = "SELECT c FROM ContratoCliente c WHERE c.idContratoCliente = :id"),
    @NamedQuery(name = "ContratoCliente.findSituacao", query = "SELECT c.inStatusContrato FROM ContratoCliente c WHERE c.idContratoCliente = :id"),
    @NamedQuery(name = "ContratoCliente.findByLocalEmpresaContratante", query = "SELECT c.idContratoCliente FROM ContratoCliente c WHERE c.idLocalEvento = :idLocalEvento and c.idEmpresa = :idEmpresa and c.idContratante = :idContratante ORDER BY c.idContratoCliente DESC"),
    @NamedQuery(name = "ContratoCliente.findByIdContratoCliente", query = "SELECT c FROM ContratoCliente c WHERE c.idContratoCliente = :idContratoCliente"),
    @NamedQuery(name = "ContratoCliente.findByDsHorarioBilheteria", query = "SELECT c FROM ContratoCliente c WHERE c.dsHorarioBilheteria = :dsHorarioBilheteria"),
    @NamedQuery(name = "ContratoCliente.findByDsHorarioCallcenter", query = "SELECT c FROM ContratoCliente c WHERE c.dsHorarioCallcenter = :dsHorarioCallcenter"),
    @NamedQuery(name = "ContratoCliente.findByDtInicioVigencia", query = "SELECT c FROM ContratoCliente c WHERE c.dtInicioVigencia = :dtInicioVigencia"),
    @NamedQuery(name = "ContratoCliente.findByDtInativacao", query = "SELECT c FROM ContratoCliente c WHERE c.dtInativacao = :dtInativacao"),
    @NamedQuery(name = "ContratoCliente.findByDtAprovacao", query = "SELECT c FROM ContratoCliente c WHERE c.dtAprovacao = :dtAprovacao"),
    @NamedQuery(name = "ContratoCliente.findByDsResumida", query = "SELECT c FROM ContratoCliente c WHERE c.dsResumida = :dsResumida"),
    @NamedQuery(name = "ContratoCliente.findByDtAssinaturaContrato", query = "SELECT c FROM ContratoCliente c WHERE c.dtAssinaturaContrato = :dtAssinaturaContrato"),
    @NamedQuery(name = "ContratoCliente.findByCdSiglaProjeto", query = "SELECT c FROM ContratoCliente c WHERE c.cdSiglaProjeto = :cdSiglaProjeto"),
    @NamedQuery(name = "ContratoCliente.findByDtTerminoVigencia", query = "SELECT c FROM ContratoCliente c WHERE c.dtTerminoVigencia = :dtTerminoVigencia"),
    @NamedQuery(name = "ContratoCliente.findByDsObsAprovador", query = "SELECT c FROM ContratoCliente c WHERE c.dsObsAprovador = :dsObsAprovador"),
    @NamedQuery(name = "ContratoCliente.findByDsObsVendedor", query = "SELECT c FROM ContratoCliente c WHERE c.dsObsVendedor = :dsObsVendedor"),
    @NamedQuery(name = "ContratoCliente.findByInStatusContrato", query = "SELECT c FROM ContratoCliente c WHERE c.inStatusContrato = :inStatusContrato"),
    @NamedQuery(name = "ContratoCliente.findByDsMotivoInativacao", query = "SELECT c FROM ContratoCliente c WHERE c.dsMotivoInativacao = :dsMotivoInativacao")})
public class ContratoCliente implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoCliente")
    private Collection<ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idContratoCliente")
    private Collection<ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contrato_cliente")
    private Integer idContratoCliente;
    @Size(max = 50)
    @Column(name = "ds_horario_bilheteria")
    private String dsHorarioBilheteria;
    @Size(max = 50)
    @Column(name = "ds_horario_callcenter")
    private String dsHorarioCallcenter;
    @Column(name = "dt_inicio_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInicioVigencia;
    @Column(name = "dt_inativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInativacao;
    @Column(name = "dt_aprovacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAprovacao;
    @Size(max = 20)
    @Column(name = "ds_resumida")
    private String dsResumida;
    @Column(name = "dt_assinatura_contrato")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAssinaturaContrato;
    @Size(max = 20)
    @Column(name = "cd_sigla_projeto")
    private String cdSiglaProjeto;
    @Column(name = "dt_termino_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtTerminoVigencia;
    @Size(max = 255)
    @Column(name = "ds_obs_aprovador")
    private String dsObsAprovador;
    @Size(max = 20)
    @Column(name = "ds_obs_vendedor")
    private String dsObsVendedor;
    @Size(max = 20)
    @Column(name = "in_status_contrato")
    private String inStatusContrato;
    @Size(max = 250)
    @Column(name = "ds_motivo_inativacao")
    private String dsMotivoInativacao;
    @JoinColumn(name = "id_contratante", referencedColumnName = "id_contratante")
    @ManyToOne
    private Contratante idContratante;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "id_local_evento", referencedColumnName = "id_local_evento")
    @ManyToOne
    private LocalEvento idLocalEvento;
    @JoinColumn(name = "id_modalidade_contrato", referencedColumnName = "id_modalidade_contrato")
    @ManyToOne
    private ModalidadeContrato idModalidadeContrato;
    @JoinColumn(name = "id_segmento_evento", referencedColumnName = "id_segmento_evento")
    @ManyToOne
    private SegmentoEvento idSegmentoEvento;
    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne
    private TipoContrato idTipoContrato;
    @JoinColumn(name = "id_usuario_aprovador", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioAprovador;
    @JoinColumn(name = "id_usuario_solicitante", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuarioSolicitante;
    @JoinColumn(name = "id_vendedor", referencedColumnName = "id_vendedor")
    @ManyToOne
    private Vendedor idVendedor;
    
    public ContratoCliente() {
    }

    public ContratoCliente(Integer idContratoCliente) {
        this.idContratoCliente = idContratoCliente;
    }

    public Integer getIdContratoCliente() {
        return idContratoCliente;
    }

    public void setIdContratoCliente(Integer idContratoCliente) {
        this.idContratoCliente = idContratoCliente;
    }

    public String getDsHorarioBilheteria() {
        return dsHorarioBilheteria;
    }

    public void setDsHorarioBilheteria(String dsHorarioBilheteria) {
        this.dsHorarioBilheteria = dsHorarioBilheteria;
    }

    public String getDsHorarioCallcenter() {
        return dsHorarioCallcenter;
    }

    public void setDsHorarioCallcenter(String dsHorarioCallcenter) {
        this.dsHorarioCallcenter = dsHorarioCallcenter;
    }

    public Date getDtInicioVigencia() {
        return dtInicioVigencia;
    }

    public void setDtInicioVigencia(Date dtInicioVigencia) {
        this.dtInicioVigencia = dtInicioVigencia;
    }

    public Date getDtInativacao() {
        return dtInativacao;
    }

    public void setDtInativacao(Date dtInativacao) {
        this.dtInativacao = dtInativacao;
    }

    public Date getDtAprovacao() {
        return dtAprovacao;
    }

    public void setDtAprovacao(Date dtAprovacao) {
        this.dtAprovacao = dtAprovacao;
    }

    public String getDsResumida() {
        return dsResumida;
    }

    public void setDsResumida(String dsResumida) {
        this.dsResumida = dsResumida;
    }

    public Date getDtAssinaturaContrato() {
        return dtAssinaturaContrato;
    }

    public void setDtAssinaturaContrato(Date dtAssinaturaContrato) {
        this.dtAssinaturaContrato = dtAssinaturaContrato;
    }

    public String getCdSiglaProjeto() {
        return cdSiglaProjeto;
    }

    public void setCdSiglaProjeto(String cdSiglaProjeto) {
        this.cdSiglaProjeto = cdSiglaProjeto;
    }

    public Date getDtTerminoVigencia() {
        return dtTerminoVigencia;
    }

    public void setDtTerminoVigencia(Date dtTerminoVigencia) {
        this.dtTerminoVigencia = dtTerminoVigencia;
    }

    public String getDsObsAprovador() {
        return dsObsAprovador;
    }

    public void setDsObsAprovador(String dsObsAprovador) {
        this.dsObsAprovador = dsObsAprovador;
    }

    public String getDsObsVendedor() {
        return dsObsVendedor;
    }

    public void setDsObsVendedor(String dsObsVendedor) {
        this.dsObsVendedor = dsObsVendedor;
    }

    public String getInStatusContrato() {
        return inStatusContrato;
    }

    public void setInStatusContrato(String inStatusContrato) {
        this.inStatusContrato = inStatusContrato;
    }

    public String getDsMotivoInativacao() {
        return dsMotivoInativacao;
    }

    public void setDsMotivoInativacao(String dsMotivoInativacao) {
        this.dsMotivoInativacao = dsMotivoInativacao;
    }

    public Contratante getIdContratante() {
        return idContratante;
    }

    public void setIdContratante(Contratante idContratante) {
        this.idContratante = idContratante;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public LocalEvento getIdLocalEvento() {
        return idLocalEvento;
    }

    public void setIdLocalEvento(LocalEvento idLocalEvento) {
        this.idLocalEvento = idLocalEvento;
    }

    public ModalidadeContrato getIdModalidadeContrato() {
        return idModalidadeContrato;
    }

    public void setIdModalidadeContrato(ModalidadeContrato idModalidadeContrato) {
        this.idModalidadeContrato = idModalidadeContrato;
    }

    public SegmentoEvento getIdSegmentoEvento() {
        return idSegmentoEvento;
    }

    public void setIdSegmentoEvento(SegmentoEvento idSegmentoEvento) {
        this.idSegmentoEvento = idSegmentoEvento;
    }

    public TipoContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public Usuario getIdUsuarioAprovador() {
        return idUsuarioAprovador;
    }

    public void setIdUsuarioAprovador(Usuario idUsuarioAprovador) {
        this.idUsuarioAprovador = idUsuarioAprovador;
    }

    public Usuario getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    public void setIdUsuarioSolicitante(Usuario idUsuarioSolicitante) {
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }

    public Vendedor getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Vendedor idVendedor) {
        this.idVendedor = idVendedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratoCliente != null ? idContratoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContratoCliente)) {
            return false;
        }
        ContratoCliente other = (ContratoCliente) object;
        if ((this.idContratoCliente == null && other.idContratoCliente != null) || (this.idContratoCliente != null && !this.idContratoCliente.equals(other.idContratoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContratoCliente[ idContratoCliente=" + idContratoCliente + " ]";
    }

    @XmlTransient
    public Collection<ContratoClientePrazoPagamento> getContratoClientePrazoPagamentoCollection() {
        return contratoClientePrazoPagamentoCollection;
    }

    public void setContratoClientePrazoPagamentoCollection(Collection<ContratoClientePrazoPagamento> contratoClientePrazoPagamentoCollection) {
        this.contratoClientePrazoPagamentoCollection = contratoClientePrazoPagamentoCollection;
    }

    @XmlTransient
    public Collection<ContratoClienteTipoLancamento> getContratoClienteTipoLancamentoCollection() {
        return contratoClienteTipoLancamentoCollection;
    }

    public void setContratoClienteTipoLancamentoCollection(Collection<ContratoClienteTipoLancamento> contratoClienteTipoLancamentoCollection) {
        this.contratoClienteTipoLancamentoCollection = contratoClienteTipoLancamentoCollection;
    }

}
