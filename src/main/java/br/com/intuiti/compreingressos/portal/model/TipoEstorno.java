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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author gabrielqueiroz
 */
@Entity
@Table(name = "mw_tipo_estorno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEstorno.findAll", query = "SELECT t FROM TipoEstorno t")
    , @NamedQuery(name = "TipoEstorno.findByIdTipoEstorno", query = "SELECT t FROM TipoEstorno t WHERE t.idTipoEstorno = :idTipoEstorno")
    , @NamedQuery(name = "TipoEstorno.findByDsTipoEstorno", query = "SELECT t FROM TipoEstorno t WHERE t.dsTipoEstorno = :dsTipoEstorno")
    , @NamedQuery(name = "TipoEstorno.findByDsIdTipoEstorno", query = "SELECT t FROM TipoEstorno t WHERE t.dsTipoEstorno = :dsTipoEstorno AND t.idTipoEstorno <> :idTipoEstorno")})
public class TipoEstorno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_estorno", unique = true)
    private Integer idTipoEstorno;
    @Size(max = 30)
    @Column(name = "ds_tipo_estorno")
    private String dsTipoEstorno;

    public TipoEstorno() {
    }

    public TipoEstorno(Integer idTipoEstorno) {
        this.idTipoEstorno = idTipoEstorno;
    }

    public Integer getIdTipoEstorno() {
        return idTipoEstorno;
    }

    public void setIdTipoEstorno(Integer idTipoEstorno) {
        this.idTipoEstorno = idTipoEstorno;
    }

    public String getDsTipoEstorno() {
        return dsTipoEstorno;
    }

    public void setDsTipoEstorno(String dsTipoEstorno) {
        this.dsTipoEstorno = dsTipoEstorno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEstorno != null ? idTipoEstorno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEstorno)) {
            return false;
        }
        TipoEstorno other = (TipoEstorno) object;
        if ((this.idTipoEstorno == null && other.idTipoEstorno != null) || (this.idTipoEstorno != null && !this.idTipoEstorno.equals(other.idTipoEstorno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsTipoEstorno;
    }
    
}
