package net.lojika.gtfs.dao.impl;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import net.lojika.gtfs.dao.HibernateGenericDao;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA. User: Sukru_Okul Date: 11.07.2013 Time: 14:19 To
 * change this template use File | Settings | File Templates.
 *
 * @param <T>
 */
public abstract class HibernateGenericDaoImpl<T> implements HibernateGenericDao<T> {

    private final Class clazz;
    private SessionFactory sessionFactory;

    public HibernateGenericDaoImpl(Class clazz) {
        this.clazz = clazz;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }
    
    @Transactional
    @Override
    public void create(T entity) {
        getSession().save(entity);
    }

    @Override
    public void edit(T entity) {
        getSession().update(entity);
    }

    @Override
    public void remove(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void refresh(T entity) {
        getSession().refresh(entity);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Transactional
    @Override
    public <Y extends Serializable> T findById(Y id) {
        return (T) getSession().get(clazz, id);
    }

    @Override
    public <Z, Y extends Serializable> Z find(Class<Z> entityClass, Y id) {
        return (Z) getSession().get(entityClass, id);
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return getSession().createCriteria(clazz).list();
    }

    @Override
    public <T> List<T> findAll(Class<T> clazz) {
        return getSession().createCriteria(clazz).list();
    }

    @Override
    public Number getCount() {
        return getSession().createCriteria(clazz).list().size();
    }
}
