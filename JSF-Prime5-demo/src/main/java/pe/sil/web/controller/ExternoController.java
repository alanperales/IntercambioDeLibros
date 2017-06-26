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
@Scope("request")
public class ExternoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	IntercambioService intercambioService;

	@Autowired
	TextoService textoService;

	@Autowired
	LoginModel loginModel;

	@Autowired
	ReporteIntercambioModel reporteIntercambioModel;

	@Autowired
	TextoModel textoModel;

	HttpServletRequest request;

	public void init() throws IOException, ServletException {
		FacesContext context = FacesContext.getCurrentInstance();

		request = (HttpServletRequest) context.getExternalContext().getRequest();

		log.info("init : codigoMovimiento " + request.getParameter("s"));
		log.info("init : codigoTexto " + request.getParameter("t"));

		boolean parametrosCompoletos = request.getParameter("s") != null && request.getParameter("t") != null;
		boolean esRetorno = request.getSession().getAttribute("esRetorno") != null && Boolean.parseBoolean(request.getSession().getAttribute("esRetorno").toString());


		if (parametrosCompoletos && !esRetorno) {
			log.info("parametros recibidos");
			// Preparando datos
			String codigoMovimiento = request.getParameter("s");
			String codigoTexto = request.getParameter("t");
			// Recuperar datos del intercambio. Luego, redireccionar a una página sin los parámetros
			boolean existeTexto = cargarConfirmarIntercambio(codigoMovimiento, codigoTexto);
			if (existeTexto) {
				request.getSession().setAttribute("esRetorno", true);
				context.getExternalContext().redirect("intercambio-confirmacion.xhtml");
				return;
			}
		}
		// Si el usuario no tiene acceso a la solicitud de intercambio o esta no corresponde al usuario, mostrar mensaje
		// Si los parámetros no están completos, mostrar mensaje
		if (!esRetorno) {
			reporteIntercambioModel = null;
			textoModel = null;
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usted no tiene acceso a la solicitud de intercambio o ya no está disponible."));
			context.getExternalContext().redirect("mensaje.xhtml");
			return;
		}
		// Nada hacer y permitir que continue el cualquier otro caso
	}

	private boolean cargarConfirmarIntercambio (String codigoMovimiento, String codigoTexto) {
		log.info("showConfirmarIntercambio: movimiento " + codigoMovimiento);
		log.info("showConfirmarIntercambio: texto " + codigoTexto);
		log.info("showConfirmarIntercambio: usuario " + loginModel.getUsuario());

		try{
			ReporteIntercambio rep = intercambioService.obtenerMovimientoIntercambio(Integer.parseInt(codigoMovimiento), loginModel.getUsuario());
			reporteIntercambioModel.setReporteIntercambio(rep);

			Texto texto = textoService.obtenerTextoSolicitado(Integer.parseInt(codigoTexto));
			if(texto !=null){
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
				return true;
			} else {
				return false;
			}
		} catch(Exception ex){
			return false;
		}
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
		// Enviar mensajes de Intercambio: Enviar mensaje a confirmante
		mensaje = formatearMsgConfirmante(loginModel.getUsuario(), rep.getUS_usuario(), rep.getEmail());
		enviarEmail("", mensaje);

		// Enviar mensaje
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje: ", "Usted confirmó el intercambio del texto \"" + textoModel.getTX_Titulo() + "\"."));
		return "mensaje.xhtml";
	}

	public String cancelarIntercambio() {
		log.info("cancelarIntercambio: ");
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia: ", "Usted canceló el intercambio del texto \"" + textoModel.getTX_Titulo() + "\"."));
		return "mensaje.xhtml";
	}

	private void enviarEmail(String correo, String mensaje){
		log.info("enviarEmail: ");
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Mail email = new Mail();
		email.setHostMail("smtp.gmail.com");
		email.setPortMail("587");
		email.setPassMail("UPC2017Fanny");
		email.setUserMail("fanny.salinasf@gmail.com");

		//email.sendMail("From", "", "To","CC", "Title", "message", null, null, true, false);
		log.info("correo " + reporteIntercambioModel.getReporteIntercambio().getEmail());
		log.info("mensaje " +mensaje);
		email.sendMail("fanny.salinasf@gmail.com", "", correo, null, "Notificacion Intercambio de Libros", mensaje, null, null, true, false);
	}

	private String formatearMsgSolicitante(String usuario, String usuarioO, String emailO){
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		String linkPerfilO = "http://" + request.getHeader("Host") + request.getRequestURL() + "/con-perfil?u=" + usuarioO;

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

		String linkPerfilS = "http://" + request.getHeader("Host") + request.getRequestURL() + "/con-perfil?u=" + usuarioS;

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