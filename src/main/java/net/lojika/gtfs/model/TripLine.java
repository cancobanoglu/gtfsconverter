/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "trip_line", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"trip_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TripLine.findAll", query = "SELECT t FROM TripLine t"),
    @NamedQuery(name = "TripLine.findById", query = "SELECT t FROM TripLine t WHERE t.id = :id")})
public class TripLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false)
    @OneToOne(optional = false)
    private Trips tripId;
    @JoinColumn(name = "line_cdk_id", referencedColumnName = "route_cdk_id")
    @ManyToOne
    private RoutesCdkid lineCdkId;



    public TripLine() {
    }

    public TripLine(Long id) {
        this.id = id;
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

    public RoutesCdkid getLineCdkId() {
        return lineCdkId;
    }

    public void setLineCdkId(RoutesCdkid lineCdkId) {
        this.lineCdkId = lineCdkId;
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
        if (!(object instanceof TripLine)) {
            return false;
        }
        TripLine other = (TripLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.TripLine[ id=" + id + " ]";
    }

}
