/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao.impl;

import net.lojika.gtfs.dao.RoutesCdkDao;
import net.lojika.gtfs.model.RoutesCdkid;

public class RoutesCdkDaoImpl extends HibernateGenericDaoImpl<RoutesCdkid> implements RoutesCdkDao {

    public RoutesCdkDaoImpl() {
        super(RoutesCdkid.class);
    }
    
    
    
}
