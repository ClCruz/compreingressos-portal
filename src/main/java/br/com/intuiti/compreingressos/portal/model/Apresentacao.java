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
@Table(name = "mw_apresentacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Apresentacao.findAll", query = "SELECT a FROM Apresentacao a"),
    @NamedQuery(name = "Apresentacao.findByIdApresentacao", query = "SELECT a FROM Apresentacao a WHERE a.idApresentacao = :idApresentacao"),
    @NamedQuery(name = "Apresentacao.findByDtApresentacao", query = "SELECT a FROM Apresentacao a WHERE a.dtApresentacao = :dtApresentacao"),
    @NamedQuery(name = "Apresentacao.findByCodApresentacao", query = "SELECT a FROM Apresentacao a WHERE a.codApresentacao = :codApresentacao"),
    @NamedQuery(name = "Apresentacao.findByHrApresentacao", query = "SELECT a FROM Apresentacao a WHERE a.hrApresentacao = :hrApresentacao"),
    @NamedQuery(name = "Apresentacao.findByDsPiso", query = "SELECT a FROM Apresentacao a WHERE a.dsPiso = :dsPiso"),
    @NamedQuery(name = "Apresentacao.findByInAtivo", query = "SELECT a FROM Apresentacao a WHERE a.inAtivo = :inAtivo")})
public class Apresentacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_apresentacao")
    private Integer idApresentacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_apresentacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtApresentacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodApresentacao")
    private int codApresentacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "hr_apresentacao")
    private String hrApresentacao;
    @Size(max = 30)
    @Column(name = "ds_piso")
    private String dsPiso;
    @Column(name = "in_ativo")
    private Boolean inAtivo;
    @JoinColumn(name = "id_evento", referencedColumnName = "id_evento")
    @ManyToOne(optional = false)
    private Evento idEvento;

    public Apresentacao() {
    }

    public Apresentacao(Integer idApresentacao) {
        this.idApresentacao = idApresentacao;
    }

    public Apresentacao(Integer idApresentacao, Date dtApresentacao, int codApresentacao, String hrApresentacao) {
        this.idApresentacao = idApresentacao;
        this.dtApresentacao = dtApresentacao;
        this.codApresentacao = codApresentacao;
        this.hrApresentacao = hrApresentacao;
    }

    public Integer getIdApresentacao() {
        return idApresentacao;
    }

    public void setIdApresentacao(Integer idApresentacao) {
        this.idApresentacao = idApresentacao;
    }

    public Date getDtApresentacao() {
        return dtApresentacao;
    }

    public void setDtApresentacao(Date dtApresentacao) {
        this.dtApresentacao = dtApresentacao;
    }

    public int getCodApresentacao() {
        return codApresentacao;
    }

    public void setCodApresentacao(int codApresentacao) {
        this.codApresentacao = codApresentacao;
    }

    public String getHrApresentacao() {
        return hrApresentacao;
    }

    public void setHrApresentacao(String hrApresentacao) {
        this.hrApresentacao = hrApresentacao;
    }

    public String getDsPiso() {
        return dsPiso;
    }

    public void setDsPiso(String dsPiso) {
        this.dsPiso = dsPiso;
    }

    public Boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Evento getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Evento idEvento) {
        this.idEvento = idEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApresentacao != null ? idApresentacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apresentacao)) {
            return false;
        }
        Apresentacao other = (Apresentacao) object;
        if ((this.idApresentacao == null && other.idApresentacao != null) || (this.idApresentacao != null && !this.idApresentacao.equals(other.idApresentacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.Apresentacao[ idApresentacao=" + idApresentacao + " ]";
    }
    
}
