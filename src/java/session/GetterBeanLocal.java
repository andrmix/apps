/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Arccomments;
import entity.Arcdocs;
import entity.Archistory;
import entity.Arcincidents;
import entity.Comments;
import entity.Departs;
import entity.Docs;
import entity.Groupuser;
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
    public List<Arcincidents> getClosedIncidents(Users user, String sort);
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
    public List<Arcincidents> getSpecialistClosedIncidents(Users specialist, String sort);
    public List<Typeincident> getTypesIncidentsForEdit(Typeincident typeincident);
    public List<Comments> getComments(Incidents incident);
    public List<Incidents> getAllocatedIncidents(String sort, Users manager);
    public List getSpecialistsStatistics();
    public List getSpecialistsStatsForLow();
    public List getOneSpecialistsStatistics(String specialist);
    public List getYearStatistic(String year, String specialist, int period);
    public List<Incidents> getOpenIncidentsNew(Users user);
    public List<Arcincidents> getClosedIncidentsNew(Users user);
    public List<Incidents> getUnallocatedIncidentsNew();
    public List<Incidents> getSpecialistOpenIncidentsNew(Users specialist);
    public List<Incidents> getAgreeIncidents(Users manager, String sort);
    public List<Incidents> getAgreeIncidentsNew(Users manager);
    public List<History> getHistory(Incidents incident);
    public List<Arcincidents> getClosedIncidentsManager(String sort);
    public List<Arcincidents> getClosedIncidentsMF(String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Statuses> getStatuses();
    public List<Arcincidents> getClosedIncidentsF(Users user, String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Arcincidents> getSpecialistClosedIncidentsF(Users specialist, String sort, String dateBeg, String dateEnd, String filterParam);
    public List<Incidents> getNotManagedIncidents();
    public List getSimilarIncidents(Incidents incident);
    public Users whoIsManager();
    public List<Posts> getAllPosts(String sort);
    public List<Posts> getPostsSearch(String searchText);
    public List<Posts> getPostsForEdit(Posts dpost);
    public List<Users> getUsersForEdit(Users user);
    public List<Docs> getDocs(Incidents incident);
    public List<Docs> getReqs(Incidents incident);
    public List<Docs> getActDone(Incidents incident);
    public List<Arcincidents> getArcincidents(boolean task);
    public List<Arccomments> getArccomments(Arcincidents arcincident);
    public List<Archistory> getArchistory(Arcincidents arcincident);
    public List<Arcdocs> getArcdocs(Arcincidents arcincident);
    public List<Arcdocs> getArcReqs(Arcincidents arcincident);
    public List<Arcdocs> getArcActDone(Arcincidents arcincident);
    public List<Incidents> getIncidents(boolean task);
    public List getIncidentsStatistics();
    public List getIncidentsStatisticsMonth(String year, String month);
    public List getSpecialistsStatisticsMonth(String year, String month);
    public List getSpecIncidentsStatisticsMonth(String year, String month, Users specialist);
    public List getSpecIncidentsStatistics(Users specialist);
}
