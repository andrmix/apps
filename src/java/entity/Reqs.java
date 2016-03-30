/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "reqs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reqs.findAll", query = "SELECT r FROM Reqs r"),
    @NamedQuery(name = "Reqs.findById", query = "SELECT r FROM Reqs r WHERE r.id = :id"),
    @NamedQuery(name = "Reqs.findByDateReq", query = "SELECT r FROM Reqs r WHERE r.dateReq = :dateReq"),
    @NamedQuery(name = "Reqs.findByTimeReq", query = "SELECT r FROM Reqs r WHERE r.timeReq = :timeReq"),
    @NamedQuery(name = "Reqs.findByText", query = "SELECT r FROM Reqs r WHERE r.text = :text"),
    @NamedQuery(name = "Reqs.findByDecision", query = "SELECT r FROM Reqs r WHERE r.decision = :decision")})
public class Reqs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dateReq")
    @Temporal(TemporalType.DATE)
    private Date dateReq;
    @Column(name = "timeReq")
    @Temporal(TemporalType.TIME)
    private Date timeReq;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "text")
    private String text;
    @Size(max = 255)
    @Column(name = "decision")
    private String decision;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne
    private Statuses status;
    @JoinColumn(name = "specialist", referencedColumnName = "login")
    @ManyToOne
    private Users specialist;
    @OneToMany(mappedBy = "req")
    private Collection<Arcincidents> arcincidentsCollection;
    @OneToMany(mappedBy = "req")
    private Collection<Incidents> incidentsCollection;

    public Reqs() {
    }

    public Reqs(Integer id) {
        this.id = id;
    }

    public Reqs(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateReq() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateReq);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setDateReq(Date dateReq) {
        this.dateReq = dateReq;
    }

    public String getTimeReq() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeReq);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setTimeReq(Date timeReq) {
        this.timeReq = timeReq;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public Users getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Users specialist) {
        this.specialist = specialist;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reqs)) {
            return false;
        }
        Reqs other = (Reqs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reqs[ id=" + id + " ]";
    }
    
}
