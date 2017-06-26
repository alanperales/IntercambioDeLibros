package pe.sil.web.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class PrincipalModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String titulo = "Titulo";	
	private String msgDialogError;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMsgDialogError() {
		return msgDialogError;
	}
	public void setMsgDialogError(String msgDialogError) {
		this.msgDialogError = msgDialogError;
	}
}


