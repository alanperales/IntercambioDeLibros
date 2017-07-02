package pe.sil.web.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;

import pe.sil.web.entities.OpcionesMenu;
import pe.sil.web.entities.Usuario;

public interface AutenticacionService extends AuthenticationProvider {
	Usuario autenticarUsuario(Usuario usuario);

	Usuario consultarUsuario(Usuario usuario);

	List<OpcionesMenu> obtenerOpcionesMenu();
}
