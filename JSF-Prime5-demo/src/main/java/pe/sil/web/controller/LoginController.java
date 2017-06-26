package pe.sil.web.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean(name="loginController")
@RequestScoped
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);

	ApplicationContext ctx = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());

	HttpServletRequest request;

	// ------------------

	public String cerrarSesion() {
		log.info("Executing cerrarSesion()");
		try {
			ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
			HttpServletRequest httpServletRequest = (HttpServletRequest) ctx.getRequest();
			httpServletRequest.getSession().removeAttribute("username");
			HttpSession session = (HttpSession) ctx.getSession(false);
			session.invalidate();

			System.out.println("ctx.getRequestContextPath() " + ctx.getRequestContextPath());
			ctx.redirect(ctx.getRequestContextPath());
		} catch (IOException ex) {
			log.info(ex.toString());
		}
		return "";
	}
}