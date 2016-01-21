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
@Table(name = "comments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findById", query = "SELECT c FROM Comments c WHERE c.id = :id"),
    @NamedQuery(name = "Comments.findByText", query = "SELECT c FROM Comments c WHERE c.text = :text"),
    @NamedQuery(name = "Comments.findByIncident", query = "SELECT c FROM Comments c WHERE c.incident = :incident"),
    @NamedQuery(name = "Comments.findByDateComment", query = "SELECT c FROM Comments c WHERE c.dateComment = :dateComment")})
public class Comments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateComment")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateComment;
    @JoinColumn(name = "incident", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Incidents incident;
    @JoinColumn(name = "usersLogin", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Users usersLogin;

    public Comments() {
    }

    public Comments(Integer id) {
        this.id = id;
    }

    public Comments(Integer id, String text, Date dateComment) {
        this.id = id;
        this.text = text;
        this.dateComment = dateComment;
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
            return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(this.dateComment);
        } catch (NullPointerException e) {
            return "Дата не определена";
        }
    }

    public void setDateComment(Date dateComment) {
        this.dateComment = dateComment;
    }

    public Incidents getIncident() {
        return incident;
    }

    public void setIncident(Incidents incident) {
        this.incident = incident;
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
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Comments[ id=" + id + " ]";
    }
    
}
