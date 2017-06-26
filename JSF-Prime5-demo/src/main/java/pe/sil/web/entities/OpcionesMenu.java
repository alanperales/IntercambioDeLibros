package pe.sil.web.entities;

import pe.sil.web.common.SilEntidad;

public class OpcionesMenu  extends SilEntidad{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String descripcion;
	private String ruta;
	private String orden;
	private Integer idpadre;
	
	public OpcionesMenu(){		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String getOrden() {
		return orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public Integer getIdpadre() {
		return idpadre;
	}

	public void setIdpadre(Integer idpadre) {
		this.idpadre = idpadre;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
