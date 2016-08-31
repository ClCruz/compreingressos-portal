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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_genero_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneroEvento.findAll", query = "SELECT g FROM GeneroEvento g"),
    @NamedQuery(name = "GeneroEvento.findByIdGeneroEvento", query = "SELECT g FROM GeneroEvento g WHERE g.idGeneroEvento = :idGeneroEvento"),
    @NamedQuery(name = "GeneroEvento.findByDsGeneroEvento", query = "SELECT g FROM GeneroEvento g WHERE g.dsGeneroEvento = :dsGeneroEvento"),
    @NamedQuery(name = "GeneroEvento.findByInAtivo", query = "SELECT g FROM GeneroEvento g WHERE g.inAtivo = :inAtivo")})
public class GeneroEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_genero_evento")
    private Integer idGeneroEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50, message = "O campo Descrição deve ter entre 1 e 50 caracteres")
    @Column(name = "ds_genero_evento")
    private String dsGeneroEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo;
    @JoinColumn(name = "id_segmento_evento", referencedColumnName = "id_segmento_evento")
    @ManyToOne(optional = false)
    private SegmentoEvento idSegmentoEvento;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    public GeneroEvento() {
    }

    public GeneroEvento(Integer idGeneroEvento) {
        this.idGeneroEvento = idGeneroEvento;
    }

    public GeneroEvento(Integer idGeneroEvento, String dsGeneroEvento, boolean inAtivo) {
        this.idGeneroEvento = idGeneroEvento;
        this.dsGeneroEvento = dsGeneroEvento;
        this.inAtivo = inAtivo;
    }

    public Integer getIdGeneroEvento() {
        return idGeneroEvento;
    }

    public void setIdGeneroEvento(Integer idGeneroEvento) {
        this.idGeneroEvento = idGeneroEvento;
    }

    public String getDsGeneroEvento() {
        return dsGeneroEvento;
    }

    public void setDsGeneroEvento(String dsGeneroEvento) {
        this.dsGeneroEvento = dsGeneroEvento;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public SegmentoEvento getIdSegmentoEvento() {
        return idSegmentoEvento;
    }

    public void setIdSegmentoEvento(SegmentoEvento idSegmentoEvento) {
        this.idSegmentoEvento = idSegmentoEvento;
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
        hash += (idGeneroEvento != null ? idGeneroEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GeneroEvento)) {
            return false;
        }
        GeneroEvento other = (GeneroEvento) object;
        if ((this.idGeneroEvento == null && other.idGeneroEvento != null) || (this.idGeneroEvento != null && !this.idGeneroEvento.equals(other.idGeneroEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.GeneroEvento[ idGeneroEvento=" + idGeneroEvento + " ]";
    }
    
}
