/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author marcos-ladeira
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    
    protected abstract EntityManager getEntityManager();
    
    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void save(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        Query q=getEntityManager().createQuery("from "+entityClass.getSimpleName());
        return q.getResultList();
    }

    public List<T> findRange(int[] range) {
        Query q=getEntityManager().createQuery("from "+entityClass.getSimpleName());
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        Query q=getEntityManager().createQuery("from "+entityClass.getSimpleName());
        return q.getResultList().size();
    }
    
    public List<T> listaFiltrando(String filtro, String... atributos) {
        String hql = "from " + entityClass.getSimpleName() + " obj where ";
        for (String atributo : atributos) {
            hql += "lower(obj." + atributo + ") like :filtro OR ";
        }
        hql = hql.substring(0, hql.length() - 3);
        System.out.println("SQL: "+hql);
        Query q = getEntityManager().createQuery(hql);
        q.setParameter("filtro", "%" + filtro.toLowerCase() + "%");
        return q.getResultList();
    }

}