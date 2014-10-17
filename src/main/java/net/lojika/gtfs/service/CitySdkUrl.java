package net.lojika.gtfs.service;

import java.io.IOException;

import org.json.JSONException;

public class CitySdkUrl {

    static String main = "http://apicitysdk.ibb.gov.tr/";
    static String region = "admr.istanbul/";
    static String stops = "ptstops";
    static String lines = "ptlines";
    static String gtfs = "layer=gtfs";
    static String perpage = "per_page=";
    static String page = "page=";
    static String schedule = "select/schedule";
    static String geom = "geom";

    public static String makeStops() {
        String url = new StringBuilder().append(main).append(region).append(stops).append("?").append(gtfs).append("&").append(perpage).append("1000").append("&").append(page).append("1").toString();
        return url;
    }

    public static String buildUrlStops(int cnt) {
        return new StringBuilder().append(main).append(region).append(stops).append("?").append(gtfs).append("&").append(perpage).append("1000").append("&").append(page).append(cnt).toString();
    }

    public static String buildUrlStopsWithGeom(int cnt) {
        return new StringBuilder().append(main).append(region).append(stops).append("?").append(gtfs).append("&").append(geom).append("&").append(perpage).append("1000").append("&").append(page).append(cnt).toString();
    }

    public static String buildUrlStopsWithGeomByCdkId(String cdkId) {
        return new StringBuilder().append(main).append(cdkId).append("?").append(gtfs).append("&").append(geom).toString();
    }

    public static String makeStops(int pp, int cnt) {
        String url = new StringBuilder().append(main).append(region).append(stops).append("?").append(gtfs).append("&").append(perpage).append(pp).append("&").append(page).append(cnt).toString();
        return url;
    }

    public static String makeLines() {
        String url = new StringBuilder().append(main).append(region).append(lines).append("?").append(gtfs).append("&").append(perpage).append("1000").append("&").append(page).append("1").toString();
        return url;
    }

    public static String buildUrlLines(int cnt) {
        String url = new StringBuilder().append(main).append(region).append(lines).append("?").append(gtfs).append("&").append(perpage).append("1000").append("&").append(page).append(cnt).toString();
        return url;
    }

    public static String makeLines(int pp, int cnt) {
        String url = new StringBuilder().append(main).append(region).append(lines).append("?").append(gtfs).append("&").append(perpage).append(pp).append("&").append(page).append(cnt).toString();
        return url;
    }



    public static String makeLineSchedule(String cdkId) throws JSONException, IOException {
        String url = new StringBuilder().append(main).append(cdkId).append("/").append(schedule).toString();
        return url;
    }

}
