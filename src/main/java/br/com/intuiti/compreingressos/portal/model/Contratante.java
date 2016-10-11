/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_contratante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratante.findAll", query = "SELECT c FROM Contratante c"),
    @NamedQuery(name = "Contratante.findByNmRazaoSocial", query = "SELECT c FROM Contratante c WHERE c.nmRazaoSocial = :nmRazaoSocial"),
    @NamedQuery(name = "Contratante.findByCdCnpjCpf", query = "SELECT c FROM Contratante c WHERE c.cdCnpjCpf = :cdCnpjCpf"),
    @NamedQuery(name = "Contratante.findByCdCep", query = "SELECT c FROM Contratante c WHERE c.cdCep = :cdCep"),
    @NamedQuery(name = "Contratante.findByNrTelefone", query = "SELECT c FROM Contratante c WHERE c.nrTelefone = :nrTelefone"),
    @NamedQuery(name = "Contratante.findByCdEmail", query = "SELECT c FROM Contratante c WHERE c.cdEmail = :cdEmail"),
    @NamedQuery(name = "Contratante.findByNmRepresLegal", query = "SELECT c FROM Contratante c WHERE c.nmRepresLegal = :nmRepresLegal"),
    @NamedQuery(name = "Contratante.findByCdCpfRepresLegal", query = "SELECT c FROM Contratante c WHERE c.cdCpfRepresLegal = :cdCpfRepresLegal"),
    @NamedQuery(name = "Contratante.findByNmCargoRepresLegal", query = "SELECT c FROM Contratante c WHERE c.nmCargoRepresLegal = :nmCargoRepresLegal"),
    @NamedQuery(name = "Contratante.findByCdRgRepresLegal", query = "SELECT c FROM Contratante c WHERE c.cdRgRepresLegal = :cdRgRepresLegal"),
    @NamedQuery(name = "Contratante.findByDsEnderecoRepresLegal", query = "SELECT c FROM Contratante c WHERE c.dsEnderecoRepresLegal = :dsEnderecoRepresLegal"),
    @NamedQuery(name = "Contratante.findByDsBairroRepresLegal", query = "SELECT c FROM Contratante c WHERE c.dsBairroRepresLegal = :dsBairroRepresLegal"),
    @NamedQuery(name = "Contratante.findByCdCepRepresLegal", query = "SELECT c FROM Contratante c WHERE c.cdCepRepresLegal = :cdCepRepresLegal"),
    @NamedQuery(name = "Contratante.findByNrTelefoneRepresLegal", query = "SELECT c FROM Contratante c WHERE c.nrTelefoneRepresLegal = :nrTelefoneRepresLegal"),
    @NamedQuery(name = "Contratante.findByNrCelularRepresLegal", query = "SELECT c FROM Contratante c WHERE c.nrCelularRepresLegal = :nrCelularRepresLegal"),
    @NamedQuery(name = "Contratante.findByCdEmailRepresLegal", query = "SELECT c FROM Contratante c WHERE c.cdEmailRepresLegal = :cdEmailRepresLegal"),
    @NamedQuery(name = "Contratante.findByDsEndereco", query = "SELECT c FROM Contratante c WHERE c.dsEndereco = :dsEndereco"),
    @NamedQuery(name = "Contratante.findByInAtivo", query = "SELECT c FROM Contratante c WHERE c.inAtivo = :inAtivo"),
    @NamedQuery(name = "Contratante.findByCdUfRgRepresLegal", query = "SELECT c FROM Contratante c WHERE c.cdUfRgRepresLegal = :cdUfRgRepresLegal"),
    @NamedQuery(name = "Contratante.findByDsBairro", query = "SELECT c FROM Contratante c WHERE c.dsBairro = :dsBairro"),
    @NamedQuery(name = "Contratante.findByDtInativacao", query = "SELECT c FROM Contratante c WHERE c.dtInativacao = :dtInativacao"),
    @NamedQuery(name = "Contratante.findByIdContratante", query = "SELECT c FROM Contratante c WHERE c.idContratante = :idContratante"),
    @NamedQuery(name = "Contratante.findByDsNumero", query = "SELECT c FROM Contratante c WHERE c.dsNumero = :dsNumero"),
    @NamedQuery(name = "Contratante.findByDsComplemento", query = "SELECT c FROM Contratante c WHERE c.dsComplemento = :dsComplemento"),
    @NamedQuery(name = "Contratante.findByDsNumeroEnderecoRepresLegal", query = "SELECT c FROM Contratante c WHERE c.dsNumeroEnderecoRepresLegal = :dsNumeroEnderecoRepresLegal"),
    @NamedQuery(name = "Contratante.findByDsComplementoEnderecoRepresLegal", query = "SELECT c FROM Contratante c WHERE c.dsComplementoEnderecoRepresLegal = :dsComplementoEnderecoRepresLegal"),
    @NamedQuery(name = "Contratante.findByCdSapCardcode", query = "SELECT c FROM Contratante c WHERE c.cdSapCardcode = :cdSapCardcode"),
    @NamedQuery(name = "Contratante.findByInPessoaJuridica", query = "SELECT c FROM Contratante c WHERE c.inPessoaJuridica = :inPessoaJuridica"),
    @NamedQuery(name = "Contratante.findByNmFantasia", query = "SELECT c FROM Contratante c WHERE c.nmFantasia = :nmFantasia"),
    @NamedQuery(name = "Contratante.findByCdAgenciaBanco", query = "SELECT c FROM Contratante c WHERE c.cdAgenciaBanco = :cdAgenciaBanco"),
    @NamedQuery(name = "Contratante.findByDvAgenciaBanco", query = "SELECT c FROM Contratante c WHERE c.dvAgenciaBanco = :dvAgenciaBanco"),
    @NamedQuery(name = "Contratante.findByCdContaBancaria", query = "SELECT c FROM Contratante c WHERE c.cdContaBancaria = :cdContaBancaria"),
    @NamedQuery(name = "Contratante.findByDvContaBancaria", query = "SELECT c FROM Contratante c WHERE c.dvContaBancaria = :dvContaBancaria"),
    @NamedQuery(name = "Contratante.findByNmTitularContaRepasse", query = "SELECT c FROM Contratante c WHERE c.nmTitularContaRepasse = :nmTitularContaRepasse"),
    @NamedQuery(name = "Contratante.findByCdCpfCnpjTitularConta", query = "SELECT c FROM Contratante c WHERE c.cdCpfCnpjTitularConta = :cdCpfCnpjTitularConta"),
    @NamedQuery(name = "Contratante.findAllMunicipio", query = "SELECT m FROM Municipio m WHERE m.idEstado = :idEstado ORDER BY m.dsMunicipio")})
public class Contratante implements Serializable {

    @OneToMany(mappedBy = "idContratante")
    private Collection<ContratoCliente> contratoClienteCollection;

    private static final long serialVersionUID = 1L;
    @Size(max = 150, message = "O campo Razão Social deve ter no máximo 150 caracteres")
    @NotNull
    @Column(name = "nm_razao_social")
    private String nmRazaoSocial;
    @Column(name = "cd_cnpj_cpf")
    private BigInteger cdCnpjCpf;
    @Size(max = 8, message = "O campo CEP deve ter no máximo 8 caracteres")
    @Column(name = "cd_cep")
    private String cdCep;
    @Size(max = 50, message = "O campo Telefone deve ter no máximo 50 caracteres")
    @NotNull
    @Column(name = "nr_telefone")
    private String nrTelefone;
    @Size(max = 100, message = "O campo E-mail deve ter no máximo 100 caracteres")
    @NotNull
    @Column(name = "cd_email")
    private String cdEmail;
    @Size(max = 100, message = "O campo deve ter no máximo 100 caracteres")
    @Column(name = "nm_repres_legal")
    private String nmRepresLegal;
    @Size(max = 11, message = "O campo deve ter no máximo 11 caracteres")
    @Column(name = "cd_cpf_repres_legal")
    private String cdCpfRepresLegal;
    @Size(max = 100, message = "O campo deve ter no máximo 100 caracteres")
    @Column(name = "nm_cargo_repres_legal")
    private String nmCargoRepresLegal;
    @Size(max = 12, message = "O campo deve ter no máximo 12 caracteres")
    @Column(name = "cd_rg_repres_legal")
    private String cdRgRepresLegal;
    @Size(max = 100, message = "O campo deve ter no máximo 100 caracteres")
    @Column(name = "ds_endereco_repres_legal")
    private String dsEnderecoRepresLegal;
    @Size(max = 30, message = "O campo deve ter no máximo 30 caracteres")
    @Column(name = "ds_bairro_repres_legal")
    private String dsBairroRepresLegal;
    @Size(max = 8, message = "O campo deve ter no máximo 8 caracteres")
    @Column(name = "cd_cep_repres_legal")
    private String cdCepRepresLegal;
    @Size(max = 50, message = "O campo deve ter no máximo 50 caracteres")
    @Column(name = "nr_telefone_repres_legal")
    private String nrTelefoneRepresLegal;
    @Size(max = 50, message = "O campo deve ter no máximo 50 caracteres")
    @Column(name = "nr_celular_repres_legal")
    private String nrCelularRepresLegal;
    @Size(max = 100, message = "O campo deve ter no máximo 100 caracteres")
    @Column(name = "cd_email_repres_legal")
    private String cdEmailRepresLegal;
    @Size(max = 100, message = "O campo deve ter no máximo 100 caracteres")
    @Column(name = "ds_endereco")
    private String dsEndereco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @Size(max = 2)
    @Column(name = "cd_uf_rg_repres_legal")
    private String cdUfRgRepresLegal;
    @Size(max = 100)
    @Column(name = "ds_bairro")
    private String dsBairro;
    @Column(name = "dt_inativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInativacao;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contratante")
    private Integer idContratante;
    @Size(max = 100)
    @Pattern(regexp = "^[0-9]+$", message="O campo Número deve conter somente números.")
    @Column(name = "ds_numero")
    private String dsNumero;
    @Size(max = 100)
    @Column(name = "ds_complemento")
    private String dsComplemento;
    @Size(max = 100)
    @Column(name = "ds_numero_endereco_repres_legal")
    private String dsNumeroEnderecoRepresLegal;
    @Size(max = 100)
    @Column(name = "ds_complemento_endereco_repres_legal")
    private String dsComplementoEnderecoRepresLegal;
    @Size(max = 30)
    @Column(name = "cd_sap_cardcode")
    private String cdSapCardcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_pessoa_juridica")
    private boolean inPessoaJuridica;
    @Size(max = 20)
    @Column(name = "nm_fantasia")
    private String nmFantasia;
    @Size(max = 10)
    @Column(name = "cd_agencia_banco")
    private String cdAgenciaBanco;
    @Column(name = "dv_agencia_banco")
    private Character dvAgenciaBanco;
    @Size(max = 20)
    @Column(name = "cd_conta_bancaria")
    private String cdContaBancaria;
    @Column(name = "dv_conta_bancaria")
    private Character dvContaBancaria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nm_titular_conta_repasse")
    private String nmTitularContaRepasse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "cd_cpf_cnpj_titular_conta")
    private String cdCpfCnpjTitularConta;
    @JoinColumn(name = "id_banco", referencedColumnName = "id_banco")
    @ManyToOne
    private Banco idBanco;
    @NotNull
    @JoinColumn(name = "id_municipio", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipio idMunicipio;
    @JoinColumn(name = "id_municipio_repres_legal", referencedColumnName = "id_municipio")
    @ManyToOne
    private Municipio idMunicipioRepresLegal;
    @Transient
    private Estado idEstado;
    @Transient
    private Estado idEstadoRepresLegal;

	public Contratante() {
        idBanco = new Banco();
        idMunicipio = new Municipio();
        idMunicipioRepresLegal = new Municipio();
    }
    
    public Contratante(Banco idBanco, Municipio idMunicipio, Municipio idMunicipioRepresLegal){
        this.idBanco = idBanco;
        this.idMunicipio = idMunicipio;
        this.idMunicipioRepresLegal = idMunicipioRepresLegal;
    }

    public Contratante(Integer idContratante) {
        this.idContratante = idContratante;
    }


    public Contratante(Integer idContratante, boolean inAtivo, boolean inPessoaJuridica, String nmTitularContaRepasse, String cdCpfCnpjTitularConta) {
        this.idContratante = idContratante;
        this.inAtivo = inAtivo;
        this.inPessoaJuridica = inPessoaJuridica;
        this.nmTitularContaRepasse = nmTitularContaRepasse;
        this.cdCpfCnpjTitularConta = cdCpfCnpjTitularConta;
    }

    public String getNmRazaoSocial() {
        return nmRazaoSocial;
    }

    public void setNmRazaoSocial(String nmRazaoSocial) {
        this.nmRazaoSocial = nmRazaoSocial;
    }

    public BigInteger getCdCnpjCpf() {
        return cdCnpjCpf;
    }

    public void setCdCnpjCpf(BigInteger cdCnpjCpf) {
        this.cdCnpjCpf = cdCnpjCpf;
    }

    public String getCdCep() {
        return cdCep;
    }

    public void setCdCep(String cdCep) {
        this.cdCep = cdCep;
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

    public String getNmRepresLegal() {
        return nmRepresLegal;
    }

    public void setNmRepresLegal(String nmRepresLegal) {
        this.nmRepresLegal = nmRepresLegal;
    }

    public String getCdCpfRepresLegal() {
        return cdCpfRepresLegal;
    }

    public void setCdCpfRepresLegal(String cdCpfRepresLegal) {
        this.cdCpfRepresLegal = cdCpfRepresLegal;
    }

    public String getNmCargoRepresLegal() {
        return nmCargoRepresLegal;
    }

    public void setNmCargoRepresLegal(String nmCargoRepresLegal) {
        this.nmCargoRepresLegal = nmCargoRepresLegal;
    }

    public String getCdRgRepresLegal() {
        return cdRgRepresLegal;
    }

    public void setCdRgRepresLegal(String cdRgRepresLegal) {
        this.cdRgRepresLegal = cdRgRepresLegal;
    }

    public String getDsEnderecoRepresLegal() {
        return dsEnderecoRepresLegal;
    }

    public void setDsEnderecoRepresLegal(String dsEnderecoRepresLegal) {
        this.dsEnderecoRepresLegal = dsEnderecoRepresLegal;
    }

    public String getDsBairroRepresLegal() {
        return dsBairroRepresLegal;
    }

    public void setDsBairroRepresLegal(String dsBairroRepresLegal) {
        this.dsBairroRepresLegal = dsBairroRepresLegal;
    }

    public String getCdCepRepresLegal() {
        return cdCepRepresLegal;
    }

    public void setCdCepRepresLegal(String cdCepRepresLegal) {
        this.cdCepRepresLegal = cdCepRepresLegal;
    }

    public String getNrTelefoneRepresLegal() {
        return nrTelefoneRepresLegal;
    }

    public void setNrTelefoneRepresLegal(String nrTelefoneRepresLegal) {
        this.nrTelefoneRepresLegal = nrTelefoneRepresLegal;
    }

    public String getNrCelularRepresLegal() {
        return nrCelularRepresLegal;
    }

    public void setNrCelularRepresLegal(String nrCelularRepresLegal) {
        this.nrCelularRepresLegal = nrCelularRepresLegal;
    }

    public String getCdEmailRepresLegal() {
        return cdEmailRepresLegal;
    }

    public void setCdEmailRepresLegal(String cdEmailRepresLegal) {
        this.cdEmailRepresLegal = cdEmailRepresLegal;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public String getCdUfRgRepresLegal() {
        return cdUfRgRepresLegal;
    }

    public void setCdUfRgRepresLegal(String cdUfRgRepresLegal) {
        this.cdUfRgRepresLegal = cdUfRgRepresLegal;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public Date getDtInativacao() {
        return dtInativacao;
    }

    public void setDtInativacao(Date dtInativacao) {
        this.dtInativacao = dtInativacao;
    }

    public Integer getIdContratante() {
        return idContratante;
    }

    public void setIdContratante(Integer idContratante) {
        this.idContratante = idContratante;
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
        
    public String getDsNumeroEnderecoRepresLegal() {
        return dsNumeroEnderecoRepresLegal;
    }

    public void setDsNumeroEnderecoRepresLegal(String dsNumeroEnderecoRepresLegal) {
        this.dsNumeroEnderecoRepresLegal = dsNumeroEnderecoRepresLegal;
    }

    public String getDsComplementoEnderecoRepresLegal() {
        return dsComplementoEnderecoRepresLegal;
    }

    public void setDsComplementoEnderecoRepresLegal(String dsComplementoEnderecoRepresLegal) {
        this.dsComplementoEnderecoRepresLegal = dsComplementoEnderecoRepresLegal;
    }

    public String getCdSapCardcode() {
        return cdSapCardcode;
    }

    public void setCdSapCardcode(String cdSapCardcode) {
        this.cdSapCardcode = cdSapCardcode;
    }

    public boolean getInPessoaJuridica() {
        return inPessoaJuridica;
    }

    public void setInPessoaJuridica(boolean inPessoaJuridica) {
        this.inPessoaJuridica = inPessoaJuridica;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public String getCdAgenciaBanco() {
        return cdAgenciaBanco;
    }

    public void setCdAgenciaBanco(String cdAgenciaBanco) {
        this.cdAgenciaBanco = cdAgenciaBanco;
    }

    public Character getDvAgenciaBanco() {
        return dvAgenciaBanco;
    }

    public void setDvAgenciaBanco(Character dvAgenciaBanco) {
        this.dvAgenciaBanco = dvAgenciaBanco;
    }

    public String getCdContaBancaria() {
        return cdContaBancaria;
    }

    public void setCdContaBancaria(String cdContaBancaria) {
        this.cdContaBancaria = cdContaBancaria;
    }

    public Character getDvContaBancaria() {
        return dvContaBancaria;
    }

    public void setDvContaBancaria(Character dvContaBancaria) {
        this.dvContaBancaria = dvContaBancaria;
    }

    public String getNmTitularContaRepasse() {
        return nmTitularContaRepasse;
    }

    public void setNmTitularContaRepasse(String nmTitularContaRepasse) {
        this.nmTitularContaRepasse = nmTitularContaRepasse;
    }

    public String getCdCpfCnpjTitularConta() {
        return cdCpfCnpjTitularConta;
    }

    public void setCdCpfCnpjTitularConta(String cdCpfCnpjTitularConta) {
        this.cdCpfCnpjTitularConta = cdCpfCnpjTitularConta;
    }

    public Banco getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Banco idBanco) {
        this.idBanco = idBanco;
    }

    public Municipio getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipio idMunicipio) {
        this.idMunicipio = idMunicipio;
    }
    
    public Municipio getIdMunicipioRepresLegal() {
        return idMunicipioRepresLegal;
    }
    
    public void setIdMunicipioRepresLegal(Municipio idMunicipioRepresLegal) {
        this.idMunicipioRepresLegal = idMunicipioRepresLegal;
    }
    
    public Estado getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}
	
	public Estado getIdEstadoRepresLegal() {
		return idEstadoRepresLegal;
	}

	public void setIdEstadoRepresLegal(Estado idEstadoRepresLegal) {
		this.idEstadoRepresLegal = idEstadoRepresLegal;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContratante != null ? idContratante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contratante)) {
            return false;
        }
        Contratante other = (Contratante) object;
        if ((this.idContratante == null && other.idContratante != null) || (this.idContratante != null && !this.idContratante.equals(other.idContratante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nmRazaoSocial;
    }

    @XmlTransient
    public Collection<ContratoCliente> getContratoClienteCollection() {
        return contratoClienteCollection;
    }

    public void setContratoClienteCollection(Collection<ContratoCliente> contratoClienteCollection) {
        this.contratoClienteCollection = contratoClienteCollection;
    }
    
}
