package pe.sil.web.common;

import java.util.List;

public interface SilDao<BE extends SilEntidad> {
	BE obtenerPorCorrelativo(Long correlativo);
	List<BE> listarTodo();
	Long insertar(BE entidad);
	Long modificar(BE entidad);
	void eliminarPorCorrelativo(Long correlativo);
}


