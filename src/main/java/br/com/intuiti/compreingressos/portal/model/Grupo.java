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
import javax.persistence.Transient;
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
@Table(name = "mw_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Grupo.findByIdGrupo", query = "SELECT g FROM Grupo g WHERE g.idGrupo = :idGrupo"),
    @NamedQuery(name = "Grupo.findByNmGrupo", query = "SELECT g FROM Grupo g WHERE g.nmGrupo = :nmGrupo"),
    @NamedQuery(name = "Grupo.findByInAtivo", query = "SELECT g FROM Grupo g WHERE g.inAtivo = :inAtivo")})
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupo")
    private Integer idGrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nm_grupo")
    private String nmGrupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo")
    private Collection<UsuarioGrupo> usuarioGrupoCollection;
    @Transient
    private boolean selected;
    @Transient
    private UsuarioGrupo usuarioGrupo;
    
    public Grupo() {
    }

    public Grupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Grupo(Integer idGrupo, String nmGrupo, boolean inAtivo) {
        this.idGrupo = idGrupo;
        this.nmGrupo = nmGrupo;
        this.inAtivo = inAtivo;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }
    
    @XmlTransient
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }        

    @XmlTransient
    public UsuarioGrupo getUsuarioGrupo() {
        return usuarioGrupo;
    }

    public void setUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
        this.usuarioGrupo = usuarioGrupo;
    }        

    @XmlTransient
    @JsonIgnore
    public Collection<UsuarioGrupo> getUsuarioGrupoCollection() {
        return usuarioGrupoCollection;
    }

    public void setUsuarioGrupoCollection(Collection<UsuarioGrupo> usuarioGrupoCollection) {
        this.usuarioGrupoCollection = usuarioGrupoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupo != null ? idGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Grupo)) {
            return false;
        }
        Grupo other = (Grupo) object;
        if ((this.idGrupo == null && other.idGrupo != null) || (this.idGrupo != null && !this.idGrupo.equals(other.idGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.Grupo[ idGrupo=" + idGrupo + " ]";
    }
    
}
