package pe.sil.web.util;

import java.util.*;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

public class Mail {
	final static Logger logger = Logger.getLogger(Mail.class);
	private String hostMail;
	private String portMail;	
	protected String userMail;
	protected String passMail;
	
	
	//getters and setters
	public String getHostMail() {
		return hostMail;
	}

	public void setHostMail(String hostMail) {
		this.hostMail = hostMail;
	}

	public String getPortMail() {
		return portMail;
	}

	public void setPortMail(String portMail) {
		this.portMail = portMail;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getPassMail() {
		return passMail;
	}

	public void setPassMail(String passMail) {
		this.passMail = passMail;
	}

	public boolean sendMail(String mailRemitente, 
							String nameRemitente,
							String destinatario,
							String destinatarioCC, 
							String asunto, 
							String mensaje, 
							//String pathAdjunto, 
							//String nameAdjunto,
							List<byte[]> archivosAdjuntos,
							List<String> namesAdjuntos,
							boolean tipoEnvio,
							boolean envioAdjunto) {

		//definir variable de envio
		boolean indSend = false;
		
		try { 
			//configurar propiedades
			Properties props = new Properties();
			props.put("mail.smtp.host", this.hostMail);
			props.put("mail.smtp.port", this.portMail);
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.connectiontimeout", "2000");			
			//definir session
			Session session;
			
			//verificar tipo de envio
			if(tipoEnvio == true){ 				
				//agregar propiedades			
				props.put("mail.smtp.user", this.userMail);
				props.put("mail.smtp.auth", "true"); //autentificacion
				props.put("mail.smtp.socketFactory.port", this.portMail); 
				//props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.socketFactory.fallback", "false");
				//definir objeto security
				//SecurityManager security = System.getSecurityManager();				
				//autentificar correo
				Authenticator auth = new autentificadorSMTP();				
				//establecer session
				session = Session.getInstance(props, auth);			
				//session.setDebug(true);			
			}else{				
				//agregar propiedades
				props.put("mail.smtp.auth", "false");
				
				//establecer session
				session = Session.getInstance(props);				
			} 			     
	 
			//definir texto de correo
			BodyPart texto = new MimeBodyPart();
			texto.setContent(mensaje, "text/html; charset=iso-8859-1");
			
			//unir texto y adjunto
			MimeMultipart multiParte = new MimeMultipart();			
			multiParte.addBodyPart(texto);
			
			BodyPart adjunto = null;
			if(envioAdjunto){
				
				for(int x = 0; x < namesAdjuntos.size() ; x++){
					//definir adjunto de correo
					adjunto = new MimeBodyPart();
					ByteArrayDataSource byt = new ByteArrayDataSource(archivosAdjuntos.get(x), "application/octet-stream");
					//adjunto.setDataHandler(new DataHandler(new FileDataSource(pathAdjunto)));
					adjunto.setDataHandler(new DataHandler(byt));
					adjunto.setFileName(namesAdjuntos.get(x));
					multiParte.addBodyPart(adjunto);
				}				
			}			
			
			//definir mensaje de correo
			MimeMessage msg = new MimeMessage(session);
			//msg.setText(this.mensaje); //mensaje en formato texto
			//msg.setText(mensaje, "ISO-8859-1", "html"); //mensaje en formato HTML
			
			msg.setContent(multiParte);
			msg.setSubject(asunto);
			msg.setFrom(new InternetAddress(mailRemitente, nameRemitente));
			
			//definir destinatarios
			msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			if(destinatarioCC != null){ 
				msg.addRecipients(Message.RecipientType.CC,InternetAddress.parse(destinatarioCC));
			}
			
			//enviar correo			
			 Transport.send(msg);			  
			//asignar true si envio es satisfactorio
			 indSend = true;			
			//mostrar mensaje satisfactorio
			logger.info("SUCCESS: Envio satisfactorio...");
		} catch (Exception e) {			
			//asignar false si envio ha fallado
			indSend = false;			
			//imprimir error
			logger.info("ERROR: ENVIO " + e.toString() );			
		}		
		//retornar variable de envio
		return indSend;
	}
	
	private class autentificadorSMTP extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			//autentificar servidor de correo
			return new PasswordAuthentication(userMail, passMail); 
		}
	}
}
