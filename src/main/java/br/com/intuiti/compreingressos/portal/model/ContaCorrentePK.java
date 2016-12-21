/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author gabrielqueiroz
 */
@Embeddable
public class ContaCorrentePK implements Serializable {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @NotNull
    @Column(name = "id_transacao")
    private int idTransacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_contrato_cliente")
    private int idContratoCliente;

    public ContaCorrentePK() {
    }

    public ContaCorrentePK(int idTransacao, int idContratoCliente) {
        this.idTransacao = idTransacao;
        this.idContratoCliente = idContratoCliente;
    }

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdContratoCliente() {
        return idContratoCliente;
    }

    public void setIdContratoCliente(int idContratoCliente) {
        this.idContratoCliente = idContratoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTransacao;
        hash += (int) idContratoCliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContaCorrentePK)) {
            return false;
        }
        ContaCorrentePK other = (ContaCorrentePK) object;
        if (this.idTransacao != other.idTransacao) {
            return false;
        }
        if (this.idContratoCliente != other.idContratoCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.ContaCorrentePK[ idTransacao=" + idTransacao + ", idContratoCliente=" + idContratoCliente + " ]";
    }
    
}
