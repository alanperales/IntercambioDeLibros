package pe.sil.web.dao;

import java.util.List;

import pe.sil.web.common.SilDao;
import pe.sil.web.entities.OpcionesMenu;

public interface OpcionesMenuDao  extends SilDao<OpcionesMenu> {
	List<OpcionesMenu> obtenerOpcionesMenu();	
}

