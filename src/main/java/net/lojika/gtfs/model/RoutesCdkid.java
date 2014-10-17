/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.lojika.gtfs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "routes_cdkid", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoutesCdkid.findAll", query = "SELECT r FROM RoutesCdkid r"),
    @NamedQuery(name = "RoutesCdkid.findByRouteCdkId", query = "SELECT r FROM RoutesCdkid r WHERE r.routeCdkId = :routeCdkId")})
public class RoutesCdkid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "route_cdk_id", nullable = false, length = 100)
    private String routeCdkId;
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    @ManyToOne
    private Routes routeId;

    public RoutesCdkid() {
    }

    public RoutesCdkid(String routeCdkId) {
        this.routeCdkId = routeCdkId;
    }

    public String getRouteCdkId() {
        return routeCdkId;
    }

    public void setRouteCdkId(String routeCdkId) {
        this.routeCdkId = routeCdkId;
    }

    public Routes getRouteId() {
        return routeId;
    }

    public void setRouteId(Routes routeId) {
        this.routeId = routeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (routeCdkId != null ? routeCdkId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoutesCdkid)) {
            return false;
        }
        RoutesCdkid other = (RoutesCdkid) object;
        if ((this.routeCdkId == null && other.routeCdkId != null) || (this.routeCdkId != null && !this.routeCdkId.equals(other.routeCdkId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.RoutesCdkid[ routeCdkId=" + routeCdkId + " ]";
    }

}
