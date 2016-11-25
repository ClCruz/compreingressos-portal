package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Table(name = "mw_tipo_estorno_restricao_tipo_lancamento")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "RestricaoEstorno.findAll", query = "SELECT r FROM RestricaoEstorno r")})
public class RestricaoEstorno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JoinColumn(name = "id_tipo_estorno", referencedColumnName = "id_tipo_estorno")
	@ManyToOne(optional = false)
	private TipoEstorno idTipoEstorno;
	@JoinColumn(name = "id_tipo_lancamento", referencedColumnName = "id_tipo_lancamento")
	@ManyToOne(optional = false)
	private TipoLancamento idTipoLancamento;
	
	public RestricaoEstorno(){
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
	
	
	
}
