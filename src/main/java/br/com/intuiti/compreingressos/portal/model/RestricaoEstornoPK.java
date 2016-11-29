package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RestricaoEstornoPK implements Serializable {
	
	@Basic(optional = false)
	@NotNull
	@Column(name = "id_tipo_estorno")
	private int idTipoEstorno;
	@Basic(optional = false)
	@NotNull
	@Column(name = "id_tipo_lancamento")
	private int idTipoLancamento;

	public RestricaoEstornoPK(){
		
	}
	
	public RestricaoEstornoPK(int tipoEstorno, int tipoLancamento){
		this.idTipoEstorno = idTipoEstorno;
		this.idTipoLancamento = idTipoLancamento;
	}

	public int getIdTipoEstorno() {
		return idTipoEstorno;
	}

	public void setIdTipoEstorno(int idTipoEstorno) {
		this.idTipoEstorno = idTipoEstorno;
	}

	public int getIdTipoLancamento() {
		return idTipoLancamento;
	}

	public void setIdTipoLancamento(int idTipoLancamento) {
		this.idTipoLancamento = idTipoLancamento;
	}
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTipoEstorno;
        hash += (int) idTipoLancamento;
        return hash;
    }
	
	 @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof RestricaoEstornoPK)) {
	            return false;
	        }
	        RestricaoEstornoPK other = (RestricaoEstornoPK) object;
	        if (this.idTipoEstorno != other.idTipoEstorno) {
	            return false;
	        }
	        if (this.idTipoLancamento != other.idTipoLancamento) {
	            return false;
	        }
	        return true;
	    }
	 
	 @Override
	    public String toString() {
	        return "br.com.intuiti.compreingressos.model.RestricaoEstornoPK[ tipoEstorno=" + idTipoEstorno + ", tipoLancamento=" + idTipoLancamento+ " ]";
	    }
	
}
