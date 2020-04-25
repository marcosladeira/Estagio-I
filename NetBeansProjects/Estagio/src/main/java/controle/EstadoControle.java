package controle;

import entidades.Estado;
import facade.EstadoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class EstadoControle implements Serializable {

    @EJB
    private EstadoFacade estadoFacade;
    private Estado est;

    public void novo() {
        est = new Estado();
    }

    public void salvar() {
        estadoFacade.save(est);
    }

    public void excluir(Estado e) {
        estadoFacade.remove(e);
    }

    public void editar(Estado e) {
        est = e;
    }

    public Estado getEst() {
        return est;
    }

    public void setEst(Estado est) {
        this.est = est;
    }

    public List<Estado> getLista() {
        return estadoFacade.findAll();
    }

}