/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "mw_base")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Base.findAll", query = "SELECT b FROM Base b"),
    @NamedQuery(name = "Base.findByIdBase", query = "SELECT b FROM Base b WHERE b.idBase = :idBase"),
    @NamedQuery(name = "Base.findByDsNomeBaseSql", query = "SELECT b FROM Base b WHERE b.dsNomeBaseSql = :dsNomeBaseSql"),
    @NamedQuery(name = "Base.findByDsNomeTeatro", query = "SELECT b FROM Base b WHERE b.dsNomeTeatro = :dsNomeTeatro"),
    @NamedQuery(name = "Base.findByInAtivo", query = "SELECT b FROM Base b WHERE b.inAtivo = :inAtivo"),
    @NamedQuery(name = "Base.findByDsRzSocial", query = "SELECT b FROM Base b WHERE b.dsRzSocial = :dsRzSocial"),
    @NamedQuery(name = "Base.findByCdCpfCnpj", query = "SELECT b FROM Base b WHERE b.cdCpfCnpj = :cdCpfCnpj"),
    @NamedQuery(name = "Base.findByQtPrazoRepasseEmDias", query = "SELECT b FROM Base b WHERE b.qtPrazoRepasseEmDias = :qtPrazoRepasseEmDias"),
    @NamedQuery(name = "Base.findByDsNomeBanco", query = "SELECT b FROM Base b WHERE b.dsNomeBanco = :dsNomeBanco"),
    @NamedQuery(name = "Base.findByDsNrBanco", query = "SELECT b FROM Base b WHERE b.dsNrBanco = :dsNrBanco"),
    @NamedQuery(name = "Base.findByDsNrAgencia", query = "SELECT b FROM Base b WHERE b.dsNrAgencia = :dsNrAgencia"),
    @NamedQuery(name = "Base.findByDsNrConta", query = "SELECT b FROM Base b WHERE b.dsNrConta = :dsNrConta"),
    @NamedQuery(name = "Base.findByInPoupancaCc", query = "SELECT b FROM Base b WHERE b.inPoupancaCc = :inPoupancaCc"),
    @NamedQuery(name = "Base.findByDsNomeContato", query = "SELECT b FROM Base b WHERE b.dsNomeContato = :dsNomeContato"),
    @NamedQuery(name = "Base.findByDsDddTelFixo", query = "SELECT b FROM Base b WHERE b.dsDddTelFixo = :dsDddTelFixo"),
    @NamedQuery(name = "Base.findByDsTelFixo", query = "SELECT b FROM Base b WHERE b.dsTelFixo = :dsTelFixo"),
    @NamedQuery(name = "Base.findByDsDddCel", query = "SELECT b FROM Base b WHERE b.dsDddCel = :dsDddCel"),
    @NamedQuery(name = "Base.findByDsCel", query = "SELECT b FROM Base b WHERE b.dsCel = :dsCel"),
    @NamedQuery(name = "Base.findByDsEmail", query = "SELECT b FROM Base b WHERE b.dsEmail = :dsEmail"),
    @NamedQuery(name = "Base.findByVlTaxaCartaoCred", query = "SELECT b FROM Base b WHERE b.vlTaxaCartaoCred = :vlTaxaCartaoCred"),
    @NamedQuery(name = "Base.findByVlTaxaCartaoDeb", query = "SELECT b FROM Base b WHERE b.vlTaxaCartaoDeb = :vlTaxaCartaoDeb"),
    @NamedQuery(name = "Base.findByVlIngresso", query = "SELECT b FROM Base b WHERE b.vlIngresso = :vlIngresso"),
    @NamedQuery(name = "Base.findByVlTaxaRepasse", query = "SELECT b FROM Base b WHERE b.vlTaxaRepasse = :vlTaxaRepasse")})
public class Base implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_base")
    private Integer idBase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_nome_base_sql")
    private String dsNomeBaseSql;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "ds_nome_teatro")
    private String dsNomeTeatro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private Character inAtivo;
    @Size(max = 80)
    @Column(name = "ds_rz_social")
    private String dsRzSocial;
    @Size(max = 14)
    @Column(name = "cd_cpf_cnpj")
    private String cdCpfCnpj;
    @Column(name = "qt_prazo_repasse_em_dias")
    private Integer qtPrazoRepasseEmDias;
    @Size(max = 50)
    @Column(name = "ds_nome_banco")
    private String dsNomeBanco;
    @Size(max = 6)
    @Column(name = "ds_nr_banco")
    private String dsNrBanco;
    @Size(max = 8)
    @Column(name = "ds_nr_agencia")
    private String dsNrAgencia;
    @Size(max = 8)
    @Column(name = "ds_nr_conta")
    private String dsNrConta;
    @Column(name = "in_poupanca_cc")
    private Character inPoupancaCc;
    @Size(max = 60)
    @Column(name = "ds_nome_contato")
    private String dsNomeContato;
    @Size(max = 3)
    @Column(name = "ds_ddd_tel_fixo")
    private String dsDddTelFixo;
    @Size(max = 12)
    @Column(name = "ds_tel_fixo")
    private String dsTelFixo;
    @Size(max = 3)
    @Column(name = "ds_ddd_cel")
    private String dsDddCel;
    @Size(max = 12)
    @Column(name = "ds_cel")
    private String dsCel;
    @Size(max = 100)
    @Column(name = "ds_email")
    private String dsEmail;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "vl_taxa_cartao_cred")
    private BigDecimal vlTaxaCartaoCred;
    @Column(name = "vl_taxa_cartao_deb")
    private BigDecimal vlTaxaCartaoDeb;
    @Column(name = "vl_ingresso")
    private BigDecimal vlIngresso;
    @Column(name = "vl_taxa_repasse")
    private BigDecimal vlTaxaRepasse;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBase")
    private Collection<Evento> eventoCollection;

    public Base() {
    }

    public Base(Integer idBase) {
        this.idBase = idBase;
    }

    public Base(Integer idBase, String dsNomeBaseSql, String dsNomeTeatro, Character inAtivo) {
        this.idBase = idBase;
        this.dsNomeBaseSql = dsNomeBaseSql;
        this.dsNomeTeatro = dsNomeTeatro;
        this.inAtivo = inAtivo;
    }
    

    public Integer getIdBase() {
        return idBase;
    }

    public void setIdBase(Integer idBase) {
        this.idBase = idBase;
    }

    public String getDsNomeBaseSql() {
        return dsNomeBaseSql;
    }

    public void setDsNomeBaseSql(String dsNomeBaseSql) {
        this.dsNomeBaseSql = dsNomeBaseSql;
    }

    public String getDsNomeTeatro() {
        return dsNomeTeatro;
    }

    public void setDsNomeTeatro(String dsNomeTeatro) {
        this.dsNomeTeatro = dsNomeTeatro;
    }

    public Character getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Character inAtivo) {
        this.inAtivo = inAtivo;
    }

    public String getDsRzSocial() {
        return dsRzSocial;
    }

    public void setDsRzSocial(String dsRzSocial) {
        this.dsRzSocial = dsRzSocial;
    }

    public String getCdCpfCnpj() {
        return cdCpfCnpj;
    }

    public void setCdCpfCnpj(String cdCpfCnpj) {
        this.cdCpfCnpj = cdCpfCnpj;
    }

    public Integer getQtPrazoRepasseEmDias() {
        return qtPrazoRepasseEmDias;
    }

    public void setQtPrazoRepasseEmDias(Integer qtPrazoRepasseEmDias) {
        this.qtPrazoRepasseEmDias = qtPrazoRepasseEmDias;
    }

    public String getDsNomeBanco() {
        return dsNomeBanco;
    }

    public void setDsNomeBanco(String dsNomeBanco) {
        this.dsNomeBanco = dsNomeBanco;
    }

    public String getDsNrBanco() {
        return dsNrBanco;
    }

    public void setDsNrBanco(String dsNrBanco) {
        this.dsNrBanco = dsNrBanco;
    }

    public String getDsNrAgencia() {
        return dsNrAgencia;
    }

    public void setDsNrAgencia(String dsNrAgencia) {
        this.dsNrAgencia = dsNrAgencia;
    }

    public String getDsNrConta() {
        return dsNrConta;
    }

    public void setDsNrConta(String dsNrConta) {
        this.dsNrConta = dsNrConta;
    }

    public Character getInPoupancaCc() {
        return inPoupancaCc;
    }

    public void setInPoupancaCc(Character inPoupancaCc) {
        this.inPoupancaCc = inPoupancaCc;
    }

    public String getDsNomeContato() {
        return dsNomeContato;
    }

    public void setDsNomeContato(String dsNomeContato) {
        this.dsNomeContato = dsNomeContato;
    }

    public String getDsDddTelFixo() {
        return dsDddTelFixo;
    }

    public void setDsDddTelFixo(String dsDddTelFixo) {
        this.dsDddTelFixo = dsDddTelFixo;
    }

    public String getDsTelFixo() {
        return dsTelFixo;
    }

    public void setDsTelFixo(String dsTelFixo) {
        this.dsTelFixo = dsTelFixo;
    }

    public String getDsDddCel() {
        return dsDddCel;
    }

    public void setDsDddCel(String dsDddCel) {
        this.dsDddCel = dsDddCel;
    }

    public String getDsCel() {
        return dsCel;
    }

    public void setDsCel(String dsCel) {
        this.dsCel = dsCel;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public BigDecimal getVlTaxaCartaoCred() {
        return vlTaxaCartaoCred;
    }

    public void setVlTaxaCartaoCred(BigDecimal vlTaxaCartaoCred) {
        this.vlTaxaCartaoCred = vlTaxaCartaoCred;
    }

    public BigDecimal getVlTaxaCartaoDeb() {
        return vlTaxaCartaoDeb;
    }

    public void setVlTaxaCartaoDeb(BigDecimal vlTaxaCartaoDeb) {
        this.vlTaxaCartaoDeb = vlTaxaCartaoDeb;
    }

    public BigDecimal getVlIngresso() {
        return vlIngresso;
    }

    public void setVlIngresso(BigDecimal vlIngresso) {
        this.vlIngresso = vlIngresso;
    }

    public BigDecimal getVlTaxaRepasse() {
        return vlTaxaRepasse;
    }

    public void setVlTaxaRepasse(BigDecimal vlTaxaRepasse) {
        this.vlTaxaRepasse = vlTaxaRepasse;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBase != null ? idBase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Base)) {
            return false;
        }
        Base other = (Base) object;
        if ((this.idBase == null && other.idBase != null) || (this.idBase != null && !this.idBase.equals(other.idBase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsNomeTeatro;
    }
    
}
