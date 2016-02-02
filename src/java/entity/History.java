/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "History.findAll", query = "SELECT h FROM History h"),
    @NamedQuery(name = "History.findById", query = "SELECT h FROM History h WHERE h.id = :id"),
    @NamedQuery(name = "History.findByDateAction", query = "SELECT h FROM History h WHERE h.dateAction = :dateAction"),
    @NamedQuery(name = "History.findByTimeAction", query = "SELECT h FROM History h WHERE h.timeAction = :timeAction")})
public class History implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAction")
    @Temporal(TemporalType.DATE)
    private Date dateAction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeAction")
    @Temporal(TemporalType.TIME)
    private Date timeAction;
    @JoinColumn(name = "incident", referencedColumnName = "id")
    @ManyToOne
    private Incidents incident;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne
    private Statuses status;
    @JoinColumn(name = "actioner", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Users actioner;

    public History() {
    }

    public History(Integer id) {
        this.id = id;
    }

    public History(Integer id, Date dateAction, Date timeAction) {
        this.id = id;
        this.dateAction = dateAction;
        this.timeAction = timeAction;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public Date getTimeAction() {
        return timeAction;
    }

    public void setTimeAction(Date timeAction) {
        this.timeAction = timeAction;
    }

    public Incidents getIncident() {
        return incident;
    }

    public void setIncident(Incidents incident) {
        this.incident = incident;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public Users getActioner() {
        return actioner;
    }

    public void setActioner(Users actioner) {
        this.actioner = actioner;
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
        if (!(object instanceof History)) {
            return false;
        }
        History other = (History) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.History[ id=" + id + " ]";
    }
    
}
