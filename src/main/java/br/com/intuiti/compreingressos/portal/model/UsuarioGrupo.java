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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_usuario_grupo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioGrupo.findAll", query = "SELECT u FROM UsuarioGrupo u"),
    @NamedQuery(name = "UsuarioGrupo.findAllByUsuario", query = "SELECT g FROM Grupo g WHERE g.inAtivo = true"),
    @NamedQuery(name = "UsuarioGrupo.findByIdUsuario", query = "SELECT u FROM UsuarioGrupo u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioGrupo.findByIdUsuarioGrupo", query = "SELECT u FROM UsuarioGrupo u WHERE u.idUsuarioGrupo = :idUsuarioGrupo")})
public class UsuarioGrupo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_grupo")
    private Integer idUsuarioGrupo;
    @JoinColumn(name = "id_grupo", referencedColumnName = "id_grupo")
    @ManyToOne(optional = false)
    private Grupo idGrupo;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public UsuarioGrupo() {
    }

    public UsuarioGrupo(Integer idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public UsuarioGrupo(Grupo grupo, Usuario usuario) {
    	this.idGrupo = grupo;
    	this.idUsuario = usuario;
    }

	public Integer getIdUsuarioGrupo() {
        return idUsuarioGrupo;
    }

    public void setIdUsuarioGrupo(Integer idUsuarioGrupo) {
        this.idUsuarioGrupo = idUsuarioGrupo;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
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
        hash += (idUsuarioGrupo != null ? idUsuarioGrupo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioGrupo)) {
            return false;
        }
        UsuarioGrupo other = (UsuarioGrupo) object;
        if ((this.idUsuarioGrupo == null && other.idUsuarioGrupo != null) || (this.idUsuarioGrupo != null && !this.idUsuarioGrupo.equals(other.idUsuarioGrupo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.UsuarioGrupo[ idUsuarioGrupo=" + idUsuarioGrupo + " ]";
    }
    
}
