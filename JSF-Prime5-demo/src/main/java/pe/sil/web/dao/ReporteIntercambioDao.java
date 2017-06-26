package pe.sil.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pe.sil.web.common.SilDao;
import pe.sil.web.entities.ReporteIntercambio;

public interface ReporteIntercambioDao extends SilDao<ReporteIntercambio> {
	List<ReporteIntercambio> obtenerResultadosIntercambio(@Param("codigoPersona") Integer codigoPersona,
														  @Param("tipoIntercambio") Integer tipoIntercambio,
														  @Param("textoOfrecido") Integer textoOfrecido,
														  @Param("textoSolicitado") Integer textoSolicitado);

	ReporteIntercambio obtenerMovimientoIntercambio(@Param("codigoMovimiento") Integer codigoMovimiento,
													@Param("usuario") String usuario);
}
