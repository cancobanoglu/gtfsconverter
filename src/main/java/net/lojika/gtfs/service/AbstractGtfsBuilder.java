/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.service;

import net.lojika.gtfs.dao.HibernateGenericDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author canboanoglu
 */
public abstract class AbstractGtfsBuilder<T extends HibernateGenericDao> {

    protected abstract T getDao();
    protected String lineUrl;

    public AbstractGtfsBuilder() {
    }

    protected JSONArray getResults(JSONObject obj) throws JSONException {
        return obj.getJSONArray("results");
    }

    protected JSONObject getGtfs(JSONObject obj) throws JSONException {
        return obj.getJSONObject("gtfs");
    }
    
    protected JSONArray getTrips(JSONObject obj) throws JSONException {
        return obj.getJSONArray("trips");
    }

    protected JSONObject getData(JSONObject obj) throws JSONException {
        return obj.getJSONObject("data");
    }

    protected String getCdkId(JSONObject obj) throws JSONException {
        return obj.getString("cdk_id");
    }

    public abstract String getUrl();

    protected JSONObject getLayer(int i, JSONArray results) throws JSONException {
        return results.getJSONObject(i).getJSONObject("layers");
    }

    protected boolean hasNext(JSONObject obj) throws JSONException {
        int hasPage = obj.getInt("next_page");
        if (hasPage == -1) {
            return false;
        } else {
            return true;
        }
    }

    protected void buildLineUrl(int page) {
        lineUrl = String.format("%s%sptlines?per_page=%s&page=%s&geom", CitySdkUrl.main, CitySdkUrl.region, "1000", page);
    }

    protected void buildTripUrlByLineId(String cdklineId) {
        lineUrl = String.format("%s%s/select/schedule", CitySdkUrl.main, cdklineId);
    }

}
