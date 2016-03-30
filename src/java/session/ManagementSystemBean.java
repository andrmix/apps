/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Arcincidents;
import entity.Comments;
import entity.Departs;
import entity.Groupuser;
import entity.History;
import entity.Incidents;
import entity.Reqs;
import entity.Statuses;
import entity.Typeincident;
import entity.Users;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManagementSystemBean implements ManagementSystemLocal {

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "helpPU")
    private EntityManager em;

    @EJB
    private GetterBeanLocal gb;

    /* add добавление =================================================================================================*/
    @Override
    public void addIncident(String title, String text, Users zayavitel,
            Typeincident ti, boolean addIncident, int id, String attachment) {
        Incidents incident;
        if (addIncident) {
            incident = new Incidents();
        } else {
            incident = findIncident(id);
        }
        incident.setDateIncident(new Date());
        incident.setTimeIncident(new Date());
        incident.setDateStatus(new Date());
        incident.setTimeStatus(new Date());
        Statuses status = em.find(Statuses.class, 1);
        incident.setStatus(status);
        incident.setTitle(title);
        incident.setText(text);
        incident.setZayavitel(zayavitel);
        incident.setTypeIncident(ti);
        incident.setAttachment(attachment);
        incident.setNew1(1);
        if (addIncident) {
            em.persist(incident);
            addHistory(incident, zayavitel, null);
            if (isAuto()) {
                autoAppoint(incident);
            }
        } else {
            em.merge(incident);
            addHistory(incident, zayavitel, "Исправление");
        }
    }

    @Override
    public void addIncidentInArc(Incidents incident) {
        Arcincidents arcincident = new Arcincidents();
        arcincident.setId(incident.getId());
        arcincident.setDateIncident(incident.getDateIncidentD());
        arcincident.setTimeIncident(incident.getTimeIncidentD());
        arcincident.setTitle(incident.getTitle());
        arcincident.setText(incident.getText());
        arcincident.setDecision(incident.getDecision());
        arcincident.setZayavitel(incident.getZayavitel());
        arcincident.setSpecialist(incident.getSpecialist());
        arcincident.setManager(incident.getManager());
        arcincident.setStatus(incident.getStatus());
        arcincident.setTypeIncident(incident.getTypeIncident());
        arcincident.setDateClose(new Date());
        arcincident.setTimeClose(new Date());
        arcincident.setRevisionCount(incident.getRevisionCount());
        arcincident.setNew1(incident.getNew1());
        arcincident.setAttachment(incident.getAttachment());
        em.persist(arcincident);
        editHAndCInArc(incident, arcincident);
        deleteIncident(incident);
    }

    @Override
    public void addTask(String title, String text, Users manager,
            Typeincident ti, boolean addIncident, int id, Users specialist) {
        Incidents incident;
        if (addIncident) {
            incident = new Incidents();
        } else {
            incident = findIncident(id);
        }
        incident.setDateIncident(new Date());
        incident.setTimeIncident(new Date());
        incident.setDateStatus(new Date());
        incident.setTimeStatus(new Date());
        Statuses status = em.find(Statuses.class, 2);
        incident.setStatus(status);
        incident.setTitle(title);
        incident.setText(text);
        incident.setZayavitel(manager);
        incident.setManager(manager);
        incident.setSpecialist(specialist);
        incident.setTypeIncident(ti);
        incident.setAttachment(null);
        incident.setNew1(1);
        if (addIncident) {
            em.persist(incident);
            addHistory(incident, manager, null);
        } else {
            em.merge(incident);
            addHistory(incident, manager, "Исправление");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addUser(String login, String fio, String email,
            Departs depart, String role, boolean addUser) {
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
            if (addUser) {
                user.setPass("12345678");
                user.setChangePassword(1);
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
    public void addSpecialist(Incidents incident, Users specialist, Users manager) {
        incident.setSpecialist(specialist);
        incident.setStatus(gb.getStatuses().get(1));//2
        incident.setManager(manager);
        incident.setNew1(1);
        em.merge(incident);
        addHistory(incident, manager, specialist.getName());
    }

    @Override
    public void addComment(String text, Users commentator, Incidents incident) {
        Comments comment = new Comments();
        comment.setText(text);
        comment.setDateComment(new Date());
        comment.setTimeComment(new Date());
        comment.setUsersLogin(commentator);
        comment.setIncident(incident);
        em.persist(comment);
    }

    @Override
    public void addHistory(Incidents incident, Users actioner, String text) {
        History history = new History();
        history.setIncident(incident);
        history.setActioner(actioner);
        history.setDateAction(new Date());
        history.setTimeAction(new Date());
        history.setStatus(incident.getStatus());
        history.setText(text);
        em.persist(history);
    }

    @Override
    public void addReq(Users specialist, String text, Incidents incident) {
        Reqs req = new Reqs();
        req.setDateReq(new Date());
        req.setTimeReq(new Date());
        req.setText(text);
        req.setSpecialist(specialist);
        Statuses status = em.find(Statuses.class, 1);
        req.setStatus(status);
        em.persist(req);
        incident.setReq(req);
        em.merge(incident);
        addHistory(incident, specialist, "Создана заявка на замену оборудования");
    }
    
    /* delete удаление ===============================================================================================*/
    @Override
    public void deleteUser(Users user) {
        Users toBeRemoved = em.merge(user);
        em.remove(toBeRemoved);
    }

    @Override
    public void deleteDepart(Departs depart) {
        Departs toBeRemoved = em.merge(depart);
        em.remove(toBeRemoved);
    }

    @Override
    public void deleteTypeincident(Typeincident typeincident) {
        Typeincident toBeRemoved = em.merge(typeincident);
        em.remove(toBeRemoved);
    }

    @Override
    public void deleteIncident(Incidents incident) {
        Incidents toBeRemoved = em.merge(incident);
        em.remove(toBeRemoved);
    }

    @Override
    public void deleteIncidentFull(Incidents incident, Arcincidents arcincident) {
        Query q = null;
        if (incident != null) {
            q = em.createNamedQuery("History.deleteOpen");
            q.setParameter("incident", incident);
        } else {
            q = em.createNamedQuery("History.deleteClosed");
            q.setParameter("arcincident", arcincident);
        }
        q.executeUpdate();
        if (incident != null) {
            q = em.createNamedQuery("Comments.deleteOpen");
            q.setParameter("incident", incident);
            q.executeUpdate();
            Incidents toBeRemoved = em.merge(incident);
            em.remove(toBeRemoved);
        } else {
            q = em.createNamedQuery("Comments.deleteClosed");
            q.setParameter("arcincident", arcincident);
            q.executeUpdate();
            Arcincidents toBeRemoved = em.merge(arcincident);
            em.remove(toBeRemoved);
        }
    }

    /* find поиск ====================================================================================================*/
    @Override
    public Users findUser(Object id) {
        return em.find(Users.class, id);
    }

    @Override
    public Incidents findIncident(Object id) {
        return em.find(Incidents.class, id);
    }

    @Override
    public Arcincidents findArcIncident(Object id) {
        return em.find(Arcincidents.class, id);
    }

    @Override
    public Typeincident findTypeIncident(Object id) {
        return em.find(Typeincident.class, id);
    }

    @Override
    public Departs findDepart(Object id) {
        return em.find(Departs.class, id);
    }

    @Override
    public Groupuser findGroupuser(Users user) {
        Query q = em.createNamedQuery("Groupuser.findByUser");
        q.setParameter("user", user);
        List resultList = q.getResultList();
        Groupuser groupuser = (Groupuser) resultList.get(0);
        return groupuser;
    }

    /* actions действия ==============================================================================================*/
    @Override
    public void cancelIncident(Incidents incident, String textp, String tstatus, boolean it, Users manager) {
        int revCount = 0;
        boolean inArc = false;
        Statuses status = null;
        if (tstatus.equals("1") || tstatus.equals("2") || tstatus.equals("3")) {
            if (it) {
                status = em.find(Statuses.class, 8);
                incident.setNew1(1);
            } else {
                status = em.find(Statuses.class, 7);
            }
            inArc = true;
            incident.setDecision(textp);
        }
        if (tstatus.equals("4")) {
            status = em.find(Statuses.class, 3);
            incident.setDateStatus(new Date());
            incident.setTimeStatus(new Date());
            if (incident.getRevisionCount() != null) {
                revCount = incident.getRevisionCount() + 1;
            } else {
                revCount = 1;
            }
            incident.setNew1(1);
            incident.setRevisionCount(revCount);
            addComment(textp, incident.getZayavitel(), incident);
        }
        incident.setStatus(status);
        if (tstatus.equals("1") && it) {
            incident.setSpecialist(manager);
            incident.setManager(manager);
        }
        em.merge(incident);

        //История
        if (tstatus.equals("1") && it) {
            addHistory(incident, manager, "Перенесен в архив");
        }
        if (tstatus.equals("1") && !it) {
            addHistory(incident, incident.getZayavitel(), null);
        }
        if (tstatus.equals("2") || tstatus.equals("3")) {
            if (it) {
                addHistory(incident, incident.getSpecialist(), "Перенесен в архив");
            } else {
                addHistory(incident, incident.getZayavitel(), null);
            }
        }
        if (tstatus.equals("4")) {
            addHistory(incident, incident.getZayavitel(), "На доработку");
        }

        if (inArc) {
            addIncidentInArc(incident);
        }
    }

    @Override
    public void inWork(Incidents incident) {
        incident.setDateStatus(new Date());
        incident.setTimeStatus(new Date());
        incident.setStatus(gb.getStatuses().get(2));//3
        em.merge(incident);
        addHistory(incident, incident.getSpecialist(), null);
    }

    @Override
    public void doneIncident(Incidents incident, String decision) {
        incident.setDateStatus(new Date());
        incident.setTimeStatus(new Date());
        incident.setStatus(gb.getStatuses().get(3));//4
        incident.setDecision(decision);
        incident.setNew1(1);
        em.merge(incident);
        addHistory(incident, incident.getSpecialist(), null);
    }

    @Override
    public void acceptIncident(Incidents incident) {
        incident.setStatus(gb.getStatuses().get(4));//5
        incident.setDateStatus(new Date());
        incident.setTimeStatus(new Date());
        incident.setNew1(1);
        em.merge(incident);
        addHistory(incident, incident.getZayavitel(), null);
        if (isAuto()) {
            Users auto = findUser("auto");
            incident.setManager(auto);
            agreeIncident(incident);
        }
    }

    @Override
    public void setNotNewIncident(Incidents incident, Arcincidents arcincident) {
        if (incident == null) {
            arcincident.setNew1(0);
            em.merge(arcincident);
        } else {
            incident.setNew1(0);
            em.merge(incident);
        }
    }

    @Override
    public boolean isTask(Users manager, Incidents incident) {
        boolean result = false;
        if (incident.getZayavitel().equals(manager)) {
            result = true;
        }
        return result;
    }

    @Override
    public void agreeIncident(Incidents incident) {
        incident.setStatus(gb.getStatuses().get(5));//6
        em.merge(incident);
        addHistory(incident, incident.getManager(), "Перенесен в архив");
        addIncidentInArc(incident);
    }

    @Override
    public void resetPassword(Users user) {
        user.setPass("12345678");
        user.setChangePassword(1);
        em.merge(user);
    }

    @Override
    public boolean isChangePassword(Users user) {
        boolean result = false;
        if (user.getChangePassword() == 1) {
            result = true;
        }
        return result;
    }

    @Override
    public void setNewPassword(Users user, String newPassword) {
        user.setPass(newPassword);
        user.setChangePassword(0);
        em.merge(user);
    }

    @Override
    public void editHAndCInArc(Incidents incident, Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("History.updateClosed");
        q.setParameter("incident", incident);
        q.setParameter("arcincident", arcincident);
        q.executeUpdate();
        q = em.createNamedQuery("Comments.updateClosed");
        q.setParameter("incident", incident);
        q.setParameter("arcincident", arcincident);
        q.executeUpdate();
    }

    @Override
    public void sendMail(Incidents incident, Arcincidents arcincident, String code, String prich) {
        Properties props;
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "10.20.30.1");
        props.put("mail.smtp.port", "25");

        String toMail = null;
        int recipCount = 0;
        List<Users> recipList = null;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("help@bank.ru", "Binbank55");
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("help@bank.ru"));
            //Заголовок письма
            message.setSubject("Обращение ID " + incident.getId() + " - " + incident.getTitle());
            //Содержимое
            switch (code) {
                case "user_new":
                    message.setText("Ваше обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " зарегистрировано (" + incident.getDateIncident()
                            + " " + incident.getTimeIncident() + ")");
                    toMail = incident.getZayavitel().getEmail();
                    recipCount = 1;
                    break;
                case "manager_new":
                    message.setText("Поступило новое обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + " - " + incident.getZayavitel().getName() + ")"
                            + " (" + incident.getDateIncident()
                            + " " + incident.getTimeIncident() + ")");
                    recipList = gb.whoIsManager();
                    recipCount = 2;
                    break;
                case "user_appoint":
                    message.setText("Ваше обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " назначено (" + incident.getDateStatus()
                            + " " + incident.getTimeStatus() + "). "
                            + "Специалист: " + incident.getSpecialist().getName());
                    toMail = incident.getZayavitel().getEmail();
                    recipCount = 1;
                    break;
                case "spec_appoint":
                    message.setText("Вам назначено обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " (" + incident.getDateStatus()
                            + " " + incident.getTimeStatus() + ")");
                    toMail = incident.getSpecialist().getEmail();
                    recipCount = 1;
                    break;
                case "done":
                    message.setText("Ваше обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " выполнено (" + incident.getDateIncident()
                            + " " + incident.getTimeIncident() + "). ");
                    toMail = incident.getZayavitel().getEmail();
                    recipCount = 1;
                    break;
                case "user_otkl":
                    message.setText("Ваше обращение (ID " + arcincident.getId()
                            + " - " + arcincident.getTitle() + ")"
                            + " отклонено (" + arcincident.getDateClose()
                            + " " + arcincident.getTimeClose() + ")"
                            + " по причине: " + arcincident.getDecision());
                    toMail = incident.getZayavitel().getEmail();
                    recipCount = 1;
                    break;
                case "spec_otkl":
                    message.setText("Обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " возвращено на доработку (" + incident.getDateStatus()
                            + " " + incident.getTimeStatus() + ")"
                            + " по причине: " + prich);
                    toMail = incident.getSpecialist().getEmail();
                    recipCount = 1;
                    break;
                case "close":
                    message.setText("Обращение (ID " + incident.getId()
                            + " - " + incident.getTitle() + ")"
                            + " закрыто (" + incident.getDateStatus()
                            + " " + incident.getTimeStatus() + ")");
                    toMail = incident.getSpecialist().getEmail();
                    recipCount = 1;
                    break;
            }
            if (recipCount == 1) {
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
                Transport.send(message);
            }
            if (recipCount > 1) {
                for (Users recipManager : recipList) {
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipManager.getEmail()));
                    Transport.send(message);
                }
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    //Auto ===================================================================================================
    private boolean isAuto() {
        boolean result = false;
        Users auto = findUser("auto");
        if (auto.getEmail().equals("on")) {
            result = true;
        }
        return result;
    }

    private void autoAppoint(Incidents incident) {
        Users specialistOnAppoint = null;
        Users auto = findUser("auto");
        specialistOnAppoint = getLowSpecialist();
        addSpecialist(incident, specialistOnAppoint, auto);
    }

    @Override
    public void autoOn(Users manager) {
        Users auto = findUser("auto");
        auto.setEmail("on");
        em.merge(auto);
        manager.setChangePassword(2);
        em.merge(manager);
        changeModer(auto);
        List<Incidents> notManaged = gb.getNotManagedIncidents();
        if (notManaged.size() > 0) {
            Iterator iterator = notManaged.iterator();
            while (iterator.hasNext()) {
                Incidents nmIncident = (Incidents) iterator.next();
                if (nmIncident.getStatus().getId() == 1) {
                    autoAppoint(nmIncident);
                }
                if (nmIncident.getStatus().getId() == 5) {
                    agreeIncident(nmIncident);
                }
            }
        }
    }

    private Users getLowSpecialist() {
        Users lowSpecialist = null;
        List<Object[]> stata = gb.getSpecialistsStatsForLow();
        if (stata.size() == 1) {
            String user = (String) stata.get(0)[3];
            lowSpecialist = findUser(user);
        }
        if (stata.size() > 1) {
            String low = (String) stata.get(0)[3];
            long lowCount = (Long) stata.get(0)[2];
            for (Object[] stat : stata) {
                if (lowCount > (Long) stat[2]) {
                    low = (String) stat[3];
                    lowCount = (Long) stat[2];
                }
            }
            lowSpecialist = findUser(low);
        }
        return lowSpecialist;
    }

    @Override
    public void comeBack(Users manager) {
        if (isAuto()) {
            Users auto = findUser("auto");
            auto.setEmail("off");
            em.merge(auto);
        } else {
            Query q = em.createNamedQuery("Users.findManager");
            List<Users> users = q.getResultList();
            Users currManager = users.get(0);
            currManager.setChangePassword(0);
            em.merge(currManager);
            Groupuser groupuser = findGroupuser(currManager);
            groupuser.setName("specialist");
            em.merge(groupuser);
        }
        manager.setChangePassword(0);
        em.merge(manager);
        changeModer(manager);
    }

    private void changeModer(Users newManager) {
        Query q = em.createNamedQuery("Incidents.updateManager");
        q.setParameter("manager", newManager);
        q.executeUpdate();
    }

    @Override
    public void doManager(Users newManager, Users oldManager) {
        Groupuser groupuser = findGroupuser(newManager);
        groupuser.setName("manager");
        em.merge(groupuser);
        newManager.setChangePassword(3);
        em.merge(newManager);
        changeModer(newManager);
        oldManager.setChangePassword(2);
        em.merge(oldManager);
    }

    @Override
    public void blockUser(Users user) {
        user.setChangePassword(2);
        em.merge(user);
    }

    @Override
    public boolean isBlockedUser(Users user) {
        boolean result = false;
        if (user.getChangePassword() == 2) {
            result = true;
        }
        return result;
    }

}
