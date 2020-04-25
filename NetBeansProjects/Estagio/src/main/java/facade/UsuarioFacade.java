/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;
import entidades.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author marcos-ladeira
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "estagioPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario validaUsuario(Usuario u) {
        String hql = "from Usuario obj where obj.login = :filtro1 and obj.senha = :filtro2";                    
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro1", u.getLogin());
        q.setParameter("filtro2", u.getSenha());
        if(q.getResultList().isEmpty()){
            return u;
        }else{
            return (Usuario) q.getSingleResult();
        }
        
    }

}