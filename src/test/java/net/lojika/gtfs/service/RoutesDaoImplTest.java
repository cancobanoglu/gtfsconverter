/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.service;

import net.lojika.gtfs.dao.RoutesDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author cancobanoglu
 */
public class RoutesDaoImplTest extends AbstractServiceImplTest {

    @Autowired
    private RoutesDao routesDao;

    public RoutesDaoImplTest() {
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
    public void fetchRoutesAndPersist() {
        GtfsBuilder routesBuilder = new RoutesBuilder(routesDao).build();
    }

}
