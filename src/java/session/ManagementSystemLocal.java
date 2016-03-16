/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Arcincidents;
import entity.Departs;
import entity.Groupuser;
import entity.Incidents;
import entity.Typeincident;
import entity.Users;
import javax.ejb.Local;

@Local
public interface ManagementSystemLocal {

    public void addIncident(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, int id, String attachment);

    public void addTask(String title, String text, Users zayavitel, Typeincident ti, boolean addIncident, int id, Users specialist);

    public void cancelIncident(Incidents incident, String textp, String tstatus, boolean it, Users manager);

    public Users findUser(Object id);

    public Incidents findIncident(Object id);

    public Typeincident findTypeIncident(Object id);

    public Departs findDepart(Object id);

    public void addUser(String login, String fio, String email, Departs depart, String role, boolean addUser);

    public void deleteUser(Users user);

    public Groupuser findGroupuser(Users user);

    public void deleteDepart(Departs depart);

    public void addDepart(String name, boolean addDepart, int id);

    public void addTypeIncident(String name, boolean addTypeIncident, int id);

    public void deleteTypeincident(Typeincident typeincident);

    public void addSpecialist(Incidents incident, Users specialist, Users manager);

    public void inWork(Incidents incident);

    public void doneIncident(Incidents incident, String decision);

    public void addComment(String text, Users commentator, Incidents incident);

    public void acceptIncident(Incidents incident);

    public void setNotNewIncident(Incidents incident, Arcincidents arcincident);

    public boolean isTask(Users manager, Incidents incident);

    public void agreeIncident(Incidents incident);

    public void addHistory(Incidents incident, Users actioner, String text);

    public void resetPassword(Users user);

    public boolean isChangePassword(Users user);

    public void setNewPassword(Users user, String newPassword);

    public void addIncidentInArc(Incidents incident);

    public void deleteIncident(Incidents incident);

    public Arcincidents findArcIncident(Object id);

    public void editHAndCInArc(Incidents incident, Arcincidents arcincident);

    public void deleteIncidentFull(Incidents incident, Arcincidents arcincident);

    public void sendMail(Incidents incident, Arcincidents arcincident, String code, String prich);

    public void autoOn(Users manager);

    public void comeBack(Users manager);

    public void doManager(Users newManager, Users oldManager);
    
    public void blockUser(Users user);
    
    public boolean isBlockedUser(Users user);
}
