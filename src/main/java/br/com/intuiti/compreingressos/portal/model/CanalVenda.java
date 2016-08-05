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
import javax.persistence.GenerationType;
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
@Table(name = "mw_canal_venda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CanalVenda.findAll", query = "SELECT c FROM CanalVenda c"),
    @NamedQuery(name = "CanalVenda.findByIdCanalVenda", query = "SELECT c FROM CanalVenda c WHERE c.idCanalVenda = :idCanalVenda"),
    @NamedQuery(name = "CanalVenda.findByDsCanalVenda", query = "SELECT c FROM CanalVenda c WHERE c.dsCanalVenda = :dsCanalVenda")})
public class CanalVenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy = IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_canal_venda")
    private Integer idCanalVenda;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ds_canal_venda")
    private String dsCanalVenda;
    @OneToMany(mappedBy = "idCanalVenda")
    private Collection<TipoLancamento> tipoLancamentoCollection;

    public CanalVenda() {
    }

    public CanalVenda(Integer idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }

    public CanalVenda(Integer idCanalVenda, String dsCanalVenda) {
        this.idCanalVenda = idCanalVenda;
        this.dsCanalVenda = dsCanalVenda;
    }

    public Integer getIdCanalVenda() {
        return idCanalVenda;
    }

    public void setIdCanalVenda(Integer idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }

    public String getDsCanalVenda() {
        return dsCanalVenda;
    }

    public void setDsCanalVenda(String dsCanalVenda) {
        this.dsCanalVenda = dsCanalVenda;
    }

    @XmlTransient
    public Collection<TipoLancamento> getTipoLancamentoCollection() {
        return tipoLancamentoCollection;
    }

    public void setTipoLancamentoCollection(Collection<TipoLancamento> tipoLancamentoCollection) {
        this.tipoLancamentoCollection = tipoLancamentoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCanalVenda != null ? idCanalVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CanalVenda)) {
            return false;
        }
        CanalVenda other = (CanalVenda) object;
        if ((this.idCanalVenda == null && other.idCanalVenda != null) || (this.idCanalVenda != null && !this.idCanalVenda.equals(other.idCanalVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.CanalVenda[ idCanalVenda=" + idCanalVenda + " ]";
    }
    
}
