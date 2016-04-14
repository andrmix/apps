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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "arcincidents")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arcincidents.findAll", query = "SELECT a FROM Arcincidents a"),
    @NamedQuery(name = "Arcincidents.findById", query = "SELECT a FROM Arcincidents a WHERE a.id = :id"),
    @NamedQuery(name = "Arcincidents.findByDateIncident", query = "SELECT a FROM Arcincidents a WHERE a.dateIncident = :dateIncident"),
    @NamedQuery(name = "Arcincidents.findByTimeIncident", query = "SELECT a FROM Arcincidents a WHERE a.timeIncident = :timeIncident"),
    @NamedQuery(name = "Arcincidents.findByTitle", query = "SELECT a FROM Arcincidents a WHERE a.title = :title"),
    @NamedQuery(name = "Arcincidents.findByDateClose", query = "SELECT a FROM Arcincidents a WHERE a.dateClose = :dateClose"),
    @NamedQuery(name = "Arcincidents.findByTimeClose", query = "SELECT a FROM Arcincidents a WHERE a.timeClose = :timeClose"),
    @NamedQuery(name = "Arcincidents.findByRevisionCount", query = "SELECT a FROM Arcincidents a WHERE a.revisionCount = :revisionCount"),
    @NamedQuery(name = "Arcincidents.findByNew1", query = "SELECT a FROM Arcincidents a WHERE a.new1 = :new1"),
    @NamedQuery(name = "Arcincidents.findByKb", query = "SELECT a FROM Arcincidents a WHERE a.kb = :kb"),

    @NamedQuery(name = "Arcincidents.findClosedByUser", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2"),
    @NamedQuery(name = "Arcincidents.findClosedByUserOrderName", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findClosedByUserOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findClosedByUserOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findClosedByUserOrderSpec", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findClosedByUserOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findByUser1StatusNew", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status = :status AND a.new1 = 1"),
    @NamedQuery(name = "Arcincidents.findClosedManager", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderName", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderZay", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.zayavitel"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderSpec", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findClosedManagerOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE a.status = :status1 OR a.status = :status2 OR a.status = :status3 ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findClosedManagerF", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderName", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderDate", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderZay", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.zayavitel"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderSpec", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findClosedManagerFOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerF", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderName", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderDate", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderZay", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.zayavitel"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderSpec", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findClosedCManagerFOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE (a.status = :status1 OR a.status = :status2 OR a.status = :status3) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findClosedByUserF", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend"),
    @NamedQuery(name = "Arcincidents.findClosedByUserFOrderName", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findClosedByUserFOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findClosedByUserFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findClosedByUserFOrderSpec", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findClosedByUserFOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserF", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserFOrderName", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserFOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserFOrderSpec", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.specialist"),
    @NamedQuery(name = "Arcincidents.findOClosedByUserFOrderDateClose", query = "SELECT a FROM Arcincidents a WHERE a.zayavitel = :user AND a.status.id >= :status1 AND a.status.id <= :status2 AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2Status", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2)"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusOrderName", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusOrderDatec", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusOrderZay", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) ORDER BY a.zayavitel"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusF", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusFOrderName", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusFOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusFOrderDatec", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findBySpecialist2StatusFOrderZay", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateClose >= :datebeg AND a.dateClose <= :dateend ORDER BY a.zayavitel"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusF", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusFOrderName", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.title"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusFOrderDate", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateIncident"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusFOrderStatus", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.status"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusFOrderDatec", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.dateClose"),
    @NamedQuery(name = "Arcincidents.findByOSpecialist2StatusFOrderZay", query = "SELECT a FROM Arcincidents a WHERE a.specialist = :specialist AND (a.status = :status1 OR a.status = :status2) AND a.dateIncident >= :datebeg AND a.dateIncident <= :dateend ORDER BY a.zayavitel")
})
public class Arcincidents implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
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
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "decision")
    private String decision;
    @Column(name = "dateClose")
    @Temporal(TemporalType.DATE)
    private Date dateClose;
    @Column(name = "timeClose")
    @Temporal(TemporalType.TIME)
    private Date timeClose;
    @Column(name = "revisionCount")
    private Integer revisionCount;
    @Column(name = "new")
    private Integer new1;
    @Lob
    @Size(max = 65535)
    @Column(name = "attachment")
    private String attachment;
    @Column(name = "KB")
    private Integer kb;
    @OneToMany(mappedBy = "arcincident")
    private Collection<Comments> commentsCollection;
    @OneToMany(mappedBy = "arcincident")
    private Collection<Docs> docsCollection;
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
    @OneToMany(mappedBy = "arcincident")
    private Collection<History> historyCollection;

    public Arcincidents() {
    }

    public Arcincidents(Integer id) {
        this.id = id;
    }

    public Arcincidents(Integer id, String title, String text, String decision) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.decision = decision;
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

    public String getDateClose() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateClose);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public String getTimeClose() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeClose);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setTimeClose(Date timeClose) {
        this.timeClose = timeClose;
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

    public Integer getKb() {
        return kb;
    }

    public void setKb(Integer kb) {
        this.kb = kb;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<Docs> getDocsCollection() {
        return docsCollection;
    }

    public void setDocsCollection(Collection<Docs> docsCollection) {
        this.docsCollection = docsCollection;
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
        if (!(object instanceof Arcincidents)) {
            return false;
        }
        Arcincidents other = (Arcincidents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Arcincidents[ id=" + id + " ]";
    }
    
}
