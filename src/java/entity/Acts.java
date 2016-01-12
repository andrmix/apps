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
@Table(name = "acts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acts.findAll", query = "SELECT a FROM Acts a"),
    @NamedQuery(name = "Acts.findById", query = "SELECT a FROM Acts a WHERE a.id = :id"),
    @NamedQuery(name = "Acts.findByDateAct", query = "SELECT a FROM Acts a WHERE a.dateAct = :dateAct"),
    @NamedQuery(name = "Acts.findByPodpisNach", query = "SELECT a FROM Acts a WHERE a.podpisNach = :podpisNach"),
    @NamedQuery(name = "Acts.findByPodpisZayavitel", query = "SELECT a FROM Acts a WHERE a.podpisZayavitel = :podpisZayavitel")})
public class Acts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAct")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAct;
    @Basic(optional = false)
    @NotNull
    @Column(name = "podpisNach")
    private int podpisNach;
    @Basic(optional = false)
    @NotNull
    @Column(name = "podpisZayavitel")
    private int podpisZayavitel;
    @JoinColumn(name = "incident", referencedColumnName = "id")
    @ManyToOne
    private Incidents incident;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne
    private Statuses status;
    @JoinColumn(name = "typeAct", referencedColumnName = "id")
    @ManyToOne
    private Typeact typeAct;

    public Acts() {
    }

    public Acts(Integer id) {
        this.id = id;
    }

    public Acts(Integer id, Date dateAct, int podpisNach, int podpisZayavitel) {
        this.id = id;
        this.dateAct = dateAct;
        this.podpisNach = podpisNach;
        this.podpisZayavitel = podpisZayavitel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAct() {
        return dateAct;
    }

    public void setDateAct(Date dateAct) {
        this.dateAct = dateAct;
    }

    public int getPodpisNach() {
        return podpisNach;
    }

    public void setPodpisNach(int podpisNach) {
        this.podpisNach = podpisNach;
    }

    public int getPodpisZayavitel() {
        return podpisZayavitel;
    }

    public void setPodpisZayavitel(int podpisZayavitel) {
        this.podpisZayavitel = podpisZayavitel;
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

    public Typeact getTypeAct() {
        return typeAct;
    }

    public void setTypeAct(Typeact typeAct) {
        this.typeAct = typeAct;
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
        if (!(object instanceof Acts)) {
            return false;
        }
        Acts other = (Acts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Acts[ id=" + id + " ]";
    }
    
}
