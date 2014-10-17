/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao.impl;

import java.util.List;
import net.lojika.gtfs.dao.StopTimesDao;
import net.lojika.gtfs.model.StopTimes;
import org.hibernate.SQLQuery;
import org.springframework.transaction.annotation.Transactional;

public class StopTimesDaoImpl extends HibernateGenericDaoImpl<StopTimes> implements StopTimesDao {

    public StopTimesDaoImpl() {
        super(StopTimes.class);
    }

    @Transactional
    @Override
    public StopTimes existsStopTimes(String stopId, String tripId) {
        SQLQuery query = getSession().createSQLQuery("select * from stop_times where trip_id =:tripId and stop_id =:stopId");
        query.setParameter("tripId", tripId);
        query.setParameter("stopId", stopId);
        query.addEntity(StopTimes.class);
        List<StopTimes> list = query.list();

        int size = list.size();

        if (size > 1) {
            for (StopTimes stopTimes : list) {
                remove(stopTimes);
                size--;
            }
        }

        return size == 1 ? list.get(0) : null;
    }

}
