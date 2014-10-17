/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.lojika.gtfs.dao.RoutesDao;
import net.lojika.gtfs.model.Routes;
import net.lojika.gtfs.model.RoutesCdkid;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author canboanoglu
 */
public class RoutesBuilder extends AbstractGtfsBuilder<RoutesDao> implements GtfsBuilder {

    private final RoutesDao routesDao;
    private String url;

    public RoutesBuilder(RoutesDao routesDao) {
        this.routesDao = routesDao;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public GtfsBuilder build() {
        try {
            int page = 1;

            for (;;) {
                buildUrl(page);

                JSONObject stopsJson = JsonReader.readJsonFromUrl(getUrl());
                JSONArray results = getResults(stopsJson);

                int length = results.length();

                for (int i = 0; i < length; i++) {
                    JSONObject layer = getLayer(i, results);
                    String cdkId = getCdkId(results.getJSONObject(i));
                    JSONObject gtfs = getGtfs(layer);
                    JSONObject data = getData(gtfs);

                    // first check if there is any route object
                    Routes route = getDao().findById(getRouteId(data));

                    if (route == null) {
                        route = new Routes();
                        route.setRouteId(getRouteId(data));
                    }

                    RoutesCdkid routesCdkid = new RoutesCdkid(cdkId);
                    routesCdkid.setRouteId(route);
                    route.getRoutesCdkidCollection().add(routesCdkid);
                    
                    route.setRouteLongName(getRouteLongName(data));
                    route.setRouteShortName(getRouteShortName(data));
                    route.setRouteType(getRouteType(data));

                    getDao().saveOrUpdate(route);
                }

                page++;
                System.out.println("Current fetched page index ::: " + page);

                if (!hasNext(stopsJson)) {
                    break;
                }
            }

        } catch (IOException | JSONException ex) {
            Logger.getLogger(StopsBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this;
    }

    private String getRouteId(JSONObject obj) throws JSONException, JSONException {
        return obj.getString("route_id");
    }

    private String getRouteLongName(JSONObject obj) throws JSONException, JSONException {
        return obj.getString("route_long_name");
    }

    private String getRouteShortName(JSONObject obj) throws JSONException, JSONException {
        return obj.getString("route_short_name");
    }

    private String getRouteType(JSONObject obj) throws JSONException, JSONException {
        return obj.getString("route_type");
    }

    private void buildUrl(int page) {
        url = CitySdkUrl.buildUrlLines(page);
    }

    @Override
    protected RoutesDao getDao() {
        return routesDao;
    }

}
