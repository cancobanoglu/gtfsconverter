/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.lojika.gtfs.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "stops", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stops.findAll", query = "SELECT s FROM Stops s"),
    @NamedQuery(name = "Stops.findByStopId", query = "SELECT s FROM Stops s WHERE s.stopId = :stopId"),
    @NamedQuery(name = "Stops.findByStopName", query = "SELECT s FROM Stops s WHERE s.stopName = :stopName"),
    @NamedQuery(name = "Stops.findByStopLat", query = "SELECT s FROM Stops s WHERE s.stopLat = :stopLat"),
    @NamedQuery(name = "Stops.findByStopLon", query = "SELECT s FROM Stops s WHERE s.stopLon = :stopLon")})
public class Stops implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "stop_id", nullable = false, length = 500)
    private String stopId;
    @Column(name = "stop_name", length = 500)
    private String stopName;
    @Column(name = "stop_lat", length = 100)
    private String stopLat;
    @Column(name = "stop_lon", length = 100)
    private String stopLon;
    @OneToMany(mappedBy = "stopId")
    private List<StopTimes> stopTimesList;

    public Stops() {
    }

    public Stops(String stopId) {
        this.stopId = stopId;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopLat() {
        return stopLat;
    }

    public void setStopLat(String stopLat) {
        this.stopLat = stopLat;
    }

    public String getStopLon() {
        return stopLon;
    }

    public void setStopLon(String stopLon) {
        this.stopLon = stopLon;
    }

    @XmlTransient
    public List<StopTimes> getStopTimesList() {
        return stopTimesList;
    }

    public void setStopTimesList(List<StopTimes> stopTimesList) {
        this.stopTimesList = stopTimesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stopId != null ? stopId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stops)) {
            return false;
        }
        Stops other = (Stops) object;
        if ((this.stopId == null && other.stopId != null) || (this.stopId != null && !this.stopId.equals(other.stopId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.Stops[ stopId=" + stopId + " ]";
    }
    
}
