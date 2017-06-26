package pe.sil.web.service;

import java.io.Serializable;
import java.util.List;

import pe.sil.web.entities.Intercambio;
import pe.sil.web.entities.ReporteIntercambio;

public interface IntercambioService extends Serializable {
	List<ReporteIntercambio> obtenerResultadosIntercambio(Integer codigoPersona,Integer tipoIntercambio,Integer textoOfrecido,Integer textoSolicitado);
	List<Intercambio> obtenerSolicitudesPorSolicitante(Integer codigoPersona);
	List<Intercambio> obtenerOfertasPorOfertante(Integer codigoPersona);
	void intercambiar(Integer idOfertado,Integer idSolicitado);
	ReporteIntercambio obtenerMovimientoIntercambio(Integer codigoMovimiento, String usuario);
}