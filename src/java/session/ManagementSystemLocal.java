/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Comments;
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

    public List<Incidents> getClosedIncidents(Users user, String sort);

    public List<Incidents> getOpenIncidents(Users user, String sort);

    public void addIncident(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, int id);

    public void cancelIncident(Incidents incident, String textp, String tstatus, Users user, boolean it);

    public List<Statuses> getStatuses();

    public Users findUser(Object id);

    public Incidents findIncident(Object id);

    public Typeincident findTypeIncident(Object id);

    public List<Users> getAllUsers(String sort);

    public List<Departs> getAllDeparts(String sort);

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

    public List<Typeincident> getAllTypesIncident(String sort);

    public List<Incidents> getUnallocatedIncidents(String sort);

    public List<Users> getSpecialists(String sort);

    public void addSpecialist(Incidents incident, Users specialist);

    public void inWork(Incidents incident);

    public List<Incidents> getSpecialistOpenIncidents(Users specialist);

    public void doneIncident(Incidents incident, String decision);

    public List<Incidents> getSpecialistDoneIncidents(Users specialist);

    public List<Incidents> getSpecialistClosedIncidents(Users specialist);

    public List<Typeincident> getTypesIncidentsForEdit(Typeincident typeincident);

    public void addComment(String text, Users commentator, Incidents incident);

    public List<Comments> getComments(Incidents incident);

    public void acceptIncident(Incidents incident);

    public List<Incidents> getAllocatedIncidents(String sort);

    public List getSpecialistsStatistics();
    
    public List getOneSpecialistsStatistics(String specialist);
    
    public List getYearStatistic(String year, String specialist, int period);
}
