package pe.sil.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import pe.sil.web.service.AutenticacionService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public SecurityConfig() {
		super();
	}

	@Autowired
	private AutenticacionService autenticacionService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(autenticacionService);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login.xhtml").permitAll()
				.antMatchers("/perfil.xhtml").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/javax.faces.resource/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login.xhtml")
				.loginProcessingUrl("/iniciarSesion")
				.usernameParameter("txtUsuario")
				.passwordParameter("txtClave")
				.defaultSuccessUrl("/principal.xhtml", false)
				.failureUrl("/login.xhtml?error=true")
				.and()
			.logout()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutUrl("/perfil.xhtml")
				.and()
			.httpBasic()
				.and()
			.csrf()
				.disable();
	}
}
