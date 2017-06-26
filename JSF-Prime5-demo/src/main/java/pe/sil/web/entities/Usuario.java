package pe.sil.web.entities;

import java.util.Date;

import pe.sil.web.common.SilEntidad;

public class Usuario extends SilEntidad{
	private static final long serialVersionUID = 1L;
	
	private Integer PE_CodigoPersona;
	private Integer PL_CodigoPerfil;
	private String US_usuario;
	private String PE_Nombre;
	private String PE_ApellidoPaterno;
	private String PE_ApellidoMaterno;
	private String US_contrasenia;
	private Integer US_EstadoTrabajdor;
	private Date US_Ultimasesion;
	private String ubigeo;
	private String PE_EmailPersonal;

	private String UG_Latitud;
	private String UG_Longitud;

	public Usuario(){}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}

	public Integer getPE_CodigoPersona() {
		return PE_CodigoPersona;
	}

	public void setPE_CodigoPersona(Integer pE_CodigoPersona) {
		PE_CodigoPersona = pE_CodigoPersona;
	}

	public Integer getPL_CodigoPerfil() {
		return PL_CodigoPerfil;
	}

	public void setPL_CodigoPerfil(Integer pL_CodigoPerfil) {
		PL_CodigoPerfil = pL_CodigoPerfil;
	}

	public String getUS_usuario() {
		return US_usuario;
	}

	public void setUS_usuario(String uS_usuario) {
		US_usuario = uS_usuario;
	}

	public String getPE_Nombre() {
		return PE_Nombre;
	}

	public void setPE_Nombre(String pE_Nombre) {
		PE_Nombre = pE_Nombre;
	}

	public String getPE_ApellidoPaterno() {
		return PE_ApellidoPaterno;
	}

	public void setPE_ApellidoPaterno(String pE_ApellidoPaterno) {
		PE_ApellidoPaterno = pE_ApellidoPaterno;
	}

	public String getPE_ApellidoMaterno() {
		return PE_ApellidoMaterno;
	}

	public void setPE_ApellidoMaterno(String pE_ApellidoMaterno) {
		PE_ApellidoMaterno = pE_ApellidoMaterno;
	}

	public String getUS_contrasenia() {
		return US_contrasenia;
	}

	public void setUS_contrasenia(String uS_contrasenia) {
		US_contrasenia = uS_contrasenia;
	}

	public Integer getUS_EstadoTrabajdor() {
		return US_EstadoTrabajdor;
	}

	public void setUS_EstadoTrabajdor(Integer uS_EstadoTrabajdor) {
		US_EstadoTrabajdor = uS_EstadoTrabajdor;
	}

	public Date getUS_Ultimasesion() {
		return US_Ultimasesion;
	}

	public void setUS_Ultimasesion(Date uS_Ultimasesion) {
		US_Ultimasesion = uS_Ultimasesion;
	}

	public String getPE_EmailPersonal() {
		return PE_EmailPersonal;
	}

	public void setPE_EmailPersonal(String pE_EmailPersonal) {
		PE_EmailPersonal = pE_EmailPersonal;
	}

	public String getUG_Latitud() {
		return UG_Latitud;
	}

	public void setUG_Latitud(String uG_Latitud) {
		UG_Latitud = uG_Latitud;
	}

	public String getUG_Longitud() {
		return UG_Longitud;
	}

	public void setUG_Longitud(String uG_Longitud) {
		UG_Longitud = uG_Longitud;
	}
}
