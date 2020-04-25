package controle;

import entidades.Funcao;
import facade.FuncaoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FuncaoControle implements Serializable {

    @EJB
    private FuncaoFacade funcaoFacade;
    private Funcao fun;

    public void novo() {
        fun = new Funcao();
    }

    public void salvar() {
        funcaoFacade.save(fun);
    }

    public void excluir(Funcao e) {
        funcaoFacade.remove(e);
    }

    public void editar(Funcao e) {
        fun = e;
    }

    public Funcao getFun() {
        return fun;
    }

    public void setFun(Funcao fun) {
        this.fun = fun;
    }

    public List<Funcao> getLista() {
        return funcaoFacade.findAll();
    }

}