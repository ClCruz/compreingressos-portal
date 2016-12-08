package br.com.intuiti.compreingressos.portal.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Queiroz
 */
@Entity
@Table(name = "mw_usuario")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.dsNome"),
		@NamedQuery(name = "Usuario.findLazy", query = "SELECT u FROM Usuario u"),
		@NamedQuery(name = "Usuario.findAsc", query = "SELECT u FROM Usuario u ORDER BY u.dsNome"),
		@NamedQuery(name = "Usuario.findAtivo", query = "SELECT u FROM Usuario u WHERE u.inAtivo = :inAtivo ORDER BY u.dsNome"),
		@NamedQuery(name = "Usuario.findAllRow", query = "SELECT COUNT(u) FROM Usuario u"),
		@NamedQuery(name = "Usuario.findCdLogin", query = "SELECT u FROM Usuario u WHERE u.cdLogin = :cdLogin"),
		@NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
		@NamedQuery(name = "Usuario.findByCdLogin", query = "SELECT u FROM Usuario u WHERE u.cdLogin = :cdLogin"),
		@NamedQuery(name = "Usuario.findByDsNome", query = "SELECT u FROM Usuario u WHERE u.dsNome = :dsNome"),
		@NamedQuery(name = "Usuario.findByCdPww", query = "SELECT u FROM Usuario u WHERE u.cdPww = :cdPww"),
		@NamedQuery(name = "Usuario.findByDsEmail", query = "SELECT u FROM Usuario u WHERE u.dsEmail = :dsEmail"),
		@NamedQuery(name = "Usuario.findByInAtivo", query = "SELECT u FROM Usuario u WHERE u.inAtivo = :inAtivo"),
		@NamedQuery(name = "Usuario.findByInAdmin", query = "SELECT u FROM Usuario u WHERE u.inAdmin = :inAdmin"),
		@NamedQuery(name = "Usuario.findByInTelemarketing", query = "SELECT u FROM Usuario u WHERE u.inTelemarketing = :inTelemarketing"),
		@NamedQuery(name = "Usuario.findByInPdv", query = "SELECT u FROM Usuario u WHERE u.inPdv = :inPdv"),
		@NamedQuery(name = "Usuario.findByInPos", query = "SELECT u FROM Usuario u WHERE u.inPos = :inPos") })
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 10)
	@Column(name = "cd_login", unique = true)
	private String cdLogin;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "ds_nome")
	private String dsNome;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 32)
	@Column(name = "cd_pww")
	private String cdPww;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "ds_email", unique = true)
	private String dsEmail;
	@Basic(optional = false)
	@NotNull
	@Column(name = "in_ativo")
	private int inAtivo;
	@Basic(optional = false)
	@NotNull
	@Column(name = "in_admin")
	private int inAdmin;
	@Column(name = "in_telemarketing")
	private int inTelemarketing;
	@Column(name = "in_pdv")
	private int inPdv;
	@Basic(optional = false)
	@NotNull
	@Column(name = "in_pos")
	private int inPos;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private Collection<SegmentoEvento> segmentoEventoCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
	private Collection<GeneroEvento> generoEventoCollection;

	public Usuario() {
	}

	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(Integer idUsuario, String cdLogin, String dsNome, String cdPww, String dsEmail, Character inAtivo,
			Character inAdmin, int inPos) {
		this.idUsuario = idUsuario;
		this.cdLogin = cdLogin;
		this.dsNome = dsNome;
		this.cdPww = cdPww;
		this.dsEmail = dsEmail;
		this.inAtivo = inAtivo;
		this.inAdmin = inAdmin;
		this.inPos = inPos;
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

	public String getCdPww() {
		return cdPww;
	}

	public void setCdPww(String cdPww) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(cdPww.getBytes(), 0, cdPww.length());
		this.cdPww = new BigInteger(1, m.digest()).toString(16);
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public int getInAtivo() {
		return inAtivo;
	}

	public void setInAtivo(int inAtivo) {
		this.inAtivo = inAtivo;
	}

	public int getInAdmin() {
		return inAdmin;
	}

	public void setInAdmin(int inAdmin) {
		this.inAdmin = inAdmin;
	}

	public int getInTelemarketing() {
		return inTelemarketing;
	}

	public void setInTelemarketing(int inTelemarketing) {
		this.inTelemarketing = inTelemarketing;
	}

	public int getInPdv() {
		return inPdv;
	}

	public void setInPdv(int inPdv) {
		this.inPdv = inPdv;
	}

	public int getInPos() {
		return inPos;
	}

	public void setInPos(int inPos) {
		this.inPos = inPos;
	}

	@XmlTransient
	public Collection<SegmentoEvento> getSegmentoEventoCollection() {
		return segmentoEventoCollection;
	}

	public void setSegmentoEventoCollection(Collection<SegmentoEvento> segmentoEventoCollection) {
		this.segmentoEventoCollection = segmentoEventoCollection;
	}

	@XmlTransient
	public Collection<GeneroEvento> getGeneroEventoCollection() {
		return generoEventoCollection;
	}

	public void setGeneroEventoCollection(Collection<GeneroEvento> generoEventoCollection) {
		this.generoEventoCollection = generoEventoCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUsuario != null ? idUsuario.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.idUsuario == null && other.idUsuario != null)
				|| (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return dsNome;
	}

}
