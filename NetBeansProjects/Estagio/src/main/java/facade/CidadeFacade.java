/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.Cidade;
import entidades.Estado;
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
public class CidadeFacade extends AbstractFacade<Cidade> {

    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
      public List<Cidade> listaFiltrandoEstado(Estado est) {
        String hql = "from Cidade obj ";
        if (est != null) {
            hql += "where obj.estado = :filtro";
        }
        Query q = getEntityManager().createQuery(hql);
        if (est != null) {
            q.setParameter("filtro", est);
        }
        return q.getResultList();
    }

}
