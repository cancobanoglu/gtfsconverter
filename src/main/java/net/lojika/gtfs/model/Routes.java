/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "routes", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Routes.findAll", query = "SELECT r FROM Routes r"),
    @NamedQuery(name = "Routes.findByRouteId", query = "SELECT r FROM Routes r WHERE r.routeId = :routeId"),
    @NamedQuery(name = "Routes.findByRouteShortName", query = "SELECT r FROM Routes r WHERE r.routeShortName = :routeShortName"),
    @NamedQuery(name = "Routes.findByRouteLongName", query = "SELECT r FROM Routes r WHERE r.routeLongName = :routeLongName"),
    @NamedQuery(name = "Routes.findByRouteType", query = "SELECT r FROM Routes r WHERE r.routeType = :routeType")})
public class Routes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "route_id", nullable = false, length = 100)
    private String routeId;
    @Column(name = "route_short_name", length = 500)
    private String routeShortName;
    @Column(name = "route_long_name", length = 500)
    private String routeLongName;
    @Column(name = "route_type", length = 100)
    private String routeType;
    @OneToMany(mappedBy = "routeId")
    private List<Trips> tripsList;

    @OneToMany(mappedBy = "routeId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<RoutesCdkid> routesCdkidCollection;

    public Routes() {
    }

    public Routes(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    @XmlTransient
    public List<Trips> getTripsList() {
        return tripsList;
    }

    public void setTripsList(List<Trips> tripsList) {
        this.tripsList = tripsList;
    }

    public List<RoutesCdkid> getRoutesCdkidCollection() {
        if(routesCdkidCollection == null) {
            routesCdkidCollection = new ArrayList<>();
        }
        return routesCdkidCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeId != null ? routeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Routes)) {
            return false;
        }
        Routes other = (Routes) object;
        if ((this.routeId == null && other.routeId != null) || (this.routeId != null && !this.routeId.equals(other.routeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.Routes[ routeId=" + routeId + " ]";
    }

}
