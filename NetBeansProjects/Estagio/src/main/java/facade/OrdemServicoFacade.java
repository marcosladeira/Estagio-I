/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.ItensVenda;
import entidades.OrdemServico;
import entidades.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marcos-ladeira
 */
@Stateless
public class OrdemServicoFacade extends AbstractFacade<OrdemServico> {
    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdemServicoFacade() {
        super(OrdemServico.class);
    }
    
    @Override
    public void save(OrdemServico entity) {
        for(ItensVenda it : entity.getItensVenda()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() - it.getQuantidade()));
            em.merge(p);
        }
        super.save(entity); 
    }
    
    @Override
    public void remove(OrdemServico entity) {
        for(ItensVenda it : entity.getItensVenda()){
            Produto p = it.getProduto();
            p.setEstoque((int) (p.getEstoque() + it.getQuantidade()));
            em.merge(p);
        }
        super.remove(entity);
    }
    
    @Override
    public List<OrdemServico> findAll() {
        List<OrdemServico> listaordemservicos = super.findAll(); 
        for(OrdemServico v : listaordemservicos){
            v.getItensServico().size();
            v.getItensVenda().size();
            v.getContasReceber().size();
        }
        return listaordemservicos;
    }
  
     public List<OrdemServico> listagemAgenda() {
        Query q = em.createNativeQuery(" select os.* from OrdemServico os inner join ItensServico it on it.ordemServico_id = os.id",OrdemServico.class);
        return q.getResultList();
    }

    public void carregaItensServico(OrdemServico os) {
        Query q = em.createQuery(" FROM ItensServico i where i.ordemServico = :os");
        q.setParameter("os", os);
        os.setItensServico(q.getResultList());
    }
    public void carregaItensProduto(OrdemServico os) {
        Query q = em.createQuery(" FROM ItensVenda iv where iv.ordemServico = :os");
        q.setParameter("os", os);
        os.getItensVenda().size();
        os.setItensVenda(q.getResultList());
    }

    public void carregaContaReceberOS(OrdemServico os) {
        Query q = em.createQuery(" FROM ContasReceber cr where cr.ordemServico = :os");
        q.setParameter("os", os);
        os.setContasReceber(q.getResultList());
    
}
}