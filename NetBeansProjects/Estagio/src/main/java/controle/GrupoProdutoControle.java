/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import entidades.GrupoProduto;
import facade.GrupoProdutoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author marcos-ladeira
 */
@ManagedBean
@SessionScoped
public class GrupoProdutoControle implements Serializable {

    @EJB
    private GrupoProdutoFacade grupoProdutoFacade;
    private GrupoProduto gp;

    public void novo() {
        gp = new GrupoProduto();
    }

    public void salvar() {
        grupoProdutoFacade.save(gp);
    }

    public void excluir(GrupoProduto gp) {
        grupoProdutoFacade.remove(gp);
    }

    public void editar(GrupoProduto gp) {
        this.gp = gp;
    }

    public GrupoProduto getGp() {
        if(gp == null){
         
               gp = new GrupoProduto();
        }
        return gp;
    }

    public void setGp(GrupoProduto gp) {
        this.gp = gp;
    }

    public List<GrupoProduto> getLista() {
        return grupoProdutoFacade.findAll();
    }

}