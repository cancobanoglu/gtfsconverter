/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lojika.gtfs.dao.RoutesCdkDao;
import net.lojika.gtfs.dao.StopTimesDao;
import net.lojika.gtfs.dao.StopsDao;
import net.lojika.gtfs.dao.TripsDao;
import net.lojika.gtfs.model.RoutesCdkid;
import net.lojika.gtfs.model.StopTimes;
import net.lojika.gtfs.model.Stops;
import net.lojika.gtfs.model.Trips;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author canboanoglu
 */
public class LineBuilder extends AbstractGtfsBuilder<TripsDao> implements GtfsBuilder {

    private final TripsDao tripsDao;
    private final RoutesCdkDao routesCdkDao;
    private final StopsDao stopsDao;
    private final StopTimesDao stopTimesDao;

    public LineBuilder(TripsDao tripsDao, RoutesCdkDao cdkDao, StopsDao stopsDao, StopTimesDao stopTimesDao) {
        this.tripsDao = tripsDao;
        this.routesCdkDao = cdkDao;
        this.stopsDao = stopsDao;
        this.stopTimesDao = stopTimesDao;
    }

    @Override
    protected TripsDao getDao() {
        return tripsDao;
    }

    @Override
    public String getUrl() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GtfsBuilder build() {
        try {
            int page = 1;

            List<RoutesCdkid> allCdkIds = routesCdkDao.findAll();

            for (RoutesCdkid cdkid : allCdkIds) {
                System.out.println("processing line for ::: " + cdkid.getRouteCdkId());
                buildTripUrlByLineId(cdkid.getRouteCdkId());

                JSONObject stopsJson = JsonReader.readJsonFromUrl(this.lineUrl);
                JSONArray results = getResults(stopsJson);

                int length = results.length();

                for (int i = 0; i < length; i++) {
                    JSONObject result = results.getJSONObject(i);
                    JSONArray tripsJson = getTrips(result);

                    int tripCount = tripsJson.length();
                    System.out.println("total trip count for line ::: " + cdkid.getRouteCdkId());
                    for (int k = 0; k < tripCount; k++) {
                        JSONArray tripJson = tripsJson.getJSONArray(k);

                        String tripId = cdkid.getRouteCdkId() + "." + k;
                        System.out.println("processing trip for ::: " + cdkid.getRouteCdkId() + " with ::: id : " + tripId);
                        Trips trip = tripsDao.findById(tripId);

                        if (trip == null) {
                            System.out.println("new trip processing for ::: " + tripId);
                            trip = new Trips();
                            trip.setRouteId(cdkid.getRouteId());
                            trip.setTripId(tripId);
                            trip.setServiceId("WD");

                            tripsDao.saveOrUpdate(trip);
                        }

                        int tripJsonsize = tripJson.length();
                        System.out.println("total stop count for trip ::: " + tripId + " is = " + tripJsonsize);

                        for (int j = 0; j < tripJsonsize; j++) {
                            JSONArray stopAndTimeObj = tripJson.getJSONArray(j);
                            String rawStopId = stopAndTimeObj.getString(0);
                            String stopId = extractId(rawStopId);
                            String time = stopAndTimeObj.getString(1);

                            StopTimes stopTimes = stopTimesDao.existsStopTimes(stopId, tripId);

                            if (stopTimes == null) {
                                stopTimes = new StopTimes();
                            }

                            stopTimes.setTripId(trip);
                            stopTimes.setArrivalTime(time);
                            stopTimes.setDepartureTime(time);

                            Stops stop = stopsDao.findById(stopId);
                            stopTimes.setStopSequence(j + 1 + "");

                            if (stop != null) {
                                stopTimes.setStopId(stop);
                                stopTimesDao.saveOrUpdate(stopTimes);
                            }
                        }

                    }
                }

                page++;
            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(StopsBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error has been recorded");
        }
        
        System.out.println("Successfuly finished...");

        return this;
    }

    protected String extractId(String rawId) {
        return rawId.substring(rawId.indexOf("istb"), rawId.length());
    }

    public static void main(String[] args) {

        String stopId = "gtfs.stop.istb.540".substring("gtfs.stop.istb.540".indexOf("istb"), "gtfs.stop.istb.540".length());

        System.out.println(stopId);
    }

}
