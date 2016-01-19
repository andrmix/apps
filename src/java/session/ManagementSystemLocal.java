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
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author admin
 */
@Local
public interface ManagementSystemLocal {

    public List<Incidents> getIncidentsByUser(Users user, Statuses status);

    public List<Incidents> getClosedIncidents(Users user);

    public List<Incidents> getOpenIncidents(Users user);

    public void addIncident(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, int id);

    public void cancelIncident(Incidents incident, String textp, String tstatus);

    public List<Statuses> getStatuses();

    public List<Typeincident> getTypesOfincidents();

    public Users findUser(Object id);

    public Incidents findIncident(Object id);

    public Typeincident findTypeIncident(Object id);

    public List<Users> getAllUsers();

    public List<Departs> getAllDeparts();

    public Departs findDepart(Object id);

    public List<Users> getUsersByDepart(Departs depart);

    public List<Users> getUsersSearch(String searchText);

    public void addUser(String login, String pass, String fio, String email, Departs depart, String role, boolean addUser);

    public void deleteUser(Users user);

    public Groupuser findGroupuser(Users user);

    public List<Departs> getDepartsForEdit(Departs depart);

    public void deleteDepart(Departs depart);

    public List<Departs> getDepartsSearch(String searchText);

    public void addDepart(String name, boolean addDepart, int id);

    public void addTypeIncident(String name, boolean addTypeIncident, int id);

    public List<Typeincident> getTypesIncidentSearch(String searchText);

    public void deleteTypeincident(Typeincident typeincident);

    public List<Typeincident> getAllTypesIncident();

    public List<Incidents> getUnallocatedIncidents();

    public List<Users> getSpecialists();

    public void addSpecialist(Incidents incident, Users specialist);

    public void inWork(Incidents incident);

    public List<Incidents> getSpecialistOpenIncidents(Users specialist);

    public void doneIncident(Incidents incident, String decision);

    public List<Incidents> getSpecialistDoneIncidents(Users specialist);

    public List<Incidents> getSpecialistClosedIncidents(Users specialist);

    public List<Typeincident> getTypesIncidentsForEdit(Typeincident typeincident);
}
