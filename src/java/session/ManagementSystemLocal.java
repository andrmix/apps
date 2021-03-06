/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Arcdocs;
import entity.Arcincidents;
import entity.Departs;
import entity.Docs;
import entity.Groupuser;
import entity.Incidents;
import entity.Posts;
import entity.Typeincident;
import entity.Users;
import javax.ejb.Local;

@Local
public interface ManagementSystemLocal {

    public String addIncident(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, String id, String attachment);

    public String addTask(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, String id, Users specialist);

    public void cancelIncident(Incidents incident, String textp, String tstatus, boolean it, Users manager);

    public Users findUser(Object id);

    public Incidents findIncident(Object id);

    public Typeincident findTypeIncident(Object id);

    public Departs findDepart(Object id);

    public void addUser(String login, String fio, String email, Departs depart, String role, boolean addUser, Posts dpost);

    public void deleteUser(Users user);

    public Groupuser findGroupuser(Users user);

    public void deleteDepart(Departs depart);

    public void addDepart(String name, boolean addDepart, int id);

    public void addTypeIncident(String name, boolean addTypeIncident, int id);

    public void deleteTypeincident(Typeincident typeincident);

    public void addSpecialist(Incidents incident, Users specialist, Users manager);

    public void inWork(Incidents incident);

    public void doneIncident(Incidents incident, String decision, boolean kb);

    public void addComment(String text, Users commentator, Incidents incident);

    public void acceptIncident(Incidents incident);

    public void setNotNewIncident(Incidents incident, Arcincidents arcincident);

    public boolean isTask(Users manager, Incidents incident);

    public void addHistory(Incidents incident, Users actioner, String text);

    public void resetPassword(Users user);

    public boolean isChangePassword(Users user);

    public void setNewPassword(Users user, String newPassword);

    public void addIncidentInArc(Incidents incident);

    public void deleteIncident(Incidents incident);

    public Arcincidents findArcIncident(Object id);

    public void editHDAndCInArc(Incidents incident, Arcincidents arcincident);

    public void deleteIncidentFull(Incidents incident, Arcincidents arcincident);

    public void sendMail(Incidents incident, Arcincidents arcincident, String code, String prich);

    public void autoOn(Users manager);

    public void comeBack(Users manager);

    public void doManager(Users newManager, Users oldManager);
    
    public void blockUser(Users user);
    
    public boolean isBlockedUser(Users user);
    
    public int addReq(Users specialist, String cause, Incidents incident, Users komis1, Users komis2, String zamenaIn, String zamenaOut, boolean zEdit, Docs reqa);
    
    public Posts findPost(Object id);
    
    public void addPost(String name, boolean addPost, int id);
    
    public void deletePost(Posts dpost);
    
    public Docs findDoc(Object id);
    
    public void deleteDoc(Docs doc);
    
    public void addActDone(Incidents incident);
    
    public Arcdocs findArcDoc(Object id);
}
