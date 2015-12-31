/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "typeincident")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeincident.findAll", query = "SELECT t FROM Typeincident t"),
    @NamedQuery(name = "Typeincident.findById", query = "SELECT t FROM Typeincident t WHERE t.id = :id"),
    @NamedQuery(name = "Typeincident.findByName", query = "SELECT t FROM Typeincident t WHERE t.name = :name")})
public class Typeincident implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeIncident")
    private Collection<Incidents> incidentsCollection;

    public Typeincident() {
    }

    public Typeincident(Integer id) {
        this.id = id;
    }

    public Typeincident(Integer id, String name) {
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
    public Collection<Incidents> getIncidentsCollection() {
        return incidentsCollection;
    }

    public void setIncidentsCollection(Collection<Incidents> incidentsCollection) {
        this.incidentsCollection = incidentsCollection;
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
        if (!(object instanceof Typeincident)) {
            return false;
        }
        Typeincident other = (Typeincident) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Typeincident[ id=" + id + " ]";
    }
    
}
