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
import net.lojika.gtfs.dao.StopsDao;
import net.lojika.gtfs.model.RoutesCdkid;
import net.lojika.gtfs.model.Stops;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author canboanoglu
 */
public class StopsBuilder extends AbstractGtfsBuilder<StopsDao> implements GtfsBuilder {

    public String stopurl;
    public String lineurl;
    public final boolean withGeom;
    public final StopsDao stopsDao;
    public final RoutesCdkDao cdkDao;

    public StopsBuilder(boolean withGeom, StopsDao dao, RoutesCdkDao cdkDao) {
        this.withGeom = withGeom;
        this.stopsDao = dao;
        this.cdkDao = cdkDao;
    }

    @Override
    public StopsBuilder build() {
        try {
            int page = 1;
            
            List<RoutesCdkid> allCdkIds = cdkDao.findAll();
            
            for (RoutesCdkid cdkid : allCdkIds) {
                
                buildLineStopUrl(page, cdkid.getRouteCdkId());

                JSONObject stopsJson = JsonReader.readJsonFromUrl(this.stopurl);
                JSONArray results = getResults(stopsJson);

                int length = results.length();

                for (int i = 0; i < length; i++) {
                    JSONObject layer = getLayer(i, results);
                    JSONObject gtfs = getGtfs(layer);
                    JSONObject geom = getGeom(i, results);
                    JSONObject data = getData(gtfs);
                    JSONArray coords = getCoordinates(geom);

                    Stops newStops = new Stops();
                    newStops.setStopId(getStopId(data));
                    newStops.setStopName(getStopName(data));
                    newStops.setStopLat(coords.getString(0));
                    newStops.setStopLon(coords.getString(1));

                    getDao().saveOrUpdate(newStops);
                }

                page++;
                System.out.println("Fetching page index ::: " + page);

//                if (!hasNext(stopsJson)) {
//                    break;
//                }
            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(StopsBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }

    private void buildStopUrl(int page) {
        if (withGeom) {
            stopurl = CitySdkUrl.buildUrlStopsWithGeom(page);
        } else {
            stopurl = CitySdkUrl.buildUrlStops(page);
        }
    }

    

    private void buildLineStopUrl(int page, String cdklineId) {
        stopurl = String.format("%s%s/select/ptstops?per_page=%s&page=%s&geom", CitySdkUrl.main, cdklineId, "1000", page);
    }

    private JSONObject getGeom(int i, JSONArray results) throws JSONException {
        return results.getJSONObject(i).getJSONObject("geom");
    }

    private JSONArray getCoordinates(JSONObject obj) throws JSONException {
        return obj.getJSONArray("coordinates");
    }

    private String getStopId(JSONObject obj) throws JSONException, JSONException {
        return obj.getString("stop_id");
    }

    private String getStopName(JSONObject obj) throws JSONException {
        return obj.getString("stop_name");
    }

    @Override
    public String getUrl() {
        return stopurl;
    }

    @Override
    protected StopsDao getDao() {
        return stopsDao;
    }
}
