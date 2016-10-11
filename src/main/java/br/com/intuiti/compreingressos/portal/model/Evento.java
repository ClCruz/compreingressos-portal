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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_evento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e"),
    @NamedQuery(name = "Evento.findByBase", query = "SELECT e FROM Evento e WHERE exists(SELECT ap FROM Apresentacao ap WHERE ap.idEvento = e and ap.dtApresentacao < :data) and e.idBase = :idBase"),
    @NamedQuery(name = "Evento.findByIdEvento", query = "SELECT e FROM Evento e WHERE e.idEvento = :idEvento"),
    @NamedQuery(name = "Evento.findByDsEvento", query = "SELECT e FROM Evento e WHERE e.dsEvento = :dsEvento"),
    @NamedQuery(name = "Evento.findByCodPeca", query = "SELECT e FROM Evento e WHERE e.codPeca = :codPeca"),
    @NamedQuery(name = "Evento.findByInAtivo", query = "SELECT e FROM Evento e WHERE e.inAtivo = :inAtivo"),
    @NamedQuery(name = "Evento.findByInVendeItau", query = "SELECT e FROM Evento e WHERE e.inVendeItau = :inVendeItau"),
    @NamedQuery(name = "Evento.findByInEntregaIngresso", query = "SELECT e FROM Evento e WHERE e.inEntregaIngresso = :inEntregaIngresso"),
    @NamedQuery(name = "Evento.findByInVerNoBordero", query = "SELECT e FROM Evento e WHERE e.inVerNoBordero = :inVerNoBordero"),
    @NamedQuery(name = "Evento.findByInAntiFraude", query = "SELECT e FROM Evento e WHERE e.inAntiFraude = :inAntiFraude"),
    @NamedQuery(name = "Evento.findByQtIngrPorPedido", query = "SELECT e FROM Evento e WHERE e.qtIngrPorPedido = :qtIngrPorPedido"),
    @NamedQuery(name = "Evento.findByInobrigaCPFPos", query = "SELECT e FROM Evento e WHERE e.inobrigaCPFPos = :inobrigaCPFPos"),
    @NamedQuery(name = "Evento.findByInobrigafonePos", query = "SELECT e FROM Evento e WHERE e.inobrigafonePos = :inobrigafonePos"),
    @NamedQuery(name = "Evento.findByInimprimicanhotoPos", query = "SELECT e FROM Evento e WHERE e.inimprimicanhotoPos = :inimprimicanhotoPos"),
    @NamedQuery(name = "Evento.findByInExibeTelaAssinante", query = "SELECT e FROM Evento e WHERE e.inExibeTelaAssinante = :inExibeTelaAssinante")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_evento")
    private Integer idEvento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ds_evento")
    private String dsEvento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CodPeca")
    private short codPeca;
    @Column(name = "in_ativo")
    private Boolean inAtivo;
    @Column(name = "in_vende_itau")
    private Character inVendeItau;
    @Column(name = "in_entrega_ingresso")
    private Character inEntregaIngresso;
    @Column(name = "in_ver_no_bordero")
    private Character inVerNoBordero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_anti_fraude")
    private boolean inAntiFraude;
    @Column(name = "qt_ingr_por_pedido")
    private Short qtIngrPorPedido;
    @Column(name = "in_obriga_CPF_Pos")
    private Character inobrigaCPFPos;
    @Column(name = "in_obriga_fone_Pos")
    private Character inobrigafonePos;
    @Column(name = "in_imprimi_canhoto_Pos")
    private Character inimprimicanhotoPos;
    @Column(name = "in_exibe_tela_assinante")
    private Character inExibeTelaAssinante;
    @JoinColumn(name = "id_base", referencedColumnName = "id_base")
    @ManyToOne(optional = false)
    private Base idBase;
    @JoinColumn(name = "id_local_evento", referencedColumnName = "id_local_evento")
    @ManyToOne
    private LocalEvento idLocalEvento;
    
    public Evento() {
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Evento(Integer idEvento, String dsEvento, short codPeca, boolean inAntiFraude) {
        this.idEvento = idEvento;
        this.dsEvento = dsEvento;
        this.codPeca = codPeca;
        this.inAntiFraude = inAntiFraude;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getDsEvento() {
        return dsEvento;
    }

    public void setDsEvento(String dsEvento) {
        this.dsEvento = dsEvento;
    }

    public short getCodPeca() {
        return codPeca;
    }

    public void setCodPeca(short codPeca) {
        this.codPeca = codPeca;
    }

    public Boolean getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Boolean inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Character getInVendeItau() {
        return inVendeItau;
    }

    public void setInVendeItau(Character inVendeItau) {
        this.inVendeItau = inVendeItau;
    }

    public Character getInEntregaIngresso() {
        return inEntregaIngresso;
    }

    public void setInEntregaIngresso(Character inEntregaIngresso) {
        this.inEntregaIngresso = inEntregaIngresso;
    }

    public Character getInVerNoBordero() {
        return inVerNoBordero;
    }

    public void setInVerNoBordero(Character inVerNoBordero) {
        this.inVerNoBordero = inVerNoBordero;
    }

    public boolean getInAntiFraude() {
        return inAntiFraude;
    }

    public void setInAntiFraude(boolean inAntiFraude) {
        this.inAntiFraude = inAntiFraude;
    }

    public Short getQtIngrPorPedido() {
        return qtIngrPorPedido;
    }

    public void setQtIngrPorPedido(Short qtIngrPorPedido) {
        this.qtIngrPorPedido = qtIngrPorPedido;
    }

    public Character getInobrigaCPFPos() {
        return inobrigaCPFPos;
    }

    public void setInobrigaCPFPos(Character inobrigaCPFPos) {
        this.inobrigaCPFPos = inobrigaCPFPos;
    }

    public Character getInobrigafonePos() {
        return inobrigafonePos;
    }

    public void setInobrigafonePos(Character inobrigafonePos) {
        this.inobrigafonePos = inobrigafonePos;
    }

    public Character getInimprimicanhotoPos() {
        return inimprimicanhotoPos;
    }

    public void setInimprimicanhotoPos(Character inimprimicanhotoPos) {
        this.inimprimicanhotoPos = inimprimicanhotoPos;
    }

    public Character getInExibeTelaAssinante() {
        return inExibeTelaAssinante;
    }

    public void setInExibeTelaAssinante(Character inExibeTelaAssinante) {
        this.inExibeTelaAssinante = inExibeTelaAssinante;
    }

    public Base getIdBase() {
        return idBase;
    }

    public void setIdBase(Base idBase) {
        this.idBase = idBase;
    }

    public LocalEvento getIdLocalEvento() {
        return idLocalEvento;
    }

    public void setIdLocalEvento(LocalEvento idLocalEvento) {
        this.idLocalEvento = idLocalEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return dsEvento;
    }
    
}
