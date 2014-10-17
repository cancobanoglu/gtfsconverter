package net.lojika.gtfs.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: ahmetdal Date: 8/3/13 Time: 1:58 PM To
 * change this template use File | Settings | File Templates.
 *
 * @param <T>
 */
public interface HibernateGenericDao<T> {

    void saveOrUpdate(T entity);

    void create(T entity);

    void edit(T entity);

    void remove(T entity);

    void refresh(T entity);

    void flush();

    <Z extends Object, Y extends Serializable> Z find(Class<Z> entityClass, Y id);

    List<T> findAll();

    <Y extends Object> List<Y> findAll(Class<Y> c);

    Number getCount();

    <Y extends Serializable> T findById(Y id);
}
