/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_grupo_acesso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoAcesso.findAll", query = "SELECT g FROM GrupoAcesso g ORDER BY g.dsGrupo"),
    @NamedQuery(name = "GrupoAcesso.findByIdGrupoAcesso", query = "SELECT g FROM GrupoAcesso g WHERE g.idGrupoAcesso = :idGrupoAcesso"),
    @NamedQuery(name = "GrupoAcesso.findByDsGrupo", query = "SELECT g FROM GrupoAcesso g WHERE g.dsGrupo = :dsGrupo"),
    @NamedQuery(name = "GrupoAcesso.findByInAtivo", query = "SELECT g FROM GrupoAcesso g WHERE g.inAtivo = :inAtivo")})
public class GrupoAcesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo_acesso")
    private Integer idGrupoAcesso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ds_grupo")
    private String dsGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupoAcesso")
    private Collection<GrupoFuncao> grupoFuncaoCollection;
    @OneToMany(mappedBy = "idGrupoAcesso")
    private Collection<UsuarioGrupoFuncao> usuarioGrupoFuncaoCollection;

    public GrupoAcesso() {
    }

    public GrupoAcesso(Integer idGrupoAcesso) {
        this.idGrupoAcesso = idGrupoAcesso;
    }

    public GrupoAcesso(Integer idGrupoAcesso, String dsGrupo, boolean inAtivo) {
        this.idGrupoAcesso = idGrupoAcesso;
        this.dsGrupo = dsGrupo;
        this.inAtivo = inAtivo;
    }

    public Integer getIdGrupoAcesso() {
        return idGrupoAcesso;
    }

    public void setIdGrupoAcesso(Integer idGrupoAcesso) {
        this.idGrupoAcesso = idGrupoAcesso;
    }

    public String getDsGrupo() {
        return dsGrupo;
    }

    public void setDsGrupo(String dsGrupo) {
        this.dsGrupo = dsGrupo;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<GrupoFuncao> getGrupoFuncaoCollection() {
        return grupoFuncaoCollection;
    }

    public void setGrupoFuncaoCollection(Collection<GrupoFuncao> grupoFuncaoCollection) {
        this.grupoFuncaoCollection = grupoFuncaoCollection;
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<UsuarioGrupoFuncao> getUsuarioGrupoFuncaoCollection() {
		return usuarioGrupoFuncaoCollection;
	}

	public void setUsuarioGrupoFuncaoCollection(
			Collection<UsuarioGrupoFuncao> usuarioGrupoFuncaoCollection) {
		this.usuarioGrupoFuncaoCollection = usuarioGrupoFuncaoCollection;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoAcesso != null ? idGrupoAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoAcesso)) {
            return false;
        }
        GrupoAcesso other = (GrupoAcesso) object;
        if ((this.idGrupoAcesso == null && other.idGrupoAcesso != null) || (this.idGrupoAcesso != null && !this.idGrupoAcesso.equals(other.idGrupoAcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsGrupo;
    }
    
}
