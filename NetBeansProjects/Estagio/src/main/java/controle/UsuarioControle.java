/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import converter.ConverterGenerico;
import entidades.Perfil;
import entidades.Usuario;
import facade.PerfilFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class UsuarioControle implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private PerfilFacade perfilFacade;
    private Usuario usuario;    
    private ConverterGenerico converterPerfil;

    public List<Perfil> listaPerfis(){
        return perfilFacade.findAll();
    }
    
    public ConverterGenerico getConverterPerfil() {
        if(converterPerfil == null){
            converterPerfil = new ConverterGenerico(perfilFacade);
        }
        return converterPerfil;
    }

    public void setConverterPerfil(ConverterGenerico converterPerfil) {
        this.converterPerfil = converterPerfil;
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar() {
        if (!usuario.getSenha().equals(usuario.getConfirmasenha())) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "As senhas são diferente!",
                            null));
        } else {
            usuarioFacade.save(usuario);
        }
    }

    public void excluir(Usuario e) {
        usuarioFacade.remove(e);
    }

    public void editar(Usuario e) {
        usuario = e;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getLista() {
        return usuarioFacade.findAll();
    }

}