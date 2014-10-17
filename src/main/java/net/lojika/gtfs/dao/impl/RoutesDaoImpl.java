/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao.impl;

import net.lojika.gtfs.dao.RoutesDao;
import net.lojika.gtfs.model.Routes;

public class RoutesDaoImpl extends HibernateGenericDaoImpl<Routes> implements RoutesDao {

    public RoutesDaoImpl() {
        super(Routes.class);
    }
    
}
