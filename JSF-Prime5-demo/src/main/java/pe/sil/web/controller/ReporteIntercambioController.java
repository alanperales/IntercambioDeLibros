package pe.sil.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.jsf.FacesContextUtils;

import pe.sil.web.entities.ReporteIntercambio;
import pe.sil.web.entities.Texto;
import pe.sil.web.model.LoginModel;
import pe.sil.web.model.ReporteIntercambioModel;
import pe.sil.web.model.TextoModel;
import pe.sil.web.service.IntercambioService;
import pe.sil.web.service.ParametricaService;
import pe.sil.web.service.TextoService;
import pe.sil.web.util.Mail;


@Controller
@Scope("request")
public class ReporteIntercambioController {

	private static final Logger log = Logger.getLogger(ReporteIntercambioController.class);

	private static final Integer TIPO_INTERCAMBIO = 4; 

	ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());

	@Autowired
	LoginModel loginModel;

	@Autowired
	ReporteIntercambioModel reporteIntercambioModel;

	@Autowired
	TextoModel textoModel;

	@Autowired
	ParametricaService parametricaService;	

	@Autowired
	TextoService textoService;
	
	@Autowired
	IntercambioService intercambioService;

	HttpServletRequest request;

	ReporteIntercambio verIntercambio = new ReporteIntercambio();

	private MapModel mapModel;
		
	public ReporteIntercambioController(){}

	public TextoModel getTextoModel() {
		return textoModel;
	}

	public void setTextoModel(TextoModel textoModel) {
		this.textoModel = textoModel;
	}

	public ReporteIntercambio getVerIntercambio() {
		return verIntercambio;
	}

	public void setVerIntercambio(ReporteIntercambio verIntercambio) {
		this.verIntercambio = verIntercambio;
	}
	
	public MapModel getMapModel() {
		return mapModel;
	}

	public void setMapModel(MapModel mapModel) {
		this.mapModel = mapModel;
	}

	@PostConstruct
	public void init(){
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		// Si el usuario está autenticado, debe ser bandeja de intercambio
		if (loginModel.getIdUsuario()!=null) {
			log.info("init : usuario " + loginModel.getIdUsuario());

			mapModel = new DefaultMapModel();
			//cargar combos
			cargarComboTextoOfrecido();
			cargarComboTipoIntercambio();
			filtrar();
			cargarMapa();
		}
	}
	
	private void cargarComboTextoOfrecido(){
		reporteIntercambioModel.setCboOfrecido(textoService.obtenerTextoPublico());
		reporteIntercambioModel.setCboSolicitado(textoService.obtenerTextoPublico());
	}
	
	private void cargarComboTipoIntercambio(){
		reporteIntercambioModel.setCboTipoIntercambio(parametricaService.listarParametrosPorCab(ReporteIntercambioController.TIPO_INTERCAMBIO));
	}	
	
	public void filtrar(){
		Integer tipoIntercambio = reporteIntercambioModel.getTipoIntercambio()==null?0:reporteIntercambioModel.getTipoIntercambio();
		Integer textoOfrecido = reporteIntercambioModel.getTextosOfrecido()==null?0:reporteIntercambioModel.getTextosOfrecido();
		Integer textoSolicitado = reporteIntercambioModel.getTextosSolicitado()==null?0:reporteIntercambioModel.getTextosSolicitado();
		Integer login = loginModel.getIdUsuario()==null?0:loginModel.getIdUsuario();

		log.info("filtrar button : " + login + " " + tipoIntercambio + " " +textoOfrecido + " " + textoSolicitado);
		reporteIntercambioModel.setListaReporteIntercambio(intercambioService.obtenerResultadosIntercambio(login, tipoIntercambio, textoOfrecido, textoSolicitado));
	}

	public void limpiar(){
		reporteIntercambioModel.setTextosSolicitado(0);
		reporteIntercambioModel.setTextosOfrecido(0);
		reporteIntercambioModel.setTipoIntercambio(0);
	}

	private void cargarMapa() {	
		log.info("cleaning marks : ");
		List<Marker> markers = mapModel.getMarkers();
		if (markers != null && !markers.isEmpty()) {
			log.info("deleted mark : ");
			mapModel.getMarkers().removeAll(markers);
		}

		if(!CollectionUtils.isEmpty(reporteIntercambioModel.getListaReporteIntercambio())){
			for(ReporteIntercambio rep : reporteIntercambioModel.getListaReporteIntercambio()){
				log.info("Bucle print: " + rep.getNombrePersona());
				agregarMarcaMapa(rep.getLatitud(), rep.getLongitud(), rep.getNombrePersona(), "http://maps.google.com/mapfiles/ms/micons/red-dot.png");
			}
			agregarMarcaMapa(loginModel.getLatitud(), loginModel.getLongitud(), "Aquí estoy", "http://maps.google.com/mapfiles/kml/pal3/icon56.png");
		}
	}

	private void agregarMarcaMapa (String latitud, String longitud, String nombre, String color) {
		LatLng coord = new LatLng(new Double(latitud), new Double(longitud));
		Marker marker = new Marker(coord, nombre);
		if (color != null && !"".equals(color))
			marker.setIcon(color);
		mapModel.addOverlay(marker);
	}

	public String getPosicionUsuario() {
		return loginModel.getLatitud() + "," + loginModel.getLongitud();
	}

	public void showVerForm(ReporteIntercambio rep){
		log.info("showVerForm: ");
		log.info("showVerForm: " + rep.getIdOfertado());
		reporteIntercambioModel.setReporteIntercambio(rep);

		try{
			Texto texto = textoService.obtenerTextoSolicitado(Integer.parseInt(rep.getIdOfertado()));
			log.info("showVerForm: texto " + rep.getIdOfertado());
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
				if (rep.getRutaAdjuntos()!=null && !"".equals(rep.getRutaAdjuntos())) {
					textoModel.setRutaAdjuntos(rep.getRutaAdjuntos().split(","));
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void solicitar(){
		log.info("solicitar: " + textoModel.getId());

		// Se envia un correo al usuario que ofreció el texto con información
		// de usuario que solicitó el intercambio
		String mensaje = formatearMsg1(textoModel.getId(), textoModel.getIdOfertados(), reporteIntercambioModel.getReporteIntercambio().getIdSolicitado(), reporteIntercambioModel.getReporteIntercambio().getIdOfertado(), loginModel.getUsuario(), reporteIntercambioModel.getReporteIntercambio().getTituloS(), reporteIntercambioModel.getReporteIntercambio().getTituloO());
		enviarEmail(reporteIntercambioModel.getReporteIntercambio().getEmail(), mensaje);
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

	private String formatearMsg1(String IdSolicitado, String IdOfertados, String IdTextoS, String IdTextoO, String usuarioS, String tituloS, String tituloO){
		request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		String linkFormulario = "http://" + request.getHeader("Host") + request.getContextPath() + "/intercambio-confirmacion.xhtml?s=" + IdSolicitado + "&o=" + IdOfertados + "&ts=" + IdTextoS + "&to=" + IdTextoO + "&us=" + usuarioS;
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
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Te informamos que el usuario \"" + usuarioS + "\" a solicitado realizar un intercambio de textos.</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Este usuario solicita tu libro \"" + tituloO + "\" y el te ofrece el libro que estas buscando \"" + tituloS + "\"</p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Para ver el texto de \"" + usuarioS + "\" y/o aceptar el intercambio del texto, puedes acceder a este link.: <a href=\"" + linkFormulario + "\">" + linkFormulario + "</a></p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Para ver el perfil de \"" + usuarioS + "\" puedes acceder a este link.: <a href=\"" + linkPerfilS + "\">" + linkPerfilS + "</a></p>");
		sb.append("</div>");
		sb.append("<div id=\"titulo\">");
		sb.append("<p style=\"color:black;font-weight: normal;text-decoration: none;\">Agradecemos tu preferencia.</p>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
