package pe.sil.web.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import pe.sil.web.entities.Intercambio;
import pe.sil.web.entities.ParametricaDet;
import pe.sil.web.entities.Texto;

@Component
@Scope("session")
public class IntercambioModel implements Serializable{	
	private static final long serialVersionUID = 1L;	
	List<Texto> listaTextos;
	List<ParametricaDet> listaTipoIntercambio;
	List<Intercambio> listaSolicitados;
	List<Intercambio> listaOfertados;	
}
