package pe.sil.web.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.menu.MenuModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.sil.web.entities.Intercambio;

@Component
@Scope("session")
@ManagedBean(name="loginModel")
public class LoginModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer idUsuario;
	private String usuario;	
	private String password;
	private String nombres;
	private String apellidos;	
	private MenuModel menu;
	private String ubigeo;
	private String emailPersonal;

	private String latitud;
	private String longitud;
	
	private List<Intercambio> listaSolicitados;
	private List<Intercambio> listaOfertados;
	
	public List<Intercambio> getListaSolicitados() {
		return listaSolicitados;
	}
	public void setListaSolicitados(List<Intercambio> listaSolicitados) {
		this.listaSolicitados = listaSolicitados;
	}
	public List<Intercambio> getListaOfertados() {
		return listaOfertados;
	}
	public void setListaOfertados(List<Intercambio> listaOfertados) {
		this.listaOfertados = listaOfertados;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public MenuModel getMenu() {
		return menu;
	}
	public void setMenu(MenuModel menu) {
		this.menu = menu;
	}
	public String getUbigeo() {
		return ubigeo;
	}
	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}

	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}