/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author gabrielqueiroz
 */
@Entity
@Table(name = "mw_usuario_itau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioItau.findAll", query = "SELECT u FROM UsuarioItau u")
    , @NamedQuery(name = "UsuarioItau.findByIdUsuario", query = "SELECT u FROM UsuarioItau u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "UsuarioItau.findByCdLogin", query = "SELECT u FROM UsuarioItau u WHERE u.cdLogin = :cdLogin")
    , @NamedQuery(name = "UsuarioItau.findByDsNome", query = "SELECT u FROM UsuarioItau u WHERE u.dsNome = :dsNome")
    , @NamedQuery(name = "UsuarioItau.findByDsEmail", query = "SELECT u FROM UsuarioItau u WHERE u.dsEmail = :dsEmail")
    , @NamedQuery(name = "UsuarioItau.findByInAtivo", query = "SELECT u FROM UsuarioItau u WHERE u.inAtivo = :inAtivo")
    , @NamedQuery(name = "UsuarioItau.findByInAdmin", query = "SELECT u FROM UsuarioItau u WHERE u.inAdmin = :inAdmin")
    , @NamedQuery(name = "UsuarioItau.findByCdPww", query = "SELECT u FROM UsuarioItau u WHERE u.cdPww = :cdPww")
    , @NamedQuery(name = "UsuarioItau.findByCdCpf", query = "SELECT u FROM UsuarioItau u WHERE u.cdCpf = :cdCpf")
    , @NamedQuery(name = "UsuarioItau.findByDsDddCelular", query = "SELECT u FROM UsuarioItau u WHERE u.dsDddCelular = :dsDddCelular")
    , @NamedQuery(name = "UsuarioItau.findByDsCelular", query = "SELECT u FROM UsuarioItau u WHERE u.dsCelular = :dsCelular")})
public class UsuarioItau implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 10)
    @Column(name = "cd_login")
    private String cdLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ds_nome")
    private String dsNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ds_email")
    private String dsEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "in_ativo")
    private Character inAtivo;
    @Column(name = "in_admin")
    private Character inAdmin;
    @Size(max = 32)
    @Column(name = "cd_pww")
    private String cdPww;
    @Size(max = 11)
    @Column(name = "cd_cpf")
    private String cdCpf;
    @Size(max = 2)
    @Column(name = "ds_ddd_celular")
    private String dsDddCelular;
    @Size(max = 15)
    @Column(name = "ds_celular")
    private String dsCelular;
    @ManyToMany(mappedBy = "usuarioItauCollection")
    private Collection<Evento> eventoCollection;
    @OneToMany(mappedBy = "idUsuarioItau")
    private Collection<PedidoVenda> pedidoVendaCollection;

    public UsuarioItau() {
    }

    public UsuarioItau(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UsuarioItau(Integer idUsuario, String dsNome, String dsEmail, Character inAtivo) {
        this.idUsuario = idUsuario;
        this.dsNome = dsNome;
        this.dsEmail = dsEmail;
        this.inAtivo = inAtivo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCdLogin() {
        return cdLogin;
    }

    public void setCdLogin(String cdLogin) {
        this.cdLogin = cdLogin;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public Character getInAtivo() {
        return inAtivo;
    }

    public void setInAtivo(Character inAtivo) {
        this.inAtivo = inAtivo;
    }

    public Character getInAdmin() {
        return inAdmin;
    }

    public void setInAdmin(Character inAdmin) {
        this.inAdmin = inAdmin;
    }

    public String getCdPww() {
        return cdPww;
    }

    public void setCdPww(String cdPww) {
        this.cdPww = cdPww;
    }

    public String getCdCpf() {
        return cdCpf;
    }

    public void setCdCpf(String cdCpf) {
        this.cdCpf = cdCpf;
    }

    public String getDsDddCelular() {
        return dsDddCelular;
    }

    public void setDsDddCelular(String dsDddCelular) {
        this.dsDddCelular = dsDddCelular;
    }

    public String getDsCelular() {
        return dsCelular;
    }

    public void setDsCelular(String dsCelular) {
        this.dsCelular = dsCelular;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<PedidoVenda> getPedidoVendaCollection() {
        return pedidoVendaCollection;
    }

    public void setPedidoVendaCollection(Collection<PedidoVenda> pedidoVendaCollection) {
        this.pedidoVendaCollection = pedidoVendaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioItau)) {
            return false;
        }
        UsuarioItau other = (UsuarioItau) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.intuiti.compreingressos.portal.model.UsuarioItau[ idUsuario=" + idUsuario + " ]";
    }
    
}
