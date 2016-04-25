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
@Table(name = "arccomments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arccomments.findAll", query = "SELECT a FROM Arccomments a"),
    @NamedQuery(name = "Arccomments.findById", query = "SELECT a FROM Arccomments a WHERE a.id = :id"),
    @NamedQuery(name = "Arccomments.findByDateComment", query = "SELECT a FROM Arccomments a WHERE a.dateComment = :dateComment"),
    @NamedQuery(name = "Arccomments.findByTimeComment", query = "SELECT a FROM Arccomments a WHERE a.timeComment = :timeComment"),

    @NamedQuery(name = "Arccomments.findByIncident", query = "SELECT a FROM Arccomments a WHERE a.arcincident = :incident")
})
public class Arccomments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateComment")
    @Temporal(TemporalType.DATE)
    private Date dateComment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "timeComment")
    @Temporal(TemporalType.TIME)
    private Date timeComment;
    @JoinColumn(name = "arcincident", referencedColumnName = "id")
    @ManyToOne
    private Arcincidents arcincident;
    @JoinColumn(name = "usersLogin", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public Arccomments() {
    }

    public Arccomments(Integer id) {
        this.id = id;
    }

    public Arccomments(Integer id, String text, Date dateComment, Date timeComment) {
        this.id = id;
        this.text = text;
        this.dateComment = dateComment;
        this.timeComment = timeComment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateComment() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(this.dateComment);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public String getTimeComment() {
        try {
            return new SimpleDateFormat("HH:mm:ss").format(this.timeComment);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void setTimeComment(Date timeComment) {
        this.timeComment = timeComment;
    }

    public Arcincidents getArcincident() {
        return arcincident;
    }

    public void setArcincident(Arcincidents arcincident) {
        this.arcincident = arcincident;
    }

    public Users getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(Users usersLogin) {
        this.usersLogin = usersLogin;
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
        if (!(object instanceof Arccomments)) {
            return false;
        }
        Arccomments other = (Arccomments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Arccomments[ id=" + id + " ]";
    }

}
