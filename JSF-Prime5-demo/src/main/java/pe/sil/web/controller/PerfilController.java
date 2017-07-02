package pe.sil.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.sil.web.entities.Usuario;
import pe.sil.web.model.LoginModel;
import pe.sil.web.service.AutenticacionService;
import pe.sil.web.service.IntercambioService;
//import org.apache.log4j.Logger;

@Controller
@Scope("request")
public class PerfilController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(PerfilController.class);

	@Autowired
	AutenticacionService autenticacionService;

	@Autowired
	IntercambioService intercambioService;

	LoginModel usuarioModel;

	public void init() throws IOException, ServletException {
		FacesContext context = FacesContext.getCurrentInstance();

		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		log.info("init : usuario " + request.getParameter("u"));

		boolean parametrosCompoletos = request.getParameter("u") != null;
		boolean usuarioEncontrado = context.getExternalContext().getSessionMap().get("usuarioModel") != null;

		if (parametrosCompoletos) {
			log.info("parametros recibidos");
			// Preparando datos
			String username = request.getParameter("u");
			// Recuperar datos del usuario. Luego, redireccionar a una página sin los parámetros
			boolean existeTexto = cargarUsuario(username);
			if (existeTexto) {
				context.getExternalContext().getSessionMap().put("esRetornoU", true);
				context.getExternalContext().redirect("perfil.xhtml");
				return;
			}
		}

		if (!usuarioEncontrado) {
			limpiarSesion();
			context.getExternalContext().redirect("principal.xhtml");
			return;
		}
		// Nada hacer y permitir que continue el cualquier otro caso
	}

	private boolean cargarUsuario (String username) {
		log.info("cargarUsuario : usuario " + username);

		try{
			showUsuarioForm(username);
			if (usuarioModel != null) {
				return true;
			}
		} catch(Exception ex){
		}
		return false;
	}

	public void showUsuarioForm (String username) {
		log.info("showUsuarioForm : usuario " + username);

		try{
			Usuario usuarioTmp = new Usuario();
			usuarioTmp.setUS_usuario(username);
			Usuario usuario = autenticacionService.consultarUsuario(usuarioTmp);
			System.out.println("Usuario encontrado?: " + usuario);
			if (usuario != null) {
				usuarioModel = new LoginModel();
				usuarioModel.setIdUsuario(usuario.getPE_CodigoPersona());
				usuarioModel.setNombres(usuario.getPE_Nombre());
				usuarioModel.setApellidos(usuario.getPE_ApellidoPaterno() + " " + usuario.getPE_ApellidoMaterno());
				usuarioModel.setUbigeo(usuario.getUbigeo());
				usuarioModel.setEmailPersonal(usuario.getPE_EmailPersonal());
				usuarioModel.setLatitud(usuario.getUG_Latitud());
				usuarioModel.setLongitud(usuario.getUG_Longitud());
				usuarioModel.setUsuario(usuario.getUS_usuario());
				obtenerMisSolicitudes();
				obtenerMisOfertas();
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().getSessionMap().put("usuarioModel", usuarioModel);
			}
		} catch(Exception ex){
		}
	}

	private void obtenerMisSolicitudes() {
		Integer id = usuarioModel.getIdUsuario();
		log.info("id :" + id);
		usuarioModel.setListaSolicitados(intercambioService.obtenerSolicitudesPorSolicitante(id));
	}

	private void obtenerMisOfertas() {
		Integer id = usuarioModel.getIdUsuario();
		log.info("id :" + id);
		usuarioModel.setListaOfertados(intercambioService.obtenerOfertasPorOfertante(id));
	}

	public String cancelarConsulta() {
		log.info("cancelarConsulta: ");
		limpiarSesion();
		return "intercambio1.xhtml";
	}

	private void limpiarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioModel");
		context.getExternalContext().getSessionMap().remove("esRetornoU");
	}
}