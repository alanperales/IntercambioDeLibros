package pe.sil.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.sil.web.dao.TextoDao;
import pe.sil.web.entities.Texto;
import pe.sil.web.service.TextoService;

@Service
public class TextoServiceImpl implements  TextoService{

	@Autowired
	TextoDao textoDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public List<Texto> obtenerTextoPublico() {
		return textoDao.obtenerTextoPublico();
	}

	@Override
	public Texto obtenerTextoSolicitado(Integer codigoTexto) {
		return textoDao.obtenerTextoSolicitado(codigoTexto);
	}
}
