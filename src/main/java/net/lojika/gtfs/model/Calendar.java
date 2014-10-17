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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author canboanoglu
 */
@Entity
@Table(name = "calendar", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calendar.findAll", query = "SELECT c FROM Calendar c"),
    @NamedQuery(name = "Calendar.findByServiceId", query = "SELECT c FROM Calendar c WHERE c.serviceId = :serviceId"),
    @NamedQuery(name = "Calendar.findByMonday", query = "SELECT c FROM Calendar c WHERE c.monday = :monday"),
    @NamedQuery(name = "Calendar.findByTuesday", query = "SELECT c FROM Calendar c WHERE c.tuesday = :tuesday"),
    @NamedQuery(name = "Calendar.findByWednesday", query = "SELECT c FROM Calendar c WHERE c.wednesday = :wednesday"),
    @NamedQuery(name = "Calendar.findByThursday", query = "SELECT c FROM Calendar c WHERE c.thursday = :thursday"),
    @NamedQuery(name = "Calendar.findByFriday", query = "SELECT c FROM Calendar c WHERE c.friday = :friday"),
    @NamedQuery(name = "Calendar.findBySaturday", query = "SELECT c FROM Calendar c WHERE c.saturday = :saturday"),
    @NamedQuery(name = "Calendar.findBySunday", query = "SELECT c FROM Calendar c WHERE c.sunday = :sunday"),
    @NamedQuery(name = "Calendar.findByStartDate", query = "SELECT c FROM Calendar c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Calendar.findByEndDate", query = "SELECT c FROM Calendar c WHERE c.endDate = :endDate")})
public class Calendar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "service_id", nullable = false, length = 100)
    private String serviceId;
    @Column(name = "monday", length = 1)
    private String monday;
    @Column(name = "tuesday", length = 1)
    private String tuesday;
    @Column(name = "wednesday", length = 1)
    private String wednesday;
    @Column(name = "thursday", length = 1)
    private String thursday;
    @Column(name = "friday", length = 1)
    private String friday;
    @Column(name = "saturday", length = 1)
    private String saturday;
    @Column(name = "sunday", length = 1)
    private String sunday;
    @Column(name = "start_date", length = 100)
    private String startDate;
    @Column(name = "end_date", length = 100)
    private String endDate;

    public Calendar() {
    }

    public Calendar(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceId != null ? serviceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calendar)) {
            return false;
        }
        Calendar other = (Calendar) object;
        if ((this.serviceId == null && other.serviceId != null) || (this.serviceId != null && !this.serviceId.equals(other.serviceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.gtfs.ejb.Calendar[ serviceId=" + serviceId + " ]";
    }
    
}
