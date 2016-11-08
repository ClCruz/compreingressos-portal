/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;

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
@Table(name = "mw_funcao_sistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuncaoSistema.findAll", query = "SELECT f FROM FuncaoSistema f"),
    @NamedQuery(name = "FuncaoSistema.findOrder", query = "SELECT f FROM FuncaoSistema f ORDER BY f.idOrdemExibicao"),
    @NamedQuery(name = "FuncaoSistema.findByIdFuncaoSistema", query = "SELECT f FROM FuncaoSistema f WHERE f.idFuncaoSistema = :idFuncaoSistema"),
    @NamedQuery(name = "FuncaoSistema.findByDsPrograma", query = "SELECT f FROM FuncaoSistema f WHERE f.dsPrograma = :dsPrograma"),
    @NamedQuery(name = "FuncaoSistema.findByDsUrl", query = "SELECT f FROM FuncaoSistema f WHERE f.dsUrl = :dsUrl"),
    @NamedQuery(name = "FuncaoSistema.findByIdOrdemExibicao", query = "SELECT f FROM FuncaoSistema f WHERE f.idOrdemExibicao = :idOrdemExibicao"),
    @NamedQuery(name = "FuncaoSistema.findByIdParent", query = "SELECT f FROM FuncaoSistema f WHERE f.idParent = :idParent"),
    @NamedQuery(name = "FuncaoSistema.findByDsIcone", query = "SELECT f FROM FuncaoSistema f WHERE f.dsIcone = :dsIcone")})
public class FuncaoSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_funcao_sistema")
    private Integer idFuncaoSistema;
    @Size(max = 50)
    @Column(name = "ds_programa")
    private String dsPrograma;
    @Size(max = 200)
    @Column(name = "ds_url")
    private String dsUrl;
    @Column(name = "id_ordem_exibicao")
    private Integer idOrdemExibicao;
    @JoinColumn(name = "id_parent", referencedColumnName = "id_funcao_sistema")
    @ManyToOne
    private FuncaoSistema idParent;
    @Size(max = 30)
    @Column(name = "ds_icone")
    private String dsIcone;
    @OneToMany(mappedBy = "idFuncaoSistema")
    private Collection<GrupoFuncao> grupoFuncaoCollection;

    public FuncaoSistema() {
    }
   
    public FuncaoSistema(Integer idFuncaoSistema) {
        this.idFuncaoSistema = idFuncaoSistema;
    }

    public FuncaoSistema(Integer idFuncaoSistema, String dsPrograma, Integer idOrdemExibicao, FuncaoSistema idParent, String dsIcone) {
        this.idFuncaoSistema = idFuncaoSistema;
        this.dsPrograma = dsPrograma;
        this.idOrdemExibicao = idOrdemExibicao;
        this.idParent = idParent;
        this.dsIcone = dsIcone;
    }

    public FuncaoSistema(Integer idFuncaoSistema, String dsPrograma, String dsUrl, Integer idOrdemExibicao, String dsIcone) {
        this.idFuncaoSistema = idFuncaoSistema;
        this.dsPrograma = dsPrograma;
        this.dsUrl = dsUrl;
        this.idOrdemExibicao = idOrdemExibicao;
        this.dsIcone = dsIcone;
    }
    
    public FuncaoSistema(Integer idFuncaoSistema, String dsPrograma, Integer idOrdemExibicao, String dsIcone) {
        this.idFuncaoSistema = idFuncaoSistema;
        this.dsPrograma = dsPrograma;
        this.idOrdemExibicao = idOrdemExibicao;
        this.dsIcone = dsIcone;
    }

    public Integer getIdFuncaoSistema() {
        return idFuncaoSistema;
    }
    
    public FuncaoSistema(Integer idFuncaoSistema, String dsPrograma, String dsUrl, Integer idOrdemExibicao, FuncaoSistema idParent, String dsIcone){
        this.idFuncaoSistema = idFuncaoSistema;
        this.dsPrograma = dsPrograma;
        this.dsUrl = dsUrl;
        this.idOrdemExibicao = idOrdemExibicao;
        this.idParent = idParent;
        this.dsIcone = dsIcone;
    }

    public void setIdFuncaoSistema(Integer idFuncaoSistema) {
        this.idFuncaoSistema = idFuncaoSistema;
    }

    public String getDsPrograma() {
        return dsPrograma;
    }

    public void setDsPrograma(String dsPrograma) {
        this.dsPrograma = dsPrograma;
    }

    public String getDsUrl() {
        return dsUrl;
    }

    public void setDsUrl(String dsUrl) {
        this.dsUrl = dsUrl;
    }

    public Integer getIdOrdemExibicao() {
        return idOrdemExibicao;
    }

    public void setIdOrdemExibicao(Integer idOrdemExibicao) {
        this.idOrdemExibicao = idOrdemExibicao;
    }

    public FuncaoSistema getIdParent() {
        return idParent;
    }

    public void setIdParent(FuncaoSistema idParent) {
        this.idParent = idParent;
    }

    public String getDsIcone() {
        return dsIcone;
    }

    public void setDsIcone(String dsIcone) {
        this.dsIcone = dsIcone;
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<GrupoFuncao> getGrupoFuncaoCollection() {
        return grupoFuncaoCollection;
    }

    public void setGrupoFuncaoCollection(Collection<GrupoFuncao> grupoFuncaoCollection) {
        this.grupoFuncaoCollection = grupoFuncaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFuncaoSistema != null ? idFuncaoSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof FuncaoSistema)) {
            return false;
        }
        FuncaoSistema other = (FuncaoSistema) object;
        if ((this.idFuncaoSistema == null && other.idFuncaoSistema != null) || (this.idFuncaoSistema != null && !this.idFuncaoSistema.equals(other.idFuncaoSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsPrograma;
    }
    
}
