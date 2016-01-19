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
        Query q = em.createNamedQuery("Incidents.findByUser3Status");
        q.setParameter("user", user);
        q.setParameter("status1", statuses.get(1));
        q.setParameter("status2", statuses.get(3));
        q.setParameter("status3", statuses.get(6));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getOpenIncidents(Users user) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findByUser4Status");
        q.setParameter("user", user);
        q.setParameter("status1", statuses.get(4));
        q.setParameter("status2", statuses.get(0));
        q.setParameter("status3", statuses.get(5));
        q.setParameter("status4", statuses.get(2));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void addIncident(String title, String text, Users zayavitel,
            Typeincident ti, boolean addIncident, int id) {
        Incidents incident;
        if (addIncident) {
            incident = new Incidents();
        } else {
            incident = findIncident(id);
        }
        incident.setDateIncident(new Date());
        Statuses status = em.find(Statuses.class, 1);
        incident.setStatus(status);
        incident.setTitle(title);
        incident.setText(text);
        incident.setZayavitel(zayavitel);
        incident.setTypeIncident(ti);
        if (addIncident) {
            em.persist(incident);
        } else {
            em.merge(incident);
        }
    }

    @Override
    public void cancelIncident(Incidents incident, String textp, String tstatus) {
        Statuses status = null;
        if (tstatus.equals("1") || tstatus.equals("6")) {
            status = em.find(Statuses.class, 2);
            incident.setDateClose(new Date());
            incident.setDecision(textp);
        }
        if (tstatus.equals("3")) {
            status = em.find(Statuses.class, 5);
            incident.setDateDone(new Date());
            incident.setDecision(textp);
        }
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

    @Override
    public List<Departs> getDepartsSearch(String searchText) {
        Query q = em.createNamedQuery("Departs.findSearch");
        q.setParameter("depart", "%" + searchText + "%");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void addDepart(String name, boolean addDepart, int id) {
        Departs depart;
        if (addDepart) {
            depart = new Departs();
        } else {
            depart = findDepart(id);
        }
        depart.setName(name);
        if (addDepart) {
            em.persist(depart);
        } else {
            em.merge(depart);
        }
    }

    @Override
    public void addTypeIncident(String name, boolean addTypeIncident, int id) {
        Typeincident typeincident;
        if (addTypeIncident) {
            typeincident = new Typeincident();
        } else {
            typeincident = findTypeIncident(id);
        }
        typeincident.setName(name);
        if (addTypeIncident) {
            em.persist(typeincident);
        } else {
            em.merge(typeincident);
        }
    }

    @Override
    public List<Typeincident> getTypesIncidentSearch(String searchText) {
        Query q = em.createNamedQuery("Typeincident.findSearch");
        q.setParameter("typeincident", "%" + searchText + "%");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void deleteTypeincident(Typeincident typeincident) {
        Typeincident toBeRemoved = em.merge(typeincident);
        em.remove(toBeRemoved);
    }

    @Override
    public List<Typeincident> getAllTypesIncident() {
        List resultList = em.createNamedQuery("Typeincident.findAll").getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getUnallocatedIncidents() {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findUnallocated");
        q.setParameter("status", statuses.get(0));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Users> getSpecialists() {
        Query q = em.createNamedQuery("Users.findByDepart");
        Departs depart = findDepart(4);
        q.setParameter("depart", depart);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void addSpecialist(Incidents incident, Users specialist) {
        incident.setSpecialist(specialist);
        incident.setStatus(getStatuses().get(5));
        em.merge(incident);
    }

    @Override
    public void inWork(Incidents incident) {
        incident.setStatus(getStatuses().get(4));
        em.merge(incident);
    }

    @Override
    public List<Incidents> getSpecialistOpenIncidents(Users specialist) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findBySpecialist2Status");
        q.setParameter("specialist", specialist);
        q.setParameter("status1", statuses.get(5));
        q.setParameter("status2", statuses.get(4));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public void doneIncident(Incidents incident, String decision) {
        incident.setDateDone(new Date());
        incident.setStatus(getStatuses().get(2));
        incident.setDecision(decision);
        em.merge(incident);
    }

    @Override
    public List<Incidents> getSpecialistDoneIncidents(Users specialist) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findBySpecialist1Status");
        q.setParameter("specialist", specialist);
        q.setParameter("status", statuses.get(2));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getSpecialistClosedIncidents(Users specialist) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findBySpecialist1Status");
        q.setParameter("specialist", specialist);
        q.setParameter("status", statuses.get(3));
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Typeincident> getTypesIncidentsForEdit(Typeincident typeincident) {
        List resultList = em.createNamedQuery("Typeincident.findAll").getResultList();
        Typeincident tiA = null, tiB = null;
        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()) {
            tiA = (Typeincident) iterator.next();
            if (typeincident.equals(tiA)) {
                tiB = tiA;
                iterator.remove();
            }
        }
        resultList.add(tiB);
        return resultList;
    }

}
