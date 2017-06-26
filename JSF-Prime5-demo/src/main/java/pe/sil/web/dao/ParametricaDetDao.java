package pe.sil.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pe.sil.web.common.SilDao;
import pe.sil.web.entities.ParametricaDet;

public interface ParametricaDetDao extends SilDao<ParametricaDet> {
	List<ParametricaDet> listarParametrosPorCab(@Param("codigoParametroCab") Integer codigoParametroCab);	
}
