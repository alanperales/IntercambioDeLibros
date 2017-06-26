package pe.sil.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.sil.web.dao.ParametricaDetDao;
import pe.sil.web.entities.ParametricaDet;
import pe.sil.web.service.ParametricaService;

@Service
public class ParametricaServiceImpl implements  ParametricaService{

	@Autowired
	ParametricaDetDao parametricaDetDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public List<ParametricaDet> listarParametrosPorCab(Integer codigoParametroCab) {		
		return parametricaDetDao.listarParametrosPorCab(codigoParametroCab);
	}
}
