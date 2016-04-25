/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "arcdocs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arcdocs.findAll", query = "SELECT a FROM Arcdocs a"),
    @NamedQuery(name = "Arcdocs.findById", query = "SELECT a FROM Arcdocs a WHERE a.id = :id"),
    @NamedQuery(name = "Arcdocs.findByDateDoc", query = "SELECT a FROM Arcdocs a WHERE a.dateDoc = :dateDoc"),
    @NamedQuery(name = "Arcdocs.findByTimeDoc", query = "SELECT a FROM Arcdocs a WHERE a.timeDoc = :timeDoc"),
    @NamedQuery(name = "Arcdocs.findByZamenaOut", query = "SELECT a FROM Arcdocs a WHERE a.zamenaOut = :zamenaOut"),
    @NamedQuery(name = "Arcdocs.findByZamenaIn", query = "SELECT a FROM Arcdocs a WHERE a.zamenaIn = :zamenaIn"),
    @NamedQuery(name = "Arcdocs.findByTypeDoc", query = "SELECT a FROM Arcdocs a WHERE a.typeDoc = :typeDoc"),

    @NamedQuery(name = "Arcdocs.findByIncident", query = "SELECT a FROM Arcdocs a WHERE a.arcincident = :incident"),
    @NamedQuery(name = "Arcdocs.findByArcIncidentReq", query = "SELECT a FROM Arcdocs a WHERE a.arcincident = :arcincident AND a.typeDoc = 1"),
    @NamedQuery(name = "Arcdocs.findByArcIncidentAD", query = "SELECT a FROM Arcdocs a WHERE a.arcincident = :arcincident AND a.typeDoc = 2")
})
public class Arcdocs implements Serializable {

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
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "cause")
    private String cause;
    @Size(max = 10000)
    @Column(name = "zamena_out")
    private String zamenaOut;
    @Size(max = 10000)
    @Column(name = "zamena_in")
    private String zamenaIn;
    @Column(name = "typeDoc")
    private Integer typeDoc;
    @JoinColumn(name = "arcincident", referencedColumnName = "id")
    @ManyToOne
    private Arcincidents arcincident;
    @JoinColumn(name = "specialist", referencedColumnName = "login")
    @ManyToOne
    private Users specialist;
    @JoinColumn(name = "komis1", referencedColumnName = "login")
    @ManyToOne
    private Users komis1;
    @JoinColumn(name = "komis2", referencedColumnName = "login")
    @ManyToOne
    private Users komis2;

    public Arcdocs() {
    }

    public Arcdocs(Integer id) {
        this.id = id;
    }

    public Arcdocs(Integer id, String cause) {
        this.id = id;
        this.cause = cause;
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

    public Integer getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(Integer typeDoc) {
        this.typeDoc = typeDoc;
    }

    public Arcincidents getArcincident() {
        return arcincident;
    }

    public void setArcincident(Arcincidents arcincident) {
        this.arcincident = arcincident;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arcdocs)) {
            return false;
        }
        Arcdocs other = (Arcdocs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Arcdocs[ id=" + id + " ]";
    }

}
