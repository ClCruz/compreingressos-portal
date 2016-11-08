package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;

/**
 *
 * @author edicarlos.barbosa
 */

public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;
    private boolean aprovado;
    private Integer idContrato;
    
    public Processo() {
    }
    
    public Processo(int intValue) {
    	this.idContrato = intValue;
    }

	public Integer getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Processo)) {
            return false;
        }
        Processo other = (Processo) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Processo[ procId=" + idContrato + " ]";
    }    

}
