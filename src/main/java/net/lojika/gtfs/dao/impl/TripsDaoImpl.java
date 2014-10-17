/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao.impl;

import net.lojika.gtfs.dao.TripsDao;
import net.lojika.gtfs.model.Trips;
import org.springframework.transaction.annotation.Transactional;

public class TripsDaoImpl extends HibernateGenericDaoImpl<Trips> implements TripsDao {

    public TripsDaoImpl() {
        super(Trips.class);
    }

    @Transactional
    @Override
    public boolean checkIfProcessesAlready(String tripId) {
        Trips findById = findById(tripId);
        return findById != null;
    }
}
