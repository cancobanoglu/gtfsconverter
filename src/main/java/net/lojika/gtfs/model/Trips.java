/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "trips", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trips.findAll", query = "SELECT t FROM Trips t"),
    @NamedQuery(name = "Trips.findByServiceId", query = "SELECT t FROM Trips t WHERE t.serviceId = :serviceId"),
    @NamedQuery(name = "Trips.findByTripId", query = "SELECT t FROM Trips t WHERE t.tripId = :tripId")})
public class Trips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "service_id", length = 100)
    private String serviceId;
    @Id
    @Basic(optional = false)
    @Column(name = "trip_id", nullable = false, length = 100)
    private String tripId;
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    @ManyToOne
    private Routes routeId;
//    @OneToMany(mappedBy = "tripId")
//    private List<StopTimes> stopTimesList;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tripId")
    private TripLine tripLine;

    public Trips() {
    }

    public TripLine getTripLine() {
        return tripLine;
    }

    public void setTripLine(TripLine tripLine) {
        this.tripLine = tripLine;
    }

    public Trips(String tripId) {
        this.tripId = tripId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public Routes getRouteId() {
        return routeId;
    }

    public void setRouteId(Routes routeId) {
        this.routeId = routeId;
    }

//    @XmlTransient
//    public List<StopTimes> getStopTimesList() {
//        return stopTimesList;
//    }
//
//    public void setStopTimesList(List<StopTimes> stopTimesList) {
//        this.stopTimesList = stopTimesList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tripId != null ? tripId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trips)) {
            return false;
        }
        Trips other = (Trips) object;
        if ((this.tripId == null && other.tripId != null) || (this.tripId != null && !this.tripId.equals(other.tripId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.Trips[ tripId=" + tripId + " ]";
    }

}
