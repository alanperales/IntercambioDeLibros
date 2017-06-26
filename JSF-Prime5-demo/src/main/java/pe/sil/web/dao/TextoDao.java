package pe.sil.web.dao;

import java.util.List;

import pe.sil.web.common.SilDao;
import pe.sil.web.entities.Texto;


public interface TextoDao extends SilDao<Texto> {
	List<Texto> obtenerTextoPublico();
	Texto obtenerTextoSolicitado(Integer codigoTexto);
}
