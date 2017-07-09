package pe.sil.web.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import pe.sil.web.entities.ReporteIntercambio;
import pe.sil.web.entities.Texto;
import pe.sil.web.model.LoginModel;
import pe.sil.web.model.ReporteIntercambioModel;
import pe.sil.web.model.TextoModel;
//import org.apache.log4j.Logger;
import pe.sil.web.service.IntercambioService;
import pe.sil.web.service.TextoService;
import pe.sil.web.util.Mail;

@Controller
@Scope("session")
public class ExternoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(ExternoController.class);

	@Autowired
	TextoService textoService;

	@Autowired
	IntercambioService intercambioService;

	@Autowired
	LoginModel loginModel;

	HttpServletRequest request;

	ReporteIntercambioModel reporteIntercambioModel;

	TextoModel textoModel;

	boolean esRetorno = false;

	public void init() throws IOException, ServletException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);

		request = (HttpServletRequest) context.getExternalContext().getRequest();

		log.info("init : codigoMovSolicitado " + request.getParameter("s"));
		log.info("init : codigoMovOfertado " + request.getParameter("o"));
		log.info("init : codigoTextoS " + request.getParameter("ts"));
		log.info("init : codigoTextoO " + request.getParameter("to"));
		log.info("init : codigoPersonaS " + request.getParameter("us"));
		log.info("init : reporteIntercambioModel " + context.getExternalContext().getSessionMap().get("reporteIntercambioModel"));

		boolean parametrosCompletos = request.getParameter("s") != null && request.getParameter("o") != null && request.getParameter("ts") != null && request.getParameter("to") != null && request.getParameter("us") != null;
		boolean reporteEncontrado = context.getExternalContext().getSessionMap().get("reporteIntercambioModel") != null;

		if (parametrosCompletos) {
			log.info("parametros recibidos");
			// Preparando datos
			String codigoPersonaS = request.getParameter("us");
			int codigoOfertados = Integer.parseInt(request.getParameter("o"));
			// Recuperar datos del intercambio. Luego, redireccionar a una página sin los parámetros
			boolean existeTexto = cargarConfirmarIntercambio(codigoPersonaS, codigoOfertados);
			log.info("Intercambio encontrado : " + existeTexto);
			if (existeTexto) {
				context.getExternalContext().getSessionMap().put("reporteIntercambioModel", reporteIntercambioModel);
				context.getExternalContext().getSessionMap().put("textoModel", textoModel);
				context.getExternalContext().getSessionMap().put("usuarioSolicitante", codigoPersonaS);
				context.getExternalContext().getSessionMap().put("esRetorno", true);
				context.getApplication().getNavigationHandler().handleNavigation(context, null, "/intercambio-confirmacion.xhtml?faces-redirect=true");
				return;
			}
		}

		// Si viene de un retroceso, mostrar mensaje
		// Si el usuario no tiene acceso a la solicitud de intercambio o esta no corresponde al usuario, mostrar mensaje
		// Si los parámetros no están completos, mostrar mensaje
		if (!reporteEncontrado) {
			limpiarSesion();
			context.getExternalContext().redirect("principal.xhtml");
			return;
		}
		// Nada hacer y permitir que continue el cualquier otro caso
	}

	private boolean cargarConfirmarIntercambio (String codigoPersonaS, int codigoOfertados) {
		log.info("cargarConfirmarIntercambio : codigoPersonaS " + codigoPersonaS);
		log.info("cargarConfirmarIntercambio : codigoPersonaO " + loginModel.getUsuario());
		log.info("cargarConfirmarIntercambio : codigoOfertados " + codigoOfertados);

		try{
			reporteIntercambioModel = new ReporteIntercambioModel();
			ReporteIntercambio rep = intercambioService.obtenerMovimientoIntercambio(codigoPersonaS, loginModel.getUsuario(), codigoOfertados);
			reporteIntercambioModel.setReporteIntercambio(rep);

			Texto texto = textoService.obtenerTextoSolicitado(Integer.parseInt(rep.getIdOfertado()));
			if(texto !=null){
				textoModel = new TextoModel();
				textoModel.setAT_CodigoAutor(texto.getAT_CodigoAutor());
				textoModel.setAT_NombreAutor(texto.getAT_NombreAutor());
				textoModel.setED_CodigoEditorial(texto.getED_CodigoEditorial());
				textoModel.setED_Nombre(texto.getED_Nombre());
				textoModel.setTX_AnioEdicion(texto.getTX_AnioEdicion());
				textoModel.setTX_CodigoTexto(texto.getTX_CodigoTexto());
				textoModel.setTX_ISBN(texto.getTX_ISBN());
				textoModel.setTX_NumEdicion(texto.getTX_NumEdicion());
				textoModel.setTX_NumPagina(texto.getTX_NumPagina());
				textoModel.setTX_Observacion(texto.getTX_Observacion());
				textoModel.setTX_Titulo(texto.getTX_Titulo());
				textoModel.setPD_NombreTipPublicacion(texto.getPD_NombreTipPublicacion());
				textoModel.setPD_NombreTipTapa(texto.getPD_NombreTipTapa());
				textoModel.setId(rep.getId());
				textoModel.setIdOfertados(rep.getIdOfertados());
				if (rep.getRutaAdjuntos()!=null && !"".equals(rep.getRutaAdjuntos())) {
					textoModel.setRutaAdjuntos(rep.getRutaAdjuntos().split(","));
				}
				return true;
			} else {
				return false;
			}
		} catch(Exception ex){
		}

		return false;
	}

	public String intercambiar() {
		log.info("intercambiar: " + textoModel.getId());
		log.info("intercambiar: " + "");

		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		Integer idOfertado = null;
		Integer id = null;
		ReporteIntercambio rep = reporteIntercambioModel.getReporteIntercambio();

		try {
			idOfertado = Integer.parseInt(textoModel.getIdOfertados());
		} catch (Exception e) {
		}
		try {
			id=Integer.parseInt(textoModel.getId());
		} catch (Exception e) {
		}

		intercambioService.intercambiar(id ,idOfertado);

		// Enviar mensajes de Intercambio: Enviar mensaje a solicitante
		String mensaje = formatearMsgSolicitante(rep.getUS_usuario(), loginModel.getUsuario(), loginModel.getEmailPersonal());
		enviarEmail(rep.getEmail(), mensaje);

		limpiarSesion();

		// Enviar mensaje
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje: ", "Usted confirmó el intercambio del texto \"" + textoModel.getTX_Titulo() + "\"."));
		return "intercambio1.xhtml";
	}

	public String cancelarIntercambio() {
		log.info("cancelarIntercambio: ");
		limpiarSesion();
		// Enviar mensaje
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia: ", "Usted canceló el intercambio del texto \"" + textoModel.getTX_Titulo() + "\"."));
		return "intercambio1.xhtml";
	}

	private void limpiarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("reporteIntercambioModel");
		context.getExternalContext().getSessionMap().remove("textoModel");
		context.getExternalContext().getSessionMap().remove("usuarioSolicitante");
		context.getExternalContext().getSessionMap().remove("esRetorno");
	}

	private void enviarEmail(String correo, String mensaje){
		log.info("enviarEmail: ");
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Mail email = new Mail();
		email.setHostMail("smtp.gmail.com");
		email.setPortMail("587");
		email.setPassMail("UPC2017Fanny");
		email.setUserMail("fanny.salinasf@gmail.com");

		log.info("correo " + reporteIntercambioModel.getReporteIntercambio().getEmail());
		log.info("mensaje " +mensaje);
		email.sendMail("fanny.salinasf@gmail.com", "", correo, null, "Notificacion Intercambio de Libros", mensaje, null, null, true, false);
	}

	private String formatearMsgSolicitante(String usuario, String usuarioO, String emailO){
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		String linkPerfilO = "http://" + request.getHeader("Host") + request.getContextPath() + "/perfil.xhtml?u=" + usuarioO;

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div  id=\"principal\" style=\"width:100%\">");
		sb.append("<div id=\"titulo\" style=\"width:50%; margin: 0 auto;\">");
		sb.append("<p style=\"color:black;font-weight: bold;text-decoration: underline;\">Tienes una nueva solicitud de intercambio</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">" + usuario + ", Te informamos que el usuario \"" + usuarioO + "\" a confirmado realizar un intercambio de textos</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Para que puedas contactar con " + usuario + ", puedes escribirle al siguiente correo</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\"><a href=\"mailto:" + emailO + "\">" + emailO + "</a></p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Si deseas ver el perfil de \"" + usuarioO + "\" puedes acceder desde este link.: " + linkPerfilO + "</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Agradecemos realizar tu intercambio</p>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}

	private String formatearMsgConfirmante(String usuario, String usuarioS, String emailS){
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		String linkPerfilS = "http://" + request.getHeader("Host") + request.getContextPath() + "/perfil.xhtml?u=" + usuarioS;

		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<head>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div  id=\"principal\" style=\"width:100%\">");
		sb.append("<div id=\"titulo\" style=\"width:50%; margin: 0 auto;\">");
		sb.append("<p style=\"color:black;font-weight: bold;text-decoration: underline;\">Tienes una nueva solicitud de intercambio</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">" + usuario + ", Te informamos que confirmaste el intercambio de textos con el usuario \"" + usuarioS + "\"</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Para que puedas contactar con " + usuarioS + ", puedes escribirle al siguiente correo</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\"><a href=\"mailto:" + emailS + "\">" + emailS + "</a></p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Si deseas ver el perfil de \"" + usuarioS + "\" puedes acceder desde este link.: " + linkPerfilS + "</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Agradecemos realizar tu intercambio</p>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}