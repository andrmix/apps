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

    public List<Incidents> getDoneIncidents(Users user);

    public void addIncident(String title, String text, Users zayavitel, Typeincident ti);

    public void cancelIncident(Incidents incident);

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
}
