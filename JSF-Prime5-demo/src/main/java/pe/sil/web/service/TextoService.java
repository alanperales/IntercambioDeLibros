package pe.sil.web.service;

import java.util.List;

import pe.sil.web.entities.ParametricaDet;
import pe.sil.web.entities.Texto;

public interface TextoService {
	List<Texto> obtenerTextoPublico();
	Texto obtenerTextoSolicitado(Integer codigoTexto);
}
