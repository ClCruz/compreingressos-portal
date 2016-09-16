/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "mw_conta_contabil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContaContabil.findAll", query = "SELECT c FROM ContaContabil c"),
    @NamedQuery(name = "ContaContabil.findByIdContaContabil", query = "SELECT c FROM ContaContabil c WHERE c.idContaContabil = :idContaContabil"),
    @NamedQuery(name = "ContaContabil.findByDsContaContabil", query = "SELECT c FROM ContaContabil c WHERE c.dsContaContabil = :dsContaContabil"),
    @NamedQuery(name = "ContaContabil.findByInAtivo", query = "SELECT c FROM ContaContabil c WHERE c.inAtivo = :inAtivo"),
    @NamedQuery(name = "ContaContabil.findByNrContaContabil", query = "SELECT c FROM ContaContabil c WHERE c.nrContaContabil = :nrContaContabil")})
public class ContaContabil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_conta_contabil")
    private Integer idContaContabil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60, message = "O campo Descrição deve conter entre 1 e 60 caracteres")
    @Column(name = "ds_conta_contabil")
    private String dsContaContabil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo = true;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30, message = "O campo Número da Conta deve conter entre 1 e 30 caracteres")
    @Pattern(regexp = "^[0-9]+$", message="O campo Número da Conta deve conter somente números")
    @Column(name = "nr_conta_contabil")
    private String nrContaContabil;
    @OneToMany(mappedBy = "idContaContabilDeb")
    private Collection<TipoLancamento> tipoLancamentoCollection;
    @OneToMany(mappedBy = "idContaContabilCre")
    private Collection<TipoLancamento> tipoLancamentoCollection1;

    public ContaContabil() {
    }
    
    public ContaContabil(Integer idContaContabil) {
        this.idContaContabil = idContaContabil;
    }

    public ContaContabil(Integer idContaContabil, String dsContaContabil, boolean inAtivo, String nrContaContabil) {
        this.idContaContabil = idContaContabil;
        this.dsContaContabil = dsContaContabil;
        this.inAtivo = inAtivo;
        this.nrContaContabil = nrContaContabil;
    }

    public Integer getIdContaContabil() {
        return idContaContabil;
    }

    public void setIdContaContabil(Integer idContaContabil) {
        this.idContaContabil = idContaContabil;
    }

    public String getDsContaContabil() {
        return dsContaContabil;
    }

    public void setDsContaContabil(String dsContaContabil) {
        this.dsContaContabil = dsContaContabil;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public String getNrContaContabil() {
        return nrContaContabil;
    }

    public void setNrContaContabil(String nrContaContabil) {
        this.nrContaContabil = nrContaContabil;
    }

    @XmlTransient
    public Collection<TipoLancamento> getTipoLancamentoCollection() {
        return tipoLancamentoCollection;
    }

    public void setTipoLancamentoCollection(Collection<TipoLancamento> tipoLancamentoCollection) {
        this.tipoLancamentoCollection = tipoLancamentoCollection;
    }

    @XmlTransient
    public Collection<TipoLancamento> getTipoLancamentoCollection1() {
        return tipoLancamentoCollection1;
    }

    public void setTipoLancamentoCollection1(Collection<TipoLancamento> tipoLancamentoCollection1) {
        this.tipoLancamentoCollection1 = tipoLancamentoCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContaContabil != null ? idContaContabil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaContabil)) {
            return false;
        }
        ContaContabil other = (ContaContabil) object;
        if ((this.idContaContabil == null && other.idContaContabil != null) || (this.idContaContabil != null && !this.idContaContabil.equals(other.idContaContabil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContaContabil[ idContaContabil=" + idContaContabil + " ]";
    }
    
}
