/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"),
    @NamedQuery(name = "Users.findByPass", query = "SELECT u FROM Users u WHERE u.pass = :pass"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByChangePassword", query = "SELECT u FROM Users u WHERE u.changePassword = :changePassword"),

    @NamedQuery(name = "Users.findAllOrderFio", query = "SELECT u FROM Users u ORDER BY u.name"),
    @NamedQuery(name = "Users.findAllOrder", query = "SELECT u FROM Users u ORDER BY u.login"),
    @NamedQuery(name = "Users.findAllOrderEmail", query = "SELECT u FROM Users u ORDER BY u.email"),
    @NamedQuery(name = "Users.findAllOrderDepart", query = "SELECT u FROM Users u ORDER BY u.depart"),
    @NamedQuery(name = "Users.findByDepart", query = "SELECT u FROM Users u WHERE u.depart = :depart AND u.changePassword != 2 AND u.login != 'auto'"),
    @NamedQuery(name = "Users.findByDepartWithoutM", query = "SELECT u FROM Users u WHERE u.depart = :depart AND u.changePassword != 2 AND u.login != :manager"),
    @NamedQuery(name = "Users.findSearch", query = "SELECT u FROM Users u WHERE u.name like :user"),
    @NamedQuery(name = "Users.findByDepartOrderName", query = "SELECT u FROM Users u WHERE u.depart = :depart ORDER BY u.name"),
    @NamedQuery(name = "Users.findManager", query = "SELECT u FROM Users u WHERE u.changePassword = 3")
})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Недопустимый адрес электронной почты")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Column(name = "changePassword")
    private Integer changePassword;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin", orphanRemoval = true)
    private Collection<Groupuser> groupuserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usersLogin")
    private Collection<Comments> commentsCollection;
    @OneToMany(mappedBy = "zayavitel")
    private Collection<Arcincidents> arcincidentsCollection;
    @OneToMany(mappedBy = "specialist")
    private Collection<Arcincidents> arcincidentsCollection1;
    @OneToMany(mappedBy = "manager")
    private Collection<Arcincidents> arcincidentsCollection2;
    @OneToMany(mappedBy = "zayavitel")
    private Collection<Incidents> incidentsCollection;
    @OneToMany(mappedBy = "specialist")
    private Collection<Incidents> incidentsCollection1;
    @OneToMany(mappedBy = "manager")
    private Collection<Incidents> incidentsCollection2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actioner")
    private Collection<History> historyCollection;
    @JoinColumn(name = "depart", referencedColumnName = "id")
    @ManyToOne
    private Departs depart;

    public Users() {
    }

    public Users(String login) {
        this.login = login;
    }

    public Users(String login, String pass, String name, String email) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Integer changePassword) {
        this.changePassword = changePassword;
    }

    @XmlTransient
    public Collection<Groupuser> getGroupuserCollection() {
        return groupuserCollection;
    }

    public void setGroupuserCollection(Collection<Groupuser> groupuserCollection) {
        this.groupuserCollection = groupuserCollection;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
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
    public Collection<Arcincidents> getArcincidentsCollection2() {
        return arcincidentsCollection2;
    }

    public void setArcincidentsCollection2(Collection<Arcincidents> arcincidentsCollection2) {
        this.arcincidentsCollection2 = arcincidentsCollection2;
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

    @XmlTransient
    public Collection<Incidents> getIncidentsCollection2() {
        return incidentsCollection2;
    }

    public void setIncidentsCollection2(Collection<Incidents> incidentsCollection2) {
        this.incidentsCollection2 = incidentsCollection2;
    }

    @XmlTransient
    public Collection<History> getHistoryCollection() {
        return historyCollection;
    }

    public void setHistoryCollection(Collection<History> historyCollection) {
        this.historyCollection = historyCollection;
    }

    public Departs getDepart() {
        return depart;
    }

    public void setDepart(Departs depart) {
        this.depart = depart;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Users[ login=" + login + " ]";
    }

}
