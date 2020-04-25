/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entidades.AgendamentoHorario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marcos-ladeira
 */
@Stateless
public class AgendamentoHorarioFacade extends AbstractFacade<AgendamentoHorario> {
    
    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;
     
     protected EntityManager getEntityManager() {
        return em;
    }

     public AgendamentoHorarioFacade(){
         super(AgendamentoHorario.class);
     }
     
    protected EntityManager getEm() {
        return em;
    }
    
}
