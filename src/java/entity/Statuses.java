/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author admin
 */
@Entity
@Table(name = "statuses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statuses.findAll", query = "SELECT s FROM Statuses s"),
    @NamedQuery(name = "Statuses.findById", query = "SELECT s FROM Statuses s WHERE s.id = :id"),
    @NamedQuery(name = "Statuses.findByName", query = "SELECT s FROM Statuses s WHERE s.name = :name"),

    @NamedQuery(name = "Statuses.findAllOrder", query = "SELECT s FROM Statuses s ORDER BY s.id")
})
public class Statuses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "status")
    private Collection<Reqs> reqsCollection;
    @OneToMany(mappedBy = "status")
    private Collection<Arcincidents> arcincidentsCollection;
    @OneToMany(mappedBy = "status")
    private Collection<Incidents> incidentsCollection;
    @OneToMany(mappedBy = "status")
    private Collection<History> historyCollection;

    public Statuses() {
    }

    public Statuses(Integer id) {
        this.id = id;
    }

    public Statuses(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<Reqs> getReqsCollection() {
        return reqsCollection;
    }

    public void setReqsCollection(Collection<Reqs> reqsCollection) {
        this.reqsCollection = reqsCollection;
    }

    @XmlTransient
    public Collection<Arcincidents> getArcincidentsCollection() {
        return arcincidentsCollection;
    }

    public void setArcincidentsCollection(Collection<Arcincidents> arcincidentsCollection) {
        this.arcincidentsCollection = arcincidentsCollection;
    }

    @XmlTransient
    public Collection<Incidents> getIncidentsCollection() {
        return incidentsCollection;
    }

    public void setIncidentsCollection(Collection<Incidents> incidentsCollection) {
        this.incidentsCollection = incidentsCollection;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
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
        if (!(object instanceof Statuses)) {
            return false;
        }
        Statuses other = (Statuses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Statuses[ id=" + id + " ]";
    }
    
}
