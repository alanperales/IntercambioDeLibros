package pe.sil.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.sil.web.dao.OpcionesMenuDao;
import pe.sil.web.dao.UsuarioDao;
import pe.sil.web.entities.OpcionesMenu;
import pe.sil.web.entities.Usuario;
import pe.sil.web.service.AutenticacionService;

@Service
@Transactional
public class AutenticacionServiceImpl implements AutenticacionService {
	private static final Logger log = Logger.getLogger(AutenticacionServiceImpl.class);

	@Autowired
	UsuarioDao usuarioDao;

	@Autowired
	OpcionesMenuDao opcionesMenuDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public Usuario autenticarUsuario(Usuario usuario) {
		return usuarioDao.autenticarUsuario(usuario.getUS_usuario(), usuario.getUS_contrasenia());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, isolation = Isolation.READ_COMMITTED)
	public List<OpcionesMenu> obtenerOpcionesMenu() {		
		return opcionesMenuDao.obtenerOpcionesMenu();
	}

	public Usuario consultarUsuario(Usuario usuario) {
		return usuarioDao.consultarUsuario(usuario.getUS_usuario());
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		log.debug("Prueba paso 2");
		log.debug("autenticando:usuario: " + ((authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName()));
		log.debug("autenticando:password: " + authentication.getCredentials());
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
		if (authentication.getCredentials() != null) {
			String password = authentication.getCredentials().toString();
			Usuario usuario = new Usuario();
			usuario.setUS_usuario(username);
			usuario.setUS_contrasenia(password);
			usuario = autenticarUsuario(usuario);
			if (usuario != null) {
				return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<GrantedAuthority>());
			}
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
