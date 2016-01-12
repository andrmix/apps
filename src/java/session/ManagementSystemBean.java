/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Departs;
import entity.Groupuser;
import entity.Incidents;
import entity.Statuses;
import entity.Typeincident;
import entity.Users;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless(name = "ManagementSystem")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManagementSystemBean implements ManagementSystemLocal {

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "helpPU")
    private EntityManager em;

    @Override
    public List<Incidents> getIncidentsByUser(Users user, Statuses status) {
        Query q = em.createNamedQuery("Incidents.findByUser1Status");
        q.setParameter("user", user);
        q.setParameter("status", status);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getClosedIncidents(Users user) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findByUser2Status");
        q.setParameter("user", user);
        q.setParameter("status1", statuses.get(1));
        q.setParameter("status2", statuses.get(3));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getOpenIncidents(Users user) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findByUser2Status");
        q.setParameter("user", user);
        q.setParameter("status1", statuses.get(4));
        q.setParameter("status2", statuses.get(0));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getDoneIncidents(Users user) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findByUser1Status");
        q.setParameter("user", user);
        q.setParameter("status", statuses.get(2));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void addIncident(String title, String text, Users zayavitel, Typeincident ti) {
        Incidents incident = new Incidents();
        incident.setDateIncident(new Date());
        Statuses status = em.find(Statuses.class, 1);
        incident.setStatus(status);
        incident.setTitle(title);
        incident.setText(text);
        incident.setZayavitel(zayavitel);
        incident.setTypeIncident(ti);
        em.persist(incident);
    }

    @Override
    public void cancelIncident(Incidents incident) {
        Statuses status = em.find(Statuses.class, 2);
        incident.setStatus(status);
        em.merge(incident);
    }

    @Override
    public List<Statuses> getStatuses() {
        List resultList = em.createNamedQuery("Statuses.findAllOrder").getResultList();
        return resultList;
    }

    @Override
    public List<Typeincident> getTypesOfincidents() {
        Query q = em.createQuery("SELECT t FROM Typeincident t");
        List<Typeincident> l = q.getResultList();
        return l;
    }

    @Override
    public Users findUser(Object id) {
        return em.find(Users.class, id);
    }

    @Override
    public Incidents findIncident(Object id) {
        return em.find(Incidents.class, id);
    }

    @Override
    public Typeincident findTypeIncident(Object id) {
        return em.find(Typeincident.class, id);
    }

    @Override
    public List<Users> getAllUsers() {
        List resultList = em.createNamedQuery("Users.findAllOrder").getResultList();
        return resultList;
    }

    @Override
    public List<Departs> getAllDeparts() {
        List resultList = em.createNamedQuery("Departs.findAll").getResultList();
        return resultList;
    }

    @Override
    public Departs findDepart(Object id) {
        return em.find(Departs.class, id);
    }

    @Override
    public List<Users> getUsersByDepart(Departs depart) {
        Query q = em.createNamedQuery("Users.findByDepart");
        q.setParameter("depart", depart);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Users> getUsersSearch(String searchText) {
        Query q = em.createNamedQuery("Users.findSearch");
        q.setParameter("user", "%" + searchText + "%");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUser(String login, String pass,
            String fio, String email, Departs depart,
            String role, boolean addUser) {
        try {
            Users user;
            if (addUser) {
                user = new Users();
            } else {
                user = findUser(login);
            }
            user.setDepart(depart);
            user.setEmail(email);
            user.setLogin(login);
            user.setName(fio);
            user.setPass(pass);
            if (addUser) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            Groupuser groupuser;
            if (addUser) {
                groupuser = new Groupuser();
            } else {
                groupuser = findGroupuser(user);
            }
            groupuser.setName(role);
            groupuser.setUsersLogin(user);
            if (addUser) {
                em.persist(groupuser);
            } else {
                em.merge(groupuser);
            }
        } catch (Exception e) {
            context.setRollbackOnly();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Users user) {
        Users toBeRemoved = em.merge(user);
        em.remove(toBeRemoved);
    }

    @Override
    public Groupuser findGroupuser(Users user) {
        Query q = em.createNamedQuery("Groupuser.findByUser");
        q.setParameter("user", user);
        List resultList = q.getResultList();
        Groupuser groupuser = (Groupuser) resultList.get(0);
        return groupuser;
    }

    @Override
    public List<Departs> getDepartsForEdit(Departs depart) {
        List resultList = em.createNamedQuery("Departs.findAll").getResultList();
        Departs departA = null, departB = null;
        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()) {
            departA = (Departs) iterator.next();
            if (depart.equals(departA)) {
                departB = departA;
                iterator.remove();
            }
        }
        resultList.add(departB);
        return resultList;
    }

    @Override
    public void deleteDepart(Departs depart) {
        Departs toBeRemoved = em.merge(depart);
        em.remove(toBeRemoved);
    }

}
