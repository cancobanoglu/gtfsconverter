/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao;

import net.lojika.gtfs.model.StopTimes;

/**
 *
 * @author canboanoglu
 */
public interface StopTimesDao extends HibernateGenericDao<StopTimes> {
    
    StopTimes existsStopTimes(String stopId, String tripId);
    
}
