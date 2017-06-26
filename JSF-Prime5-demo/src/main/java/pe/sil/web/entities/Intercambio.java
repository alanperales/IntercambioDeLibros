package pe.sil.web.entities;

import pe.sil.web.common.SilEntidad;

public class Intercambio extends SilEntidad{
	private static final long serialVersionUID = 1L;

	private Integer MI_CodigoMovIntercambio;
	private Integer AT_CodigoAutor;
	private String AT_Nombre;
	private String AT_ApellidoPaterno;
	private Integer TX_CodigoTexto;
	private String TX_Titulo;
	private String MI_ISBN;
	private String MI_NumEdicion;
	private String MI_AnioEdicion;
	
	public Intercambio(){}

	public Integer getMI_CodigoMovIntercambio() {
		return MI_CodigoMovIntercambio;
	}

	public void setMI_CodigoMovIntercambio(Integer mI_CodigoMovIntercambio) {
		MI_CodigoMovIntercambio = mI_CodigoMovIntercambio;
	}

	public Integer getAT_CodigoAutor() {
		return AT_CodigoAutor;
	}

	public void setAT_CodigoAutor(Integer aT_CodigoAutor) {
		AT_CodigoAutor = aT_CodigoAutor;
	}

	public String getAT_Nombre() {
		return AT_Nombre;
	}

	public void setAT_Nombre(String aT_Nombre) {
		AT_Nombre = aT_Nombre;
	}

	public String getAT_ApellidoPaterno() {
		return AT_ApellidoPaterno;
	}

	public void setAT_ApellidoPaterno(String aT_ApellidoPaterno) {
		AT_ApellidoPaterno = aT_ApellidoPaterno;
	}

	public Integer getTX_CodigoTexto() {
		return TX_CodigoTexto;
	}

	public void setTX_CodigoTexto(Integer tX_CodigoTexto) {
		TX_CodigoTexto = tX_CodigoTexto;
	}

	public String getTX_Titulo() {
		return TX_Titulo;
	}

	public void setTX_Titulo(String tX_Titulo) {
		TX_Titulo = tX_Titulo;
	}

	public String getMI_ISBN() {
		return MI_ISBN;
	}

	public void setMI_ISBN(String mI_ISBN) {
		MI_ISBN = mI_ISBN;
	}

	public String getMI_NumEdicion() {
		return MI_NumEdicion;
	}

	public void setMI_NumEdicion(String mI_NumEdicion) {
		MI_NumEdicion = mI_NumEdicion;
	}

	public String getMI_AnioEdicion() {
		return MI_AnioEdicion;
	}

	public void setMI_AnioEdicion(String mI_AnioEdicion) {
		MI_AnioEdicion = mI_AnioEdicion;
	}	
}
