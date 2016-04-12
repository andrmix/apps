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
@Table(name = "docs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docs.findAll", query = "SELECT d FROM Docs d"),
    @NamedQuery(name = "Docs.findById", query = "SELECT d FROM Docs d WHERE d.id = :id"),
    @NamedQuery(name = "Docs.findByDateDoc", query = "SELECT d FROM Docs d WHERE d.dateDoc = :dateDoc"),
    @NamedQuery(name = "Docs.findByTimeDoc", query = "SELECT d FROM Docs d WHERE d.timeDoc = :timeDoc"),
    @NamedQuery(name = "Docs.findByCause", query = "SELECT d FROM Docs d WHERE d.cause = :cause"),
    @NamedQuery(name = "Docs.findByZamenaOut", query = "SELECT d FROM Docs d WHERE d.zamenaOut = :zamenaOut"),
    @NamedQuery(name = "Docs.findByZamenaIn", query = "SELECT d FROM Docs d WHERE d.zamenaIn = :zamenaIn"),
    @NamedQuery(name = "Docs.findByText", query = "SELECT d FROM Docs d WHERE d.text = :text"),

    @NamedQuery(name = "Docs.deleteOpen", query = "DELETE FROM Docs d WHERE d.id = :id")
})
public class Docs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dateDoc")
    @Temporal(TemporalType.DATE)
    private Date dateDoc;
    @Column(name = "timeDoc")
    @Temporal(TemporalType.TIME)
    private Date timeDoc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cause")
    private String cause;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "zamena_out")
    private String zamenaOut;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "zamena_in")
    private String zamenaIn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "specialist", referencedColumnName = "login")
    @ManyToOne
    private Users specialist;
    @JoinColumn(name = "komis1", referencedColumnName = "login")
    @ManyToOne
    private Users komis1;
    @JoinColumn(name = "komis2", referencedColumnName = "login")
    @ManyToOne
    private Users komis2;
    @OneToMany(mappedBy = "req")
    private Collection<Arcincidents> arcincidentsCollection;
    @OneToMany(mappedBy = "actDone")
    private Collection<Arcincidents> arcincidentsCollection1;
    @OneToMany(mappedBy = "req")
    private Collection<Incidents> incidentsCollection;
    @OneToMany(mappedBy = "actDone")
    private Collection<Incidents> incidentsCollection1;

    public Docs() {
    }

    public Docs(Integer id) {
        this.id = id;
    }

    public Docs(Integer id, String cause, String zamenaOut, String zamenaIn, String text) {
        this.id = id;
        this.cause = cause;
        this.zamenaOut = zamenaOut;
        this.zamenaIn = zamenaIn;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDoc() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateDoc);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }

    public String getTimeDoc() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeDoc);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setTimeDoc(Date timeDoc) {
        this.timeDoc = timeDoc;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getZamenaOut() {
        return zamenaOut;
    }

    public void setZamenaOut(String zamenaOut) {
        this.zamenaOut = zamenaOut;
    }

    public String getZamenaIn() {
        return zamenaIn;
    }

    public void setZamenaIn(String zamenaIn) {
        this.zamenaIn = zamenaIn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Users getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Users specialist) {
        this.specialist = specialist;
    }

    public Users getKomis1() {
        return komis1;
    }

    public void setKomis1(Users komis1) {
        this.komis1 = komis1;
    }

    public Users getKomis2() {
        return komis2;
    }

    public void setKomis2(Users komis2) {
        this.komis2 = komis2;
    }

    @XmlTransient
    public Collection<Arcincidents> getArcincidentsCollection() {
        return arcincidentsCollection;
    }

    public void setArcincidentsCollection(Collection<Arcincidents> arcincidentsCollection) {
        this.arcincidentsCollection = arcincidentsCollection;
    }

    @XmlTransient
    public Collection<Arcincidents> getArcincidentsCollection1() {
        return arcincidentsCollection1;
    }

    public void setArcincidentsCollection1(Collection<Arcincidents> arcincidentsCollection1) {
        this.arcincidentsCollection1 = arcincidentsCollection1;
    }

    @XmlTransient
    public Collection<Incidents> getIncidentsCollection() {
        return incidentsCollection;
    }

    public void setIncidentsCollection(Collection<Incidents> incidentsCollection) {
        this.incidentsCollection = incidentsCollection;
    }

    @XmlTransient
    public Collection<Incidents> getIncidentsCollection1() {
        return incidentsCollection1;
    }

    public void setIncidentsCollection1(Collection<Incidents> incidentsCollection1) {
        this.incidentsCollection1 = incidentsCollection1;
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
        if (!(object instanceof Docs)) {
            return false;
        }
        Docs other = (Docs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Docs[ id=" + id + " ]";
    }

}
