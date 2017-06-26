package pe.sil.web.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import pe.sil.web.entities.Usuario;
import pe.sil.web.model.LoginModel;
import pe.sil.web.model.PrincipalModel;
import pe.sil.web.model.ReporteIntercambioModel;
//import org.apache.log4j.Logger;
import pe.sil.web.service.IntercambioService;

@Controller
@Scope("session")
public class PrincipalController implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PrincipalController.class);

	@Autowired
	LoginModel loginModel;

	@Autowired
	PrincipalModel principalModel;

	@Autowired
	ReporteIntercambioModel reporteIntercambioModel;

	@Autowired
	IntercambioService intercambioService;

	HttpServletRequest request;

	public void init(){
		log.debug("PrincipalController.init");
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		if (request.getSession().getAttribute("username") == null) {
			// Recuperar el usuario atenticado en AutenticacionServiceImpl
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();
			Usuario usuario = (Usuario) token.getPrincipal();
			// 
			if (usuario != null) {
				request.getSession().setAttribute("username", usuario.getUS_usuario());
				loginModel.setIdUsuario(usuario.getPE_CodigoPersona());
				loginModel.setNombres(usuario.getPE_Nombre());
				loginModel.setApellidos(usuario.getPE_ApellidoPaterno() + " " + usuario.getPE_ApellidoMaterno());
				loginModel.setUbigeo(usuario.getUbigeo());
				loginModel.setEmailPersonal(usuario.getPE_EmailPersonal());
				loginModel.setLatitud(usuario.getUG_Latitud());
				loginModel.setLongitud(usuario.getUG_Longitud());
				loginModel.setUsuario(usuario.getUS_usuario());
				cargarOpcionesMenu();
			}
		}
	}

	public void cargarOfertadosSolicitados() {
		log.info("obtener");
		obtenerMisSolicitudes();
		obtenerMisOfertas();
	}

	private void obtenerMisSolicitudes() {
		Integer id = loginModel.getIdUsuario();
		log.info("id :" + id);
		loginModel.setListaSolicitados(intercambioService.obtenerSolicitudesPorSolicitante(id));
	}

	private void obtenerMisOfertas() {
		Integer id = loginModel.getIdUsuario();
		log.info("id :" + id);
		loginModel.setListaOfertados(intercambioService.obtenerOfertasPorOfertante(id));
	}

	public void cargarOpcionesMenu() {
		MenuModel menu = loginModel.getMenu();
		menu = new DefaultMenuModel();
		DefaultSubMenu submenu = null;
		DefaultMenuItem item = null;

		submenu = new DefaultSubMenu("Comercializacion");
		item = new DefaultMenuItem("Comercializacion");
		item.setUrl("comercializacion.xhtml");
		submenu.addElement(item);
		menu.addElement(submenu);

		submenu = new DefaultSubMenu("Intercambio");
		item = new DefaultMenuItem("Intercambio");
		item.setUrl("intercambio1.xhtml");
		submenu.addElement(item);
		menu.addElement(submenu);
		loginModel.setMenu(menu);
	}
}