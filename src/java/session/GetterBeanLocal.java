/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Arcincidents;
import entity.Comments;
import entity.Departs;
import entity.History;
import entity.Incidents;
import entity.Posts;
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
public interface GetterBeanLocal {
    public List<Incidents> getClosedIncidents(Users user, String sort);
    public List<Incidents> getOpenIncidents(Users user, String sort);
    public List<Users> getAllUsers(String sort);
    public List<Departs> getAllDeparts(String sort);
    public List<Users> getUsersByDepart(Departs depart);
    public List<Users> getUsersSearch(String searchText);
    public List<Departs> getDepartsForEdit(Departs depart);
    public List<Departs> getDepartsSearch(String searchText);
    public List<Typeincident> getTypesIncidentSearch(String searchText);
    public List<Typeincident> getAllTypesIncident(String sort);
    public List<Incidents> getUnallocatedIncidents(String sort);
    public List<Users> getSpecialists(Users manager);
    public List<Incidents> getSpecialistOpenIncidents(Users specialist, String sort);
    public List<Incidents> getSpecialistDoneIncidents(Users specialist, String sort);
    public List<Incidents> getSpecialistClosedIncidents(Users specialist, String sort);
    public List<Typeincident> getTypesIncidentsForEdit(Typeincident typeincident);
    public List<Comments> getComments(Incidents incident, Arcincidents arcincident);
    public List<Incidents> getAllocatedIncidents(String sort, Users manager);
    public List getSpecialistsStatistics();
    public List getSpecialistsStatsForLow();
    public List getOneSpecialistsStatistics(String specialist);
    public List getYearStatistic(String year, String specialist, int period);
    public List<Incidents> getOpenIncidentsNew(Users user);
    public List<Incidents> getClosedIncidentsNew(Users user);
    public List<Incidents> getUnallocatedIncidentsNew();
    public List<Incidents> getSpecialistOpenIncidentsNew(Users specialist);
    public List<Incidents> getAgreeIncidents(Users manager, String sort);
    public List<Incidents> getAgreeIncidentsNew(Users manager);
    public List<History> getHistory(Incidents incident, Arcincidents arcincident);
    public List<Incidents> getClosedIncidentsManager(String sort);
    public List<Incidents> getClosedIncidentsMF(String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Statuses> getStatuses();
    public List<Incidents> getClosedIncidentsF(Users user, String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Incidents> getSpecialistClosedIncidentsF(Users specialist, String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Incidents> getNotManagedIncidents();
    public List getSimilarIncidents(Incidents incident);
    public List<Users> whoIsManager();
    public List<Posts> getAllPosts(String sort);
    public List<Posts> getPostsSearch(String searchText);
    public List<Posts> getPostsForEdit(Posts dpost);
}
