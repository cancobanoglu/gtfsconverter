/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "stop_times", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StopTimes.findAll", query = "SELECT s FROM StopTimes s"),
    @NamedQuery(name = "StopTimes.findByArrivalTime", query = "SELECT s FROM StopTimes s WHERE s.arrivalTime = :arrivalTime"),
    @NamedQuery(name = "StopTimes.findByDepartureTime", query = "SELECT s FROM StopTimes s WHERE s.departureTime = :departureTime"),
    @NamedQuery(name = "StopTimes.findByStopSequence", query = "SELECT s FROM StopTimes s WHERE s.stopSequence = :stopSequence"),
    @NamedQuery(name = "StopTimes.findById", query = "SELECT s FROM StopTimes s WHERE s.id = :id")})
public class StopTimes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "xseq_id", sequenceName = "seq_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "xseq_id")

    @Column(name = "id")
    private Long id;
    @Column(name = "arrival_time", length = 100)
    private String arrivalTime;
    @Column(name = "departure_time", length = 100)
    private String departureTime;
    @Column(name = "stop_sequence", length = 100)
    private String stopSequence;

    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    @ManyToOne
    private Trips tripId;
    @JoinColumn(name = "stop_id", referencedColumnName = "stop_id")
    @ManyToOne
    private Stops stopId;

    public StopTimes() {
    }

    public StopTimes(Long id) {
        this.id = id;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStopSequence() {
        return stopSequence;
    }

    public void setStopSequence(String stopSequence) {
        this.stopSequence = stopSequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trips getTripId() {
        return tripId;
    }

    public void setTripId(Trips tripId) {
        this.tripId = tripId;
    }

    public Stops getStopId() {
        return stopId;
    }

    public void setStopId(Stops stopId) {
        this.stopId = stopId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StopTimes)) {
            return false;
        }
        StopTimes other = (StopTimes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.StopTimes[ id=" + id + " ]";
    }

}
