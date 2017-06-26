package pe.sil.web.dao;

import org.apache.ibatis.annotations.Param;

import pe.sil.web.common.SilDao;
import pe.sil.web.entities.Usuario;

public interface UsuarioDao extends SilDao<Usuario> {
	
	Usuario autenticarUsuario(@Param("username") String username,
							  @Param("password") String password);	
}



