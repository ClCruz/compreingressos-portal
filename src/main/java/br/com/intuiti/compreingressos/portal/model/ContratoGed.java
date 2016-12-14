package br.com.intuiti.compreingressos.portal.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "mw_contrato_ged")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ContratoGed.findAll", query = "SELECT c FROM ContratoGed c"),
	@NamedQuery(name = "ContratoGed.findByIdContratoGed", query = "SELECT c FROM ContratoGed c WHERE c.idContratoGed = :idContratoGed"),
	@NamedQuery(name = "ContratoGed.findByIdContratoCliente", query = "SELECT c FROM ContratoGed c WHERE c.idContrato = :idContrato")})

public class ContratoGed implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_contrato_ged")
	private Integer idContratoGed;
	@Basic(optional = false)
	@Size(min = 1, max = 2147483647)
	@Column(name = "ds_arquivo")
	private String dsArquivo;
	@JoinColumn(name = "id_contrato", referencedColumnName = "id_contrato_cliente")
	@ManyToOne
	private ContratoCliente idContrato;
	
	public ContratoGed() {
	}

	public ContratoGed(Integer idContratoGed, String dsArquivo) {
		this.idContratoGed = idContratoGed;
		this.dsArquivo = dsArquivo;
	}
	
	public Integer getIdContratoGed() {
		return idContratoGed;
	}

	public void setIdContratoGed(Integer idContratoGed) {
		this.idContratoGed = idContratoGed;
	}

	public String getDsArquivo() {
		return dsArquivo;
	}

	public void setDsArquivo(String dsArquivo) {
		this.dsArquivo = dsArquivo;
	}
	
	public ContratoCliente getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(ContratoCliente idContrato) {
		this.idContrato = idContrato;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idContratoGed != null ? idContratoGed.hashCode() : 0);
		return hash;
	}
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof ContratoGed)) {
			return false;
		}
		ContratoGed other = (ContratoGed) object;
		if((this.idContratoGed == null && other.idContratoGed != null) || (this.idContratoGed != null && !this.idContratoGed.equals(other.idContratoGed))){
			return false;
		}
		return true;
	}
	
	@Override
	public String toString(){
		return idContratoGed.toString();
	}
	
}
