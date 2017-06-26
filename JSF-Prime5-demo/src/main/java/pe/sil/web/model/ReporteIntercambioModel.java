package pe.sil.web.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.sil.web.entities.ParametricaDet;
import pe.sil.web.entities.ReporteIntercambio;
import pe.sil.web.entities.Texto;

@Component
@Scope("session")
public class ReporteIntercambioModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 //private MapModel mapModel;
	
	private Integer textosSolicitado;
	private List<Texto> cboSolicitado;
	
	private Integer textosOfrecido;
	private List<Texto> cboOfrecido;
	
	private Integer tipoIntercambio;
	private List<ParametricaDet> cboTipoIntercambio;
	
	private List<ReporteIntercambio> listaReporteIntercambio;
	
	private ReporteIntercambio reporteIntercambio;
		
	public ReporteIntercambioModel(){}

	public ReporteIntercambio getReporteIntercambio() {
		return reporteIntercambio;
	}
	public void setReporteIntercambio(ReporteIntercambio reporteIntercambio) {
		this.reporteIntercambio = reporteIntercambio;
	}

	public Integer getTextosSolicitado() {
		return textosSolicitado;
	}

	public void setTextosSolicitado(Integer textosSolicitado) {
		this.textosSolicitado = textosSolicitado;
	}

	public List<Texto> getCboSolicitado() {
		return cboSolicitado;
	}

	public void setCboSolicitado(List<Texto> cboSolicitado) {
		this.cboSolicitado = cboSolicitado;
	}

	public Integer getTextosOfrecido() {
		return textosOfrecido;
	}

	public void setTextosOfrecido(Integer textosOfrecido) {
		this.textosOfrecido = textosOfrecido;
	}

	public List<Texto> getCboOfrecido() {
		return cboOfrecido;
	}

	public void setCboOfrecido(List<Texto> cboOfrecido) {
		this.cboOfrecido = cboOfrecido;
	}

	public Integer getTipoIntercambio() {
		return tipoIntercambio;
	}

	public void setTipoIntercambio(Integer tipoIntercambio) {
		this.tipoIntercambio = tipoIntercambio;
	}

	public List<ParametricaDet> getCboTipoIntercambio() {
		return cboTipoIntercambio;
	}

	public void setCboTipoIntercambio(List<ParametricaDet> cboTipoIntercambio) {
		this.cboTipoIntercambio = cboTipoIntercambio;
	}

	public List<ReporteIntercambio> getListaReporteIntercambio() {
		return listaReporteIntercambio;
	}

	public void setListaReporteIntercambio(
			List<ReporteIntercambio> listaReporteIntercambio) {
		this.listaReporteIntercambio = listaReporteIntercambio;
	}	
}
