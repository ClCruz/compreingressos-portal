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
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
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
@Table(name = "mw_banco")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b"),
    @NamedQuery(name = "Banco.findByIdBanco", query = "SELECT b FROM Banco b WHERE b.idBanco = :idBanco"),
    @NamedQuery(name = "Banco.findByNmBanco", query = "SELECT b FROM Banco b WHERE b.nmBanco = :nmBanco"),
    @NamedQuery(name = "Banco.findByCdBanco", query = "SELECT b FROM Banco b WHERE b.cdBanco = :cdBanco"),
    @NamedQuery(name = "Banco.findByInAtivo", query = "SELECT b FROM Banco b WHERE b.inAtivo = :inAtivo")})
public class Banco implements Serializable {

    @OneToMany(mappedBy = "idBanco")
    private Collection<Contratante> contratanteCollection;

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_banco")
    private Integer idBanco;
    @Size(max = 50)
    @Column(name = "nm_banco")
    private String nmBanco;
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "cd_banco")
    private String cdBanco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private boolean inAtivo = true;

    public Banco() {
    }

    public Banco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Banco(Integer idBanco, boolean inAtivo) {
        this.idBanco = idBanco;
        this.inAtivo = inAtivo;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public String getNmBanco() {
        return nmBanco;
    }

    public void setNmBanco(String nmBanco) {
        this.nmBanco = nmBanco;
    }

    public String getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(String cdBanco) {
        this.cdBanco = cdBanco;
    }

    public boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBanco != null ? idBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idBanco == null && other.idBanco != null) || (this.idBanco != null && !this.idBanco.equals(other.idBanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nmBanco;
    }

    @XmlTransient
    public Collection<Contratante> getContratanteCollection() {
        return contratanteCollection;
    }

    public void setContratanteCollection(Collection<Contratante> contratanteCollection) {
        this.contratanteCollection = contratanteCollection;
    }
    
}
