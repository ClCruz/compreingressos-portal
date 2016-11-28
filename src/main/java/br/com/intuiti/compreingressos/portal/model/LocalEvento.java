/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_local_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalEvento.findAll", query = "SELECT l FROM LocalEvento l"),
    @NamedQuery(name = "LocalEvento.findAllOrderBy", query = "SELECT l FROM LocalEvento l WHERE l.inAtivo = :inAtivo ORDER BY l.dsLocalEvento"),
    @NamedQuery(name = "LocalEvento.findByIdLocalEvento", query = "SELECT l FROM LocalEvento l WHERE l.idLocalEvento = :idLocalEvento"),
    @NamedQuery(name = "LocalEvento.findByDsLocalEvento", query = "SELECT l FROM LocalEvento l WHERE l.dsLocalEvento = :dsLocalEvento"),
    @NamedQuery(name = "LocalEvento.findByDsEndereco", query = "SELECT l FROM LocalEvento l WHERE l.dsEndereco = :dsEndereco"),
    @NamedQuery(name = "LocalEvento.findByDsNumero", query = "SELECT l FROM LocalEvento l WHERE l.dsNumero = :dsNumero"),
    @NamedQuery(name = "LocalEvento.findByDsComplemento", query = "SELECT l FROM LocalEvento l WHERE l.dsComplemento = :dsComplemento"),
    @NamedQuery(name = "LocalEvento.findByDsBairro", query = "SELECT l FROM LocalEvento l WHERE l.dsBairro = :dsBairro"),
    @NamedQuery(name = "LocalEvento.findByCdCepCliente", query = "SELECT l FROM LocalEvento l WHERE l.cdCepCliente = :cdCepCliente"),
    @NamedQuery(name = "LocalEvento.findByNrTelefone", query = "SELECT l FROM LocalEvento l WHERE l.nrTelefone = :nrTelefone"),
    @NamedQuery(name = "LocalEvento.findByCdEmail", query = "SELECT l FROM LocalEvento l WHERE l.cdEmail = :cdEmail"),
    @NamedQuery(name = "LocalEvento.findByDtInativacao", query = "SELECT l FROM LocalEvento l WHERE l.dtInativacao = :dtInativacao"),
    @NamedQuery(name = "LocalEvento.findByInAtivo", query = "SELECT l FROM LocalEvento l WHERE l.inAtivo = :inAtivo"),
    @NamedQuery(name = "LocalEvento.findByCdUrlSite", query = "SELECT l FROM LocalEvento l WHERE l.cdUrlSite = :cdUrlSite"),
    @NamedQuery(name = "LocalEvento.findAllMunicipio", query = "SELECT m FROM Municipio m WHERE m.idEstado = :idEstado ORDER BY m.dsMunicipio")})
public class LocalEvento implements Serializable {

    @OneToMany(mappedBy = "idLocalEvento")
    private Collection<Evento> eventoCollection;

    @OneToMany(mappedBy = "idLocalEvento")
    private Collection<ContratoCliente> contratoClienteCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_local_evento")
    private Integer idLocalEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message = "O campo Descrição deve conter entre 1 e 50 caracteres")
    @Column(name = "ds_local_evento")
    private String dsLocalEvento;
    @Size(max = 100)
    @Column(name = "ds_endereco")
    private String dsEndereco;
    @Size(max = 100)
    @Column(name = "ds_numero")
    private String dsNumero;
    @Size(max = 100)
    @Column(name = "ds_complemento")
    private String dsComplemento;
    @Size(max = 100)
    @Column(name = "ds_bairro")
    private String dsBairro;
    @Size(max = 8)
    @Column(name = "cd_cep_cliente")
    private String cdCepCliente;
    @Size(max = 50)
    @Column(name = "nr_telefone")
    private String nrTelefone;
    @Size(max = 100)
    @Column(name = "cd_email")
    private String cdEmail;
    @Column(name = "dt_inativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInativacao;
    @Column(name = "in_ativo")
    private Boolean inAtivo;
    @Size(max = 100)
    @Column(name = "cd_url_site")
    private String cdUrlSite;
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne(optional = false)
    private Municipio idMunicipio;
    @JoinColumn(name = "id_tipo_local", referencedColumnName = "id_tipo_local")
    @ManyToOne(optional = false)
    private TipoLocal idTipoLocal;
    @Transient
    private Estado idEstado;

    

	public LocalEvento() {
    }

    public LocalEvento(Integer idLocalEvento) {
        this.idLocalEvento = idLocalEvento;
    }

    public LocalEvento(Integer idLocalEvento, String dsLocalEvento) {
        this.idLocalEvento = idLocalEvento;
        this.dsLocalEvento = dsLocalEvento;
    }

    public Integer getIdLocalEvento() {
        return idLocalEvento;
    }

    public void setIdLocalEvento(Integer idLocalEvento) {
        this.idLocalEvento = idLocalEvento;
    }

    public String getDsLocalEvento() {
        return dsLocalEvento;
    }

    public void setDsLocalEvento(String dsLocalEvento) {
        this.dsLocalEvento = dsLocalEvento;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsNumero() {
        return dsNumero;
    }

    public void setDsNumero(String dsNumero) {
        this.dsNumero = dsNumero;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getCdCepCliente() {
        return cdCepCliente;
    }

    public void setCdCepCliente(String cdCepCliente) {
        this.cdCepCliente = cdCepCliente;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getCdEmail() {
        return cdEmail;
    }

    public void setCdEmail(String cdEmail) {
        this.cdEmail = cdEmail;
    }

    public Date getDtInativacao() {
        return dtInativacao;
    }

    public void setDtInativacao(Date dtInativacao) {
        this.dtInativacao = dtInativacao;
    }

    public Boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public String getCdUrlSite() {
        return cdUrlSite;
    }

    public void setCdUrlSite(String cdUrlSite) {
        this.cdUrlSite = cdUrlSite;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public TipoLocal getIdTipoLocal() {
        return idTipoLocal;
    }

    public void setIdTipoLocal(TipoLocal idTipoLocal) {
        this.idTipoLocal = idTipoLocal;
    }
    
    public Estado getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLocalEvento != null ? idLocalEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof LocalEvento)) {
            return false;
        }
        LocalEvento other = (LocalEvento) object;
        if ((this.idLocalEvento == null && other.idLocalEvento != null) || (this.idLocalEvento != null && !this.idLocalEvento.equals(other.idLocalEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsLocalEvento;
    }

    @XmlTransient
    public Collection<ContratoCliente> getContratoClienteCollection() {
        return contratoClienteCollection;
    }

    public void setContratoClienteCollection(Collection<ContratoCliente> contratoClienteCollection) {
        this.contratoClienteCollection = contratoClienteCollection;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

}
