/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;
import entidades.ItensCompra;
import entidades.Produto;
import entidades.Compra;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author marcos-ladeira
 */
@Stateless
public class CompraFacade extends AbstractFacade<Compra> {

    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    @Override
    public void save(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() + it.getQuantidade()));
            em.merge(p);
        }
        super.save(entity); 
    }

    @Override
    public void remove(Compra entity) {
        for(ItensCompra it : entity.getItensCompra()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() - it.getQuantidade()));
            em.merge(p);
        }
        super.remove(entity);
    }

    @Override
    public List<Compra> findAll() {
        List<Compra> listacompras = super.findAll(); 
        for(Compra v : listacompras){
            v.getItensCompra().size();
            v.getContasPagar().size();
        }
        return listacompras;
    }
    
    
    

}
