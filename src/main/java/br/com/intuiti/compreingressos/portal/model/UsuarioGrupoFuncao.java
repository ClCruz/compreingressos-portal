/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_usuario_grupo_funcao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioGrupoFuncao.findAll", query = "SELECT u FROM UsuarioGrupoFuncao u"),
    @NamedQuery(name = "UsuarioGrupoFuncao.findByIdUsuarioGrupoFuncao", query = "SELECT u FROM UsuarioGrupoFuncao u WHERE u.idUsuarioGrupoFuncao = :idUsuarioGrupoFuncao")})
public class UsuarioGrupoFuncao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario_grupo_funcao")
    private Integer idUsuarioGrupoFuncao;
    @JoinColumn(name = "id_grupo_funcao", referencedColumnName = "id_grupo_funcao")
    @ManyToOne
    private GrupoFuncao idGrupoFuncao;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @Transient
    private GrupoFuncao gfId;

    public UsuarioGrupoFuncao() {
    }

    public UsuarioGrupoFuncao(Integer idUsuarioGrupoFuncao) {
        this.idUsuarioGrupoFuncao = idUsuarioGrupoFuncao;
    }

    public Integer getIdUsuarioGrupoFuncao() {
        return idUsuarioGrupoFuncao;
    }

    public void setIdUsuarioGrupoFuncao(Integer idUsuarioGrupoFuncao) {
        this.idUsuarioGrupoFuncao = idUsuarioGrupoFuncao;
    }

    public GrupoFuncao getIdGrupoFuncao() {
        return idGrupoFuncao;
    }

    public void setIdGrupoFuncao(GrupoFuncao idGrupoFuncao) {
        this.idGrupoFuncao = idGrupoFuncao;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioGrupoFuncao != null ? idUsuarioGrupoFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioGrupoFuncao)) {
            return false;
        }
        UsuarioGrupoFuncao other = (UsuarioGrupoFuncao) object;
        if ((this.idUsuarioGrupoFuncao == null && other.idUsuarioGrupoFuncao != null) || (this.idUsuarioGrupoFuncao != null && !this.idUsuarioGrupoFuncao.equals(other.idUsuarioGrupoFuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.UsuarioGrupoFuncao[ idUsuarioGrupoFuncao=" + idUsuarioGrupoFuncao + " ]";
    }
    
}
