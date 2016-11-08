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

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_segmento_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SegmentoEvento.findAll", query = "SELECT s FROM SegmentoEvento s"),
    @NamedQuery(name = "SegmentoEvento.findByIdSegmentoEvento", query = "SELECT s FROM SegmentoEvento s WHERE s.idSegmentoEvento = :idSegmentoEvento"),
    @NamedQuery(name = "SegmentoEvento.findByDsSegmentoEvento", query = "SELECT s FROM SegmentoEvento s WHERE s.dsSegmentoEvento = :dsSegmentoEvento"),
    @NamedQuery(name = "SegmentoEvento.findByDsSegmentoEventoId", query = "SELECT s FROM SegmentoEvento s WHERE s.dsSegmentoEvento = :dsSegmentoEvento AND s.idSegmentoEvento <> :idSegmentoEvento"),
    @NamedQuery(name = "SegmentoEvento.findByInAtivo", query = "SELECT s FROM SegmentoEvento s WHERE s.inAtivo = :inAtivo")})
public class SegmentoEvento implements Serializable {

    @OneToMany(mappedBy = "idSegmentoEvento")
    private Collection<ContratoCliente> contratoClienteCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_segmento_evento")
    private Integer idSegmentoEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ds_segmento_evento")
    private String dsSegmentoEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo = true;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSegmentoEvento")
    private Collection<GeneroEvento> generoEventoCollection;

    public SegmentoEvento() {
    }

    public SegmentoEvento(Integer idSegmentoEvento) {
        this.idSegmentoEvento = idSegmentoEvento;
    }

    public SegmentoEvento(Integer idSegmentoEvento, String dsSegmentoEvento, boolean inAtivo) {
        this.idSegmentoEvento = idSegmentoEvento;
        this.dsSegmentoEvento = dsSegmentoEvento;
        this.inAtivo = inAtivo;
    }

    public Integer getIdSegmentoEvento() {
        return idSegmentoEvento;
    }

    public void setIdSegmentoEvento(Integer idSegmentoEvento) {
        this.idSegmentoEvento = idSegmentoEvento;
    }

    public String getDsSegmentoEvento() {
        return dsSegmentoEvento;
    }

    public void setDsSegmentoEvento(String dsSegmentoEvento) {
        this.dsSegmentoEvento = dsSegmentoEvento;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<GeneroEvento> getGeneroEventoCollection() {
        return generoEventoCollection;
    }

    public void setGeneroEventoCollection(Collection<GeneroEvento> generoEventoCollection) {
        this.generoEventoCollection = generoEventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSegmentoEvento != null ? idSegmentoEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SegmentoEvento)) {
            return false;
        }
        SegmentoEvento other = (SegmentoEvento) object;
        if ((this.idSegmentoEvento == null && other.idSegmentoEvento != null) || (this.idSegmentoEvento != null && !this.idSegmentoEvento.equals(other.idSegmentoEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsSegmentoEvento;
    }

    @XmlTransient
    public Collection<ContratoCliente> getContratoClienteCollection() {
        return contratoClienteCollection;
    }

    public void setContratoClienteCollection(Collection<ContratoCliente> contratoClienteCollection) {
        this.contratoClienteCollection = contratoClienteCollection;
    }
    
}
