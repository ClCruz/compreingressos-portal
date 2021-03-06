package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "mw_tipo_transacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTransacao.findAll", query = "SELECT t FROM TipoTransacao t ORDER BY t.dsTipoTransacao"),
    @NamedQuery(name = "TipoTransacao.findByIdTipoTransacao", query = "SELECT t FROM TipoTransacao t WHERE t.idTipoTransacao = :idTipoTransacao"),
    @NamedQuery(name = "TipoTransacao.findByDsTipoTransacao", query = "SELECT t FROM TipoTransacao t WHERE t.dsTipoTransacao = :dsTipoTransacao")})
public class TipoTransacao implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoTransacao")
    private Collection<ContaCorrente> contaCorrenteCollection;
	
	 private static final long serialVersionUID = 1L;
	 	@Id
	    @Basic(optional = false)
	    @Column(name = "id_tipo_transacao", columnDefinition = "integer")
	    private Integer idTipoTransacao;
	    @Size(max = 30)
	    @Column(name = "ds_tipo_transacao")
	    private String dsTipoTransacao;

	    public TipoTransacao() {
	    }

	    public TipoTransacao(Integer idTipoTransacao){
	    	this.idTipoTransacao = idTipoTransacao;
	    }
	    
	    public TipoTransacao(Integer idTipoTransacao, String dsTipoTransacao) {
	        this.idTipoTransacao = idTipoTransacao;
	        this.dsTipoTransacao = dsTipoTransacao;
	    }

	    public Integer getIdTipoTransacao() {
	        return idTipoTransacao;
	    }

	    public void setIdTipoTransacao(Integer idTipoTransacao) {
	        this.idTipoTransacao = idTipoTransacao;
	    }

	    public String getDsTipoTransacao() {
	        return dsTipoTransacao;
	    }

	    public void setDsTipoTransacao(String dsTipoTransacao) {
	        this.dsTipoTransacao = dsTipoTransacao;
	    }

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (idTipoTransacao != null ? idTipoTransacao.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        if (!(object instanceof TipoTransacao)) {
	            return false;
	        }
	        TipoTransacao other = (TipoTransacao) object;
	        if ((this.idTipoTransacao == null && other.idTipoTransacao != null) || (this.idTipoTransacao != null && !this.idTipoTransacao.equals(other.idTipoTransacao))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return idTipoTransacao.toString();
	    }

    @XmlTransient
    @JsonIgnore
    public Collection<ContaCorrente> getContaCorrenteCollection() {
        return contaCorrenteCollection;
    }

    public void setContaCorrenteCollection(Collection<ContaCorrente> contaCorrenteCollection) {
        this.contaCorrenteCollection = contaCorrenteCollection;
    }
	    
	}
