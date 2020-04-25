/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.Permissoes;
import entidades.Usuario;
import facade.UsuarioFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class LoginControle implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario = new Usuario();
    
    public Boolean validaMenu(String menu){
        for (Permissoes p : usuario.getPerfil().getPermissoes()) {
            if(menu.equals(p.getNome())){
                return true;
            } else {
            }
        }        
        return false;
    }
    
    public String logar(){
        usuario = usuarioFacade.validaUsuario(usuario);
        if(usuario.getPerfil() != null){
            return "index.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Usuário e/ou senha inválidos!",
                            null));
            return "login.xhtml";
        }
    }
    public String logoff() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().invalidate();
        return "/login?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}