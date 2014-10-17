/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.dao.impl;

import net.lojika.gtfs.dao.TripLineDao;
import net.lojika.gtfs.model.TripLine;

public class TripLineDaoImpl extends HibernateGenericDaoImpl<TripLine> implements TripLineDao {

    public TripLineDaoImpl() {
        super(TripLine.class);
    }

}
