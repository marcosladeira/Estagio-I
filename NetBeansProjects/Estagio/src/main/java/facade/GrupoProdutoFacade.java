/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.GrupoProduto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcos-ladeira
 */
@Stateless
public class GrupoProdutoFacade extends AbstractFacade<GrupoProduto> {

    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public GrupoProdutoFacade() {
        super(GrupoProduto.class);
    }

}