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
    @NamedQuery(name = "Incidents.findByDateStatus", query = "SELECT i FROM Incidents i WHERE i.dateStatus = :dateStatus"),
    @NamedQuery(name = "Incidents.findByTimeStatus", query = "SELECT i FROM Incidents i WHERE i.timeStatus = :timeStatus"),
    @NamedQuery(name = "Incidents.findByRevisionCount", query = "SELECT i FROM Incidents i WHERE i.revisionCount = :revisionCount"),
    @NamedQuery(name = "Incidents.findByNew1", query = "SELECT i FROM Incidents i WHERE i.new1 = :new1"),
    @NamedQuery(name = "Incidents.findByAttachment", query = "SELECT i FROM Incidents i WHERE i.attachment = :attachment"),

    @NamedQuery(name = "Incidents.findOpenByUser", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status.id >= :status1 AND i.status.id <= :status2"),
    @NamedQuery(name = "Incidents.findOpenByUserOrderName", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status.id >= :status1 AND i.status.id <= :status2 ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findOpenByUserOrderDate", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status.id >= :status1 AND i.status.id <= :status2 ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findOpenByUserOrderStatus", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status.id >= :status1 AND i.status.id <= :status2 ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findOpenByUserOrderSpec", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status.id >= :status1 AND i.status.id <= :status2 ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findByUser1StatusNew", query = "SELECT i FROM Incidents i WHERE i.zayavitel = :user AND i.status = :status AND i.new1 = 1"),
    @NamedQuery(name = "Incidents.findUnallocatedNew", query = "SELECT i FROM Incidents i WHERE i.status = :status AND i.new1 = 1"),
    @NamedQuery(name = "Incidents.findAgreeNew", query = "SELECT i FROM Incidents i WHERE (i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager)) AND i.new1 = 1"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusNew", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) AND i.new1 = 1"),
    @NamedQuery(name = "Incidents.findUnallocated", query = "SELECT i FROM Incidents i WHERE i.status = :status"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderName", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderDate", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.dateStatus"),
    @NamedQuery(name = "Incidents.findUnallocatedOrderZay", query = "SELECT i FROM Incidents i WHERE i.status = :status ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findAllocated", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager)"),
    @NamedQuery(name = "Incidents.findAllocatedOrderName", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager) ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findAllocatedOrderDate", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager) ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findAllocatedOrderStatus", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager) ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findAllocatedOrderZay", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager) ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findAllocatedOrderSpec", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2 OR (i.status = :status3 AND i.zayavitel != :manager) ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findAgree", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager)"),
    @NamedQuery(name = "Incidents.findAgreeOrderName", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager) ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findAgreeOrderDate", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager) ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findAgreeOrderStatus", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager) ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findAgreeOrderZay", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager) ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findAgreeOrderSpec", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR (i.status = :status2 AND i.zayavitel = :manager) ORDER BY i.specialist"),
    @NamedQuery(name = "Incidents.findBySpecialist2Status", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2)"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusOrderName", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) ORDER BY i.title"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusOrderDate", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) ORDER BY i.dateIncident"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusOrderStatus", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) ORDER BY i.status"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusOrderZay", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) ORDER BY i.zayavitel"),
    @NamedQuery(name = "Incidents.findBySpecialist2StatusOrderDated", query = "SELECT i FROM Incidents i WHERE i.specialist = :specialist AND (i.status = :status1 OR i.status = :status2) ORDER BY i.dateStatus"),
    @NamedQuery(name = "Incidents.updateManager", query = "UPDATE Incidents i SET i.manager = :manager WHERE i.manager IS NOT NULL"),
    @NamedQuery(name = "Incidents.findNotManaged", query = "SELECT i FROM Incidents i WHERE i.status = :status1 OR i.status = :status2")
})
public class Incidents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dateIncident")
    @Temporal(TemporalType.DATE)
    private Date dateIncident;
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
    @Column(name = "dateStatus")
    @Temporal(TemporalType.DATE)
    private Date dateStatus;
    @Column(name = "timeStatus")
    @Temporal(TemporalType.TIME)
    private Date timeStatus;
    @Column(name = "revisionCount")
    private Integer revisionCount;
    @Column(name = "new")
    private Integer new1;
    @Size(max = 255)
    @Column(name = "attachment")
    private String attachment;
    @OneToMany(mappedBy = "incident")
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
    @JoinColumn(name = "manager", referencedColumnName = "login")
    @ManyToOne
    private Users manager;
    @OneToMany(mappedBy = "incident")
    private Collection<History> historyCollection;

    public Incidents() {
    }

    public Incidents(Integer id) {
        this.id = id;
    }

    public Incidents(Integer id, String title, String text) {
        this.id = id;
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
            return "";
        }
    }

    public Date getDateIncidentD() {
        return dateIncident;
    }

    public void setDateIncident(Date dateIncident) {
        this.dateIncident = dateIncident;
    }

    public String getTimeIncident() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeIncident);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public Date getTimeIncidentD() {
        return timeIncident;
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

    public String getDateStatus() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateStatus);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setDateStatus(Date dateStatus) {
        this.dateStatus = dateStatus;
    }

    public String getTimeStatus() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeStatus);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setTimeStatus(Date timeStatus) {
        this.timeStatus = timeStatus;
    }

    public Integer getRevisionCount() {
        return revisionCount;
    }

    public void setRevisionCount(Integer revisionCount) {
        this.revisionCount = revisionCount;
    }

    public Integer getNew1() {
        return new1;
    }

    public void setNew1(Integer new1) {
        this.new1 = new1;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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

    public Users getManager() {
        return manager;
    }

    public void setManager(Users manager) {
        this.manager = manager;
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
