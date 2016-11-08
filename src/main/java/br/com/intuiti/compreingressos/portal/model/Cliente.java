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

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByDsNome", query = "SELECT c FROM Cliente c WHERE c.dsNome = :dsNome"),
    @NamedQuery(name = "Cliente.findByDsSobrenome", query = "SELECT c FROM Cliente c WHERE c.dsSobrenome = :dsSobrenome"),
    @NamedQuery(name = "Cliente.findByDtNascimento", query = "SELECT c FROM Cliente c WHERE c.dtNascimento = :dtNascimento"),
    @NamedQuery(name = "Cliente.findByDsDddTelefone", query = "SELECT c FROM Cliente c WHERE c.dsDddTelefone = :dsDddTelefone"),
    @NamedQuery(name = "Cliente.findByDsTelefone", query = "SELECT c FROM Cliente c WHERE c.dsTelefone = :dsTelefone"),
    @NamedQuery(name = "Cliente.findByDsDddCelular", query = "SELECT c FROM Cliente c WHERE c.dsDddCelular = :dsDddCelular"),
    @NamedQuery(name = "Cliente.findByDsCelular", query = "SELECT c FROM Cliente c WHERE c.dsCelular = :dsCelular"),
    @NamedQuery(name = "Cliente.findByCdRg", query = "SELECT c FROM Cliente c WHERE c.cdRg = :cdRg"),
    @NamedQuery(name = "Cliente.findByCdCpf", query = "SELECT c FROM Cliente c WHERE c.cdCpf = :cdCpf"),
    @NamedQuery(name = "Cliente.findByDsEndereco", query = "SELECT c FROM Cliente c WHERE c.dsEndereco = :dsEndereco"),
    @NamedQuery(name = "Cliente.findByDsComplEndereco", query = "SELECT c FROM Cliente c WHERE c.dsComplEndereco = :dsComplEndereco"),
    @NamedQuery(name = "Cliente.findByDsBairro", query = "SELECT c FROM Cliente c WHERE c.dsBairro = :dsBairro"),
    @NamedQuery(name = "Cliente.findByDsCidade", query = "SELECT c FROM Cliente c WHERE c.dsCidade = :dsCidade"),
    @NamedQuery(name = "Cliente.findByCdCep", query = "SELECT c FROM Cliente c WHERE c.cdCep = :cdCep"),
    @NamedQuery(name = "Cliente.findByCdEmailLogin", query = "SELECT c FROM Cliente c WHERE c.cdEmailLogin = :cdEmailLogin"),
    @NamedQuery(name = "Cliente.findByCdPassword", query = "SELECT c FROM Cliente c WHERE c.cdPassword = :cdPassword"),
    @NamedQuery(name = "Cliente.findByInRecebeInfo", query = "SELECT c FROM Cliente c WHERE c.inRecebeInfo = :inRecebeInfo"),
    @NamedQuery(name = "Cliente.findByInRecebeSms", query = "SELECT c FROM Cliente c WHERE c.inRecebeSms = :inRecebeSms"),
    @NamedQuery(name = "Cliente.findByInConcordaTermos", query = "SELECT c FROM Cliente c WHERE c.inConcordaTermos = :inConcordaTermos"),
    @NamedQuery(name = "Cliente.findByDtInclusao", query = "SELECT c FROM Cliente c WHERE c.dtInclusao = :dtInclusao"),
    @NamedQuery(name = "Cliente.findByInSexo", query = "SELECT c FROM Cliente c WHERE c.inSexo = :inSexo"),
    @NamedQuery(name = "Cliente.findByInAssinante", query = "SELECT c FROM Cliente c WHERE c.inAssinante = :inAssinante"),
    @NamedQuery(name = "Cliente.findByNrEndereco", query = "SELECT c FROM Cliente c WHERE c.nrEndereco = :nrEndereco")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private Integer idCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_nome")
    private String dsNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_sobrenome")
    private String dsSobrenome;
    @Column(name = "dt_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNascimento;
    @Size(max = 2)
    @Column(name = "ds_ddd_telefone")
    private String dsDddTelefone;
    @Size(max = 15)
    @Column(name = "ds_telefone")
    private String dsTelefone;
    @Size(max = 2)
    @Column(name = "ds_ddd_celular")
    private String dsDddCelular;
    @Size(max = 15)
    @Column(name = "ds_celular")
    private String dsCelular;
    @Size(max = 11)
    @Column(name = "cd_rg")
    private String cdRg;
    @Size(max = 11)
    @Column(name = "cd_cpf")
    private String cdCpf;
    @Size(max = 150)
    @Column(name = "ds_endereco")
    private String dsEndereco;
    @Size(max = 50)
    @Column(name = "ds_compl_endereco")
    private String dsComplEndereco;
    @Size(max = 50)
    @Column(name = "ds_bairro")
    private String dsBairro;
    @Size(max = 50)
    @Column(name = "ds_cidade")
    private String dsCidade;
    @Size(max = 8)
    @Column(name = "cd_cep")
    private String cdCep;
    @Size(max = 100)
    @Column(name = "cd_email_login")
    private String cdEmailLogin;
    @Size(max = 32)
    @Column(name = "cd_password")
    private String cdPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_recebe_info")
    private Character inRecebeInfo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_recebe_sms")
    private Character inRecebeSms;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_concorda_termos")
    private Character inConcordaTermos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_inclusao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtInclusao;
    @Column(name = "in_sexo")
    private Character inSexo;
    @Column(name = "in_assinante")
    private Character inAssinante;
    @Size(max = 15)
    @Column(name = "nr_endereco")
    private String nrEndereco;
    @OneToMany(mappedBy = "idCliente")
    private Collection<Base> baseCollection;
    @JoinColumn(name = "id_doc_estrangeiro", referencedColumnName = "id_doc_estrangeiro")
    @ManyToOne
    private DocEstrangeiro idDocEstrangeiro;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne
    private Estado idEstado;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String dsNome, String dsSobrenome, Character inRecebeInfo, Character inRecebeSms, Character inConcordaTermos, Date dtInclusao) {
        this.idCliente = idCliente;
        this.dsNome = dsNome;
        this.dsSobrenome = dsSobrenome;
        this.inRecebeInfo = inRecebeInfo;
        this.inRecebeSms = inRecebeSms;
        this.inConcordaTermos = inConcordaTermos;
        this.dtInclusao = dtInclusao;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsSobrenome() {
        return dsSobrenome;
    }

    public void setDsSobrenome(String dsSobrenome) {
        this.dsSobrenome = dsSobrenome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getDsDddTelefone() {
        return dsDddTelefone;
    }

    public void setDsDddTelefone(String dsDddTelefone) {
        this.dsDddTelefone = dsDddTelefone;
    }

    public String getDsTelefone() {
        return dsTelefone;
    }

    public void setDsTelefone(String dsTelefone) {
        this.dsTelefone = dsTelefone;
    }

    public String getDsDddCelular() {
        return dsDddCelular;
    }

    public void setDsDddCelular(String dsDddCelular) {
        this.dsDddCelular = dsDddCelular;
    }

    public String getDsCelular() {
        return dsCelular;
    }

    public void setDsCelular(String dsCelular) {
        this.dsCelular = dsCelular;
    }

    public String getCdRg() {
        return cdRg;
    }

    public void setCdRg(String cdRg) {
        this.cdRg = cdRg;
    }

    public String getCdCpf() {
        return cdCpf;
    }

    public void setCdCpf(String cdCpf) {
        this.cdCpf = cdCpf;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsComplEndereco() {
        return dsComplEndereco;
    }

    public void setDsComplEndereco(String dsComplEndereco) {
        this.dsComplEndereco = dsComplEndereco;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsCidade() {
        return dsCidade;
    }

    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public String getCdCep() {
        return cdCep;
    }

    public void setCdCep(String cdCep) {
        this.cdCep = cdCep;
    }

    public String getCdEmailLogin() {
        return cdEmailLogin;
    }

    public void setCdEmailLogin(String cdEmailLogin) {
        this.cdEmailLogin = cdEmailLogin;
    }

    public String getCdPassword() {
        return cdPassword;
    }

    public void setCdPassword(String cdPassword) {
        this.cdPassword = cdPassword;
    }

    public Character getInRecebeInfo() {
        return inRecebeInfo;
    }

    public void setInRecebeInfo(Character inRecebeInfo) {
        this.inRecebeInfo = inRecebeInfo;
    }

    public Character getInRecebeSms() {
        return inRecebeSms;
    }

    public void setInRecebeSms(Character inRecebeSms) {
        this.inRecebeSms = inRecebeSms;
    }

    public Character getInConcordaTermos() {
        return inConcordaTermos;
    }

    public void setInConcordaTermos(Character inConcordaTermos) {
        this.inConcordaTermos = inConcordaTermos;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Character getInSexo() {
        return inSexo;
    }

    public void setInSexo(Character inSexo) {
        this.inSexo = inSexo;
    }

    public Character getInAssinante() {
        return inAssinante;
    }

    public void setInAssinante(Character inAssinante) {
        this.inAssinante = inAssinante;
    }

    public String getNrEndereco() {
        return nrEndereco;
    }

    public void setNrEndereco(String nrEndereco) {
        this.nrEndereco = nrEndereco;
    }

    @XmlTransient
    public Collection<Base> getBaseCollection() {
        return baseCollection;
    }

    public void setBaseCollection(Collection<Base> baseCollection) {
        this.baseCollection = baseCollection;
    }

    public DocEstrangeiro getIdDocEstrangeiro() {
        return idDocEstrangeiro;
    }

    public void setIdDocEstrangeiro(DocEstrangeiro idDocEstrangeiro) {
        this.idDocEstrangeiro = idDocEstrangeiro;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsNome;
    }
    
}
