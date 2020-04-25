/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Caixa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Camila Padilha
 */
@Stateless
public class CaixaFacade extends AbstractFacade<Caixa> {

    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    public CaixaFacade() {
        super(Caixa.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Caixa salvarRetornando(Caixa object) {
        return em.merge(object);
    }

    public Caixa buscarCaixaAberto() {
        Query q = getEntityManager().createQuery("select c from Caixa as c where c.dataFechamento is null");
        if (!q.getResultList().isEmpty()) {
            Caixa c = (Caixa) q.getSingleResult();
//            c.getContasPagars().size();
//            c.getContasReceber().size();
            return c;
        } else {
            return null;
        }
    }

    public Double buscaContasReceber(Caixa c) {
        Query q = em.createQuery("select Sum(valor) from ContasReceber where dataVencimento = :dataCaixa");
        q.setParameter("dataCaixa", c.getDataAbertura());
        if (!q.getResultList().isEmpty()) {
            return (Double) q.getSingleResult();
        } else {
            return null;
        }
    }

    public Double buscaContasPagar(Caixa c) {
        Query q = em.createQuery("select Sum(valor) from ContasPagar where dataVencimento = :dataCaixa");
        q.setParameter("dataCaixa", c.getDataAbertura());
        if (!q.getResultList().isEmpty()) {
            return (Double) q.getSingleResult();
        } else {
            return null;
        }
    }

    
    public List<Caixa> listaTodos() {
        Query q = getEntityManager().createQuery("select c from Caixa as c order by c.id desc");
        return q.getResultList();
    }

}
