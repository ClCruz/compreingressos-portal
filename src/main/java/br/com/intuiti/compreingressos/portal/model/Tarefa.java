package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author edicarlosbarbosa
 */
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long processInstanceId;
    private String name;
    private String nomeEstabelecimento;
    private String numeroAgencia;
    private String tipoServico;
    private String estado;
    private String status;
    private String situacao;
    private Date dataProximaAcao;
    private Date dataCriacao;
    private String actualOwner;
    private String deploymentId;
    private String municipio;
    private ContratoCliente idContrato;

    public Tarefa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(long processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public Date getDataProximaAcao() {
        return dataProximaAcao;
    }

    public void setDataProximaAcao(Date dataProximaAcao) {
        this.dataProximaAcao = dataProximaAcao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getActualOwner() {
        return actualOwner;
    }

    public void setActualOwner(String actualOwner) {
        this.actualOwner = actualOwner;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

	public ContratoCliente getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(ContratoCliente idContrato) {
		this.idContrato = idContrato;
	}
    
    

}
