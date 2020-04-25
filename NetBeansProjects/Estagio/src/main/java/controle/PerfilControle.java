package controle;

import entidades.Perfil;
import entidades.Permissoes;
import facade.PerfilFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PerfilControle implements Serializable {

    @EJB
    private PerfilFacade perfilFacade;
    private Perfil per;
    private Permissoes permissoes;

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public void adicionar(){
        permissoes.setPerfil(per);
        per.getPermissoes().add(permissoes);
        permissoes = new Permissoes();
    }
    
    public void novo() {
        per = new Perfil();
        permissoes = new Permissoes();
        per.setPermissoes(new ArrayList<Permissoes>());
    }

    public void salvar() {
        perfilFacade.save(per);
    }

    public void excluir(Perfil e) {
        perfilFacade.remove(e);
    }

    public void editar(Perfil e) {
        per = e;
    }

    public Perfil getPer() {
           if(per == null){
         
               per = new Perfil();
        }
        return per;
    }

    public void setPer(Perfil per) {
        this.per = per;
    }

    public List<Perfil> getLista() {
        return perfilFacade.findAll();
    }

}