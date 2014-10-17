/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.service;

import java.io.IOException;
import java.util.List;
import net.lojika.gtfs.dao.RoutesCdkDao;
import net.lojika.gtfs.dao.StopTimesDao;
import net.lojika.gtfs.dao.StopsDao;
import net.lojika.gtfs.dao.TripLineDao;
import net.lojika.gtfs.dao.TripsDao;
import net.lojika.gtfs.model.Stops;
import net.lojika.gtfs.service.impl.CitySdkHandlerImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cancobanoglu
 */
public class LinesTest extends AbstractServiceImplTest {

    @Autowired
    private StopsDao stopsDao;
    @Autowired
    private RoutesCdkDao routesCdkDao;
    @Autowired
    private TripsDao tripsDao;
    @Autowired
    private TripLineDao tripLineDao;
    @Autowired
    private StopTimesDao stopTimesDao;

    public LinesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of test method, of class ClickTrackEventDaoImpl.
     */
    @Test
    public void fetchStopsAndPersist() {
        GtfsBuilder tripBuilder = new LineBuilder(tripsDao, routesCdkDao, stopsDao, stopTimesDao).build();
    }
}
