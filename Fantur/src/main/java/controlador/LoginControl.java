    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import dao.UsuarioInterface;
import modelo.Rol;
import modelo.Usuario;


/**
 * Controla el login del usuario y el cerrar sesion
 *
 * @author Nico
 */
@ManagedBean(name = "loginControl")
@SessionScoped
public class LoginControl implements Serializable {

    private static final long serialVersionUID = 1L;

    protected static final String LOGIN_PAGE = "login.jsf";

    protected static final String LOGIN_ERROR_PAGE = "loginError.html";

    public static final String JSF_REDIRECT = "?faces-redirect=true&amp;includeViewParams=true";

    public static final String JSF_REDIRECT_ESCAPED = "?faces-redirect=true&includeViewParams=true";

    @NotNull
    private String username;

    @NotNull
    private String password;

    private boolean authenticationError;

    private Rol rol;

    @EJB
    private UsuarioInterface ejbUsuario;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        this.authenticationError = false;
        this.rol = null;
    }

    @PreDestroy
    public void preDestroy() {

    }

    public String login() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            if (isAuthenticated()) {
                logout();
            }

            request.login(this.username, this.password);

            this.usuario = ejbUsuario.findByUsuario(username);
            if (this.usuario != null) {
                request.getSession().setAttribute(username, getUsuario());
            }

            this.authenticationError = false;
            switch (this.usuario.getRol().getRol()) {
                case "Administrador":
                    this.rol = this.usuario.getRol();
                    return "/administradores/gesAlojamientos" + JSF_REDIRECT_ESCAPED;
                case "Usuario":
                    this.rol = this.usuario.getRol();
                    return "/usuarios/" + JSF_REDIRECT_ESCAPED;
                default:
                    request.getSession(false).invalidate();
                    this.authenticationError = true;
                    break;
            }
        } catch (ServletException e) {
            request.getSession(false).invalidate();
            this.authenticationError = true;

        } catch (Exception e) {
            if (request != null) {
                request.getSession(false).invalidate();
            }
            this.authenticationError = true;
        }
        return LOGIN_ERROR_PAGE;
    }

    public boolean isAuthenticated() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        return (externalContext.getUserPrincipal() != null);
    }

    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            if (request != null) {
                //request.getSession(false).invalidate();
                request.logout();
                this.authenticationError = false;
            }
            externalContext.redirect(externalContext.getRequestContextPath() + "/index.html");
        } catch (IOException e) {
         
        } catch (ServletException e) {
            
        }
    }
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAuthenticationError() {
		return authenticationError;
	}

	public void setAuthenticationError(boolean authenticationError) {
		this.authenticationError = authenticationError;
	}
	
	public String getNombreUsuario() {
		if(this.usuario == null)
			return "NO USER";
		return this.usuario.getNombre();
	}
}
