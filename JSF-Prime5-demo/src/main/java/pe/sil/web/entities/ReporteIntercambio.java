package pe.sil.web.entities;

import pe.sil.web.common.SilEntidad;

public class ReporteIntercambio extends SilEntidad{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String tipoIntercambio;
	
	private String 	intercambioDesc;
	
	public String getIntercambioDesc() {
		return intercambioDesc;
	}

	public void setIntercambioDesc(String intercambioDesc) {
		this.intercambioDesc = intercambioDesc;
	}

	private String idSolicitados;
	private String idOfertados;
	private String PE_CodigoPersona;
	private String NombrePersona;
	private String Email;
	private String idSolicitado;
	private String TituloS;
	private String idOfertado;
	private String TituloO;
	private String latitud;
	private String longitud;
	private String distancia;
	private String US_usuario;
	
	public ReporteIntercambio(){}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTipoIntercambio() {
		return tipoIntercambio;
	}


	public void setTipoIntercambio(String tipoIntercambio) {
		this.tipoIntercambio = tipoIntercambio;
	}


	public String getIdSolicitados() {
		return idSolicitados;
	}


	public void setIdSolicitados(String idSolicitados) {
		this.idSolicitados = idSolicitados;
	}


	public String getIdOfertados() {
		return idOfertados;
	}


	public void setIdOfertados(String idOfertados) {
		this.idOfertados = idOfertados;
	}


	public String getPE_CodigoPersona() {
		return PE_CodigoPersona;
	}


	public void setPE_CodigoPersona(String pE_CodigoPersona) {
		PE_CodigoPersona = pE_CodigoPersona;
	}


	public String getNombrePersona() {
		return NombrePersona;
	}


	public void setNombrePersona(String nombrePersona) {
		NombrePersona = nombrePersona;
	}


	public String getIdSolicitado() {
		return idSolicitado;
	}


	public void setIdSolicitado(String idSolicitado) {
		this.idSolicitado = idSolicitado;
	}


	public String getTituloS() {
		return TituloS;
	}


	public void setTituloS(String tituloS) {
		TituloS = tituloS;
	}


	public String getIdOfertado() {
		return idOfertado;
	}


	public void setIdOfertado(String idOfertado) {
		this.idOfertado = idOfertado;
	}


	public String getTituloO() {
		return TituloO;
	}


	public void setTituloO(String tituloO) {
		TituloO = tituloO;
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

	public String getDistancia() {
		return distancia;
	}


	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getUS_usuario() {
		return US_usuario;
	}

	public void setUS_usuario(String uS_usuario) {
		US_usuario = uS_usuario;
	}
}
