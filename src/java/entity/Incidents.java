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
@Table(name = "incidents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidents.findAll", query = "SELECT i FROM Incidents i"),
    @NamedQuery(name = "Incidents.findById", query = "SELECT i FROM Incidents i WHERE i.id = :id"),
    @NamedQuery(name = "Incidents.findByDateIncident", query = "SELECT i FROM Incidents i WHERE i.dateIncident = :dateIncident"),
    @NamedQuery(name = "Incidents.findByTimeIncident", query = "SELECT i FROM Incidents i WHERE i.timeIncident = :timeIncident"),
    @NamedQuery(name = "Incidents.findByTitle", query = "SELECT i FROM Incidents i WHERE i.title = :title"),
    @NamedQuery(name = "Incidents.findByText", query = "SELECT i FROM Incidents i WHERE i.text = :text"),
    @NamedQuery(name = "Incidents.findByDecision", query = "SELECT i FROM Incidents i WHERE i.decision = :decision"),
    @NamedQuery(name = "Incidents.findByDateDone", query = "SELECT i FROM Incidents i WHERE i.dateDone = :dateDone"),
    @NamedQuery(name = "Incidents.findByTimeDone", query = "SELECT i FROM Incidents i WHERE i.timeDone = :timeDone"),
    @NamedQuery(name = "Incidents.findByUser", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user"),
    @NamedQuery(name = "Incidents.findByUser1Status", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status = :status"),
    @NamedQuery(name = "Incidents.findByUser3Status", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3)"),
    @NamedQuery(name = "Incidents.findByUser3StatusOrderName", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3) ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findByUser3StatusOrderDate", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3) ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findByUser3StatusOrderStatus", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3) ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findByUser3StatusOrderSpec", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3) ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findByUser4Status", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3 OR i.status = :status4)"),
    @NamedQuery(name = "Incidents.findByUser4StatusOrderName", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3 OR i.status = :status4) ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findByUser4StatusOrderDate", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3 OR i.status = :status4) ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findByUser4StatusOrderStatus", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3 OR i.status = :status4) ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findByUser4StatusOrderSpec", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3 OR i.status = :status4) ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findBySpecialist1Status", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND i.status = :status"),
    @NamedQuery(name = "Incidents.findBySpecialist2Status", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2)"),
    @NamedQuery(name = "Incidents.findBySpecialist3Status", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2 OR i.status = :status3)"),
    @NamedQuery(name = "Incidents.findUnallocated", query = "SELECT i FROM Incidents i WHERE i.status = :status"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderName", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderDate", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderStatus", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderZay", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findAllocated", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2"),
    @NamedQuery(name = "Incidents.findAllocatedOrderName", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findAllocatedOrderDate", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findAllocatedOrderStatus", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findAllocatedOrderZay", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findAllocatedOrderSpec", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findByDateClose", query = "SELECT i FROM Incidents i WHERE i.dateClose = :dateClose"),
    @NamedQuery(name = "Incidents.findByTimeClose", query = "SELECT i FROM Incidents i WHERE i.timeClose = :timeClose")})
public class Incidents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateIncident")
    @Temporal(TemporalType.DATE)
    private Date dateIncident;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeIncident")
    @Temporal(TemporalType.TIME)
    private Date timeIncident;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "text")
    private String text;
    @Size(max = 255)
    @Column(name = "decision")
    private String decision;
    @Column(name = "dateDone")
    @Temporal(TemporalType.DATE)
    private Date dateDone;
    @Column(name = "timeDone")
    @Temporal(TemporalType.TIME)
    private Date timeDone;
    @Column(name = "dateClose")
    @Temporal(TemporalType.DATE)
    private Date dateClose;
    @Column(name = "timeClose")
    @Temporal(TemporalType.TIME)
    private Date timeClose;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incident")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne
    private Statuses status;
    @JoinColumn(name = "typeIncident", referencedColumnName = "id")
    @ManyToOne
    private Typeincident typeIncident;
    @JoinColumn(name = "zayavitel", referencedColumnName = "login")
    @ManyToOne
    private Users zayavitel;
    @JoinColumn(name = "specialist", referencedColumnName = "login")
    @ManyToOne
    private Users specialist;
    @OneToMany(mappedBy = "incident")
    private Collection<Acts> actsCollection;

    public Incidents() {
    }

    public Incidents(Integer id) {
        this.id = id;
    }

    public Incidents(Integer id, Date dateIncident, Date timeIncident, String title, String text) {
        this.id = id;
        this.dateIncident = dateIncident;
        this.timeIncident = timeIncident;
        this.title = title;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateIncident() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateIncident);
        } catch (NullPointerException e) {
            return "Дата не определена";
        }
    }

    public void setDateIncident(Date dateIncident) {
        this.dateIncident = dateIncident;
    }

    public String getTimeIncident() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeIncident);
        } catch (NullPointerException e) {
            return "Время не определено";
        }
    }

    public void setTimeIncident(Date timeIncident) {
        this.timeIncident = timeIncident;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDateDone() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateDone);
        } catch (NullPointerException e) {
            return "Дата не определена";
        }
    }

    public void setDateDone(Date dateDone) {
        this.dateDone = dateDone;
    }

    public String getTimeDone() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeDone);
        } catch (NullPointerException e) {
            return "Время не определено";
        }
    }

    public void setTimeDone(Date timeDone) {
        this.timeDone = timeDone;
    }

    public String getDateClose() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateClose);
        } catch (NullPointerException e) {
            return "Дата не определена";
        }
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public String getTimeClose() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeClose);
        } catch (NullPointerException e) {
            return "Время не определено";
        }
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Statuses getStatus() {
        return status;
    }

    public void setStatus(Statuses status) {
        this.status = status;
    }

    public Typeincident getTypeIncident() {
        return typeIncident;
    }

    public void setTypeIncident(Typeincident typeIncident) {
        this.typeIncident = typeIncident;
    }

    public Users getZayavitel() {
        return zayavitel;
    }

    public void setZayavitel(Users zayavitel) {
        this.zayavitel = zayavitel;
    }

    public Users getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Users specialist) {
        this.specialist = specialist;
    }

    @XmlTransient
    public Collection<Acts> getActsCollection() {
        return actsCollection;
    }

    public void setActsCollection(Collection<Acts> actsCollection) {
        this.actsCollection = actsCollection;
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
        if (!(object instanceof Incidents)) {
            return false;
        }
        Incidents other = (Incidents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Incidents[ id=" + id + " ]";
    }
    
}
