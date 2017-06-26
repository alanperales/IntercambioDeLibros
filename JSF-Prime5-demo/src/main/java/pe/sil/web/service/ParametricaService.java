package pe.sil.web.service;

import java.util.List;

import pe.sil.web.entities.ParametricaDet;

public interface ParametricaService {
	List<ParametricaDet> listarParametrosPorCab(Integer codigoParametroCab);	
}
