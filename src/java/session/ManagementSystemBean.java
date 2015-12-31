/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Departs;
import entity.Incidents;
import entity.Statuses;
import entity.Typeincident;
import entity.Users;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author admin
 */
@Stateless(name = "ManagementSystem")
public class ManagementSystemBean implements ManagementSystemLocal {

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
        q.setParameter("user", "%"+searchText+"%");
        List resultList = q.getResultList();
        return resultList;
    }
    
}
