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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_grupo_funcao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoFuncao.findAll", query = "SELECT g FROM GrupoFuncao g"),
    @NamedQuery(name = "GrupoFuncao.findByIdGrupoFuncao", query = "SELECT g FROM GrupoFuncao g WHERE g.idGrupoFuncao = :idGrupoFuncao")})
public class GrupoFuncao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_grupo_funcao")
    private Integer idGrupoFuncao;
    @JoinColumn(name = "id_funcao_sistema", referencedColumnName = "id_funcao_sistema")
    @ManyToOne
    private FuncaoSistema idFuncaoSistema;
    @JoinColumn(name = "id_grupo_acesso", referencedColumnName = "id_grupo_acesso")
    @ManyToOne(optional = false)
    private GrupoAcesso idGrupoAcesso;

    public GrupoFuncao() {
    }

    public GrupoFuncao(Integer idGrupoFuncao) {
        this.idGrupoFuncao = idGrupoFuncao;
    }

    public Integer getIdGrupoFuncao() {
        return idGrupoFuncao;
    }

    public void setIdGrupoFuncao(Integer idGrupoFuncao) {
        this.idGrupoFuncao = idGrupoFuncao;
    }

    public FuncaoSistema getIdFuncaoSistema() {
        return idFuncaoSistema;
    }

    public void setIdFuncaoSistema(FuncaoSistema idFuncaoSistema) {
        this.idFuncaoSistema = idFuncaoSistema;
    }

    public GrupoAcesso getIdGrupoAcesso() {
        return idGrupoAcesso;
    }

    public void setIdGrupoAcesso(GrupoAcesso idGrupoAcesso) {
        this.idGrupoAcesso = idGrupoAcesso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoFuncao != null ? idGrupoFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GrupoFuncao)) {
            return false;
        }
        GrupoFuncao other = (GrupoFuncao) object;
        if ((this.idGrupoFuncao == null && other.idGrupoFuncao != null) || (this.idGrupoFuncao != null && !this.idGrupoFuncao.equals(other.idGrupoFuncao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.GrupoFuncao[ idGrupoFuncao=" + idGrupoFuncao + " ]";
    }
    
}
