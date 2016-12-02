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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
 * @author gabrielqueiroz
 */
@Entity
@Table(name = "mw_esqueci_senha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EsqueciSenha.findAll", query = "SELECT e FROM EsqueciSenha e")
    , @NamedQuery(name = "EsqueciSenha.findByIdEsqueciSenha", query = "SELECT e FROM EsqueciSenha e WHERE e.idEsqueciSenha = :idEsqueciSenha")
    , @NamedQuery(name = "EsqueciSenha.findByDsEmail", query = "SELECT e FROM EsqueciSenha e WHERE e.dsEmail = :dsEmail")
    , @NamedQuery(name = "EsqueciSenha.findByCdEsqueciSenha", query = "SELECT e FROM EsqueciSenha e WHERE e.cdEsqueciSenha = :cdEsqueciSenha")
    , @NamedQuery(name = "EsqueciSenha.findByInAtivo", query = "SELECT e FROM EsqueciSenha e WHERE e.inAtivo = :inAtivo")
    , @NamedQuery(name = "EsqueciSenha.findByDtEsqueciSenha", query = "SELECT e FROM EsqueciSenha e WHERE e.dtEsqueciSenha = :dtEsqueciSenha")})
public class EsqueciSenha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_esqueci_senha")
    private Integer idEsqueciSenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ds_email")
    private String dsEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cd_esqueci_senha")
    private String cdEsqueciSenha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private Character inAtivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dt_esqueci_senha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtEsqueciSenha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public EsqueciSenha() {
    }

    public EsqueciSenha(Integer idEsqueciSenha) {
        this.idEsqueciSenha = idEsqueciSenha;
    }

    public EsqueciSenha(Integer idEsqueciSenha, String dsEmail, String cdEsqueciSenha, Character inAtivo, Date dtEsqueciSenha) {
        this.idEsqueciSenha = idEsqueciSenha;
        this.dsEmail = dsEmail;
        this.cdEsqueciSenha = cdEsqueciSenha;
        this.inAtivo = inAtivo;
        this.dtEsqueciSenha = dtEsqueciSenha;
    }

    public Integer getIdEsqueciSenha() {
        return idEsqueciSenha;
    }

    public void setIdEsqueciSenha(Integer idEsqueciSenha) {
        this.idEsqueciSenha = idEsqueciSenha;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getCdEsqueciSenha() {
        return cdEsqueciSenha;
    }

    public void setCdEsqueciSenha(String cdEsqueciSenha) {
        this.cdEsqueciSenha = cdEsqueciSenha;
    }

    public Character getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Character inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Date getDtEsqueciSenha() {
        return dtEsqueciSenha;
    }

    public void setDtEsqueciSenha(Date dtEsqueciSenha) {
        this.dtEsqueciSenha = dtEsqueciSenha;
    }
    
    public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idEsqueciSenha != null ? idEsqueciSenha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EsqueciSenha)) {
            return false;
        }
        EsqueciSenha other = (EsqueciSenha) object;
        if ((this.idEsqueciSenha == null && other.idEsqueciSenha != null) || (this.idEsqueciSenha != null && !this.idEsqueciSenha.equals(other.idEsqueciSenha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.EsqueciSenha[ idEsqueciSenha=" + idEsqueciSenha + " ]";
    }
    
}
