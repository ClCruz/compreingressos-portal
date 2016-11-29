package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "mw_tipo_estorno_restricao_tipo_lancamento")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "RestricaoEstorno.findAll", query = "SELECT r FROM RestricaoEstorno r"),
	@NamedQuery(name = "RestricaoEstorno.findByTipoEstorno", query = "SELECT r FROM RestricaoEstorno r WHERE r.restricaoEstornoPK.idTipoEstorno = :idTipoEstorno"),
	@NamedQuery(name = "RestricaoEstorno.findByTipoLancamento", query = "SELECT r FROM RestricaoEstorno r WHERE r.restricaoEstornoPK.idTipoLancamento = :idTipoLancamento")})
public class RestricaoEstorno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected RestricaoEstornoPK restricaoEstornoPK;
	@JoinColumn(name = "id_tipo_estorno", referencedColumnName = "id_tipo_estorno", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private TipoEstorno idTipoEstorno;
	@JoinColumn(name = "id_tipo_lancamento", referencedColumnName = "id_tipo_lancamento", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private TipoLancamento idTipoLancamento;
	
	public RestricaoEstorno(){
	}

	public RestricaoEstorno(RestricaoEstornoPK restricaoEstornoPK){
		this.restricaoEstornoPK = restricaoEstornoPK;
	}
	
	public RestricaoEstorno(int tipoEstorno, int tipoLancamento){
		this.restricaoEstornoPK = new RestricaoEstornoPK(tipoEstorno, tipoLancamento);
	}
	
	public RestricaoEstornoPK getRestricaoEstornoPK() {
		return restricaoEstornoPK;
	}

	public void setRestricaoEstornoPK(RestricaoEstornoPK restricaoEstornoPK) {
		this.restricaoEstornoPK = restricaoEstornoPK;
	}

	public TipoEstorno getIdTipoEstorno() {
		return idTipoEstorno;
	}

	public void setIdTipoEstorno(TipoEstorno idTipoEstorno) {
		this.idTipoEstorno = idTipoEstorno;
	}

	public TipoLancamento getIdTipoLancamento() {
		return idTipoLancamento;
	}

	public void setIdTipoLancamento(TipoLancamento idTipoLancamento) {
		this.idTipoLancamento = idTipoLancamento;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (restricaoEstornoPK != null ? restricaoEstornoPK.hashCode() : 0);
        return hash;
    }
	
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestricaoEstorno)) {
            return false;
        }
        RestricaoEstorno other = (RestricaoEstorno) object;
        if ((this.restricaoEstornoPK == null && other.restricaoEstornoPK != null) || (this.restricaoEstornoPK != null && !this.restricaoEstornoPK.equals(other.restricaoEstornoPK))) {
            return false;
        }
        return true;
    }
	
	@Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.RestricaoEstorno[ restricaoEstornoPK=" + restricaoEstornoPK + " ]";
    }
	
}
