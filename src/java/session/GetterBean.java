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
import filter.Porter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GetterBean implements GetterBeanLocal {

    @Resource
    private SessionContext context;

    @PersistenceContext(unitName = "helpPU")
    private EntityManager em;

    @EJB
    private ManagementSystemLocal ms;

    /* users ===========================================================================================================*/
    @Override
    public List<Users> getAllUsers(String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Users.findAllOrder");
                break;
            case "fio":
                q = em.createNamedQuery("Users.findAllOrderFio");
                break;
            case "login":
                q = em.createNamedQuery("Users.findAllOrder");
                break;
            case "email":
                q = em.createNamedQuery("Users.findAllOrderEmail");
                break;
            case "depart":
                q = em.createNamedQuery("Users.findAllOrderDepart");
                break;
            case "post":
                q = em.createNamedQuery("Users.findAllOrderPost");
                break;
        }
        List resultList = q.getResultList();
        return resultList;
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
    public List<Users> getSpecialists(Users manager) {
        Query q = null;
        if (manager != null) {
            q = em.createNamedQuery("Users.findByDepartWithoutM");
            q.setParameter("manager", manager.getLogin());
        } else {
            q = em.createNamedQuery("Users.findByDepart");
        }
        Departs depart = ms.findDepart(3);
        q.setParameter("depart", depart);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public Users whoIsManager() {
        List<Users> users = null;
        Query q = em.createNamedQuery("Groupuser.findByName");
        q.setParameter("name", "manager");
        List<Groupuser> resultList = q.getResultList();
        Users manager = resultList.get(0).getUsersLogin();
        return manager;
    }

    @Override
    public List<Users> getUsersForEdit(Users user) {
        List resultList = em.createNamedQuery("Users.findAll").getResultList();
        Users tiA = null, tiB = null;
        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()) {
            tiA = (Users) iterator.next();
            if (user.equals(tiA)) {
                tiB = tiA;
                iterator.remove();
            }
        }
        resultList.add(tiB);
        return resultList;
    }

    /* incidents ===========================================================================================================*/
    /* open ===========================================================================================================*/
    @Override
    public List<Incidents> getOpenIncidents(Users user, String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findOpenByUser");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findOpenByUserOrderName");
                break;
            case "date":
                q = em.createNamedQuery("Incidents.findOpenByUserOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Incidents.findOpenByUserOrderStatus");
                break;
            case "spec":
                q = em.createNamedQuery("Incidents.findOpenByUserOrderSpec");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findOpenByUserOrderId");
                break;
        }
        q.setParameter("user", user);
        q.setParameter("status1", ((Statuses) statuses.get(0)).getId());//1-2-3-4
        q.setParameter("status2", ((Statuses) statuses.get(3)).getId());
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getOpenIncidentsNew(Users user) {
        List statuses = getStatuses();
        Query q = null;
        q = em.createNamedQuery("Incidents.findByUser1StatusNew");
        q.setParameter("user", user);
        q.setParameter("status", statuses.get(3));//4
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getSpecialistOpenIncidents(Users specialist, String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findBySpecialist2Status");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findBySpecialist2StatusOrderName");
                break;
            case "date":
                q = em.createNamedQuery("Incidents.findBySpecialist2StatusOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Incidents.findBySpecialist2StatusOrderStatus");
                break;
            case "zay":
                q = em.createNamedQuery("Incidents.findBySpecialist2StatusOrderZay");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findBySpecialist2StatusOrderId");
                break;
        }
        q.setParameter("specialist", specialist);
        q.setParameter("status1", statuses.get(1));//2
        q.setParameter("status2", statuses.get(2));//3
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getSpecialistOpenIncidentsNew(Users specialist) {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findBySpecialist2StatusNew");
        q.setParameter("specialist", specialist);
        q.setParameter("status1", statuses.get(1));//2
        q.setParameter("status2", statuses.get(2));//3
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getNotManagedIncidents() {
        List statuses = getStatuses();
        Query q = em.createNamedQuery("Incidents.findNotManaged");
        q.setParameter("status1", statuses.get(0));//1
        //q.setParameter("status2", statuses.get(4));//5
        List resultList = q.getResultList();
        return resultList;
    }

    /* done ===========================================================================================================*/
    @Override
    public List<Incidents> getSpecialistDoneIncidents(Users specialist, String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findBySpecialistDone");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findBySpecialistDoneOrderName");
                break;
            case "dateo":
                q = em.createNamedQuery("Incidents.findBySpecialistDoneOrderDate");
                break;
            case "dated":
                q = em.createNamedQuery("Incidents.findBySpecialistDoneOrderDated");
                break;
            case "zay":
                q = em.createNamedQuery("Incidents.findBySpecialistDoneOrderZay");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findBySpecialistDoneOrderId");
                break;
        }
        q.setParameter("specialist", specialist);
        q.setParameter("status", statuses.get(3));//4
        List resultList = q.getResultList();
        return resultList;
    }

    /* closed ===========================================================================================================*/
    @Override
    public List<Arcincidents> getClosedIncidents(Users user, String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Arcincidents.findClosedByUser");
                break;
            case "name":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderName");
                break;
            case "dateo":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderStatus");
                break;
            case "spec":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderSpec");
                break;
            case "datec":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderDateClose");
                break;
            case "id":
                q = em.createNamedQuery("Arcincidents.findClosedByUserOrderId");
                break;
        }
        q.setParameter("user", user);
        q.setParameter("status1", ((Statuses) statuses.get(4)).getId());//5 - 6 - 7
        q.setParameter("status2", ((Statuses) statuses.get(6)).getId());
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getClosedIncidentsF(Users user, String sort, String dateBeg, String dateEnd, String filterParam) {
        List statuses = getStatuses();
        Query q = null;
        Date dBeg = null;
        Date dEnd = null;
        if (filterParam.equals("close")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderStatus");
                    break;
                case "spec":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderSpec");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderDateClose");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findClosedByUserFOrderId");
                    break;
            }
        }
        if (filterParam.equals("open")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderStatus");
                    break;
                case "spec":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderSpec");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderDateClose");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findOClosedByUserFOrderId");
                    break;
            }
        }
        q.setParameter("user", user);
        q.setParameter("status1", ((Statuses) statuses.get(4)).getId());//5 - 6 - 7
        q.setParameter("status2", ((Statuses) statuses.get(6)).getId());
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            dBeg = format.parse(dateBeg);
            dEnd = format.parse(dateEnd);
        } catch (ParseException ex) {
            Logger.getLogger(GetterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        q.setParameter("datebeg", dBeg);
        q.setParameter("dateend", dEnd);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getClosedIncidentsNew(Users user) {
        List statuses = getStatuses();
        Query q = null;
        q = em.createNamedQuery("Arcincidents.findByUser1StatusNew");
        q.setParameter("user", user);
        q.setParameter("status", statuses.get(6));//7
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getClosedIncidentsManager(String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Arcincidents.findClosedManager");
                break;
            case "name":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderName");
                break;
            case "dateo":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderStatus");
                break;
            case "zay":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderZay");
                break;
            case "spec":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderSpec");
                break;
            case "datec":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderDateClose");
                break;
            case "id":
                q = em.createNamedQuery("Arcincidents.findClosedManagerOrderId");
                break;
        }
        q.setParameter("status1", statuses.get(4));//5
        q.setParameter("status2", statuses.get(6));//7
        q.setParameter("status3", statuses.get(5));//6
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getClosedIncidentsMF(String sort, String dateBeg, String dateEnd, String filterParam) {
        List statuses = getStatuses();
        Query q = null;
        Date dBeg = null;
        Date dEnd = null;
        if (filterParam.equals("close")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderStatus");
                    break;
                case "zay":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderZay");
                    break;
                case "spec":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderSpec");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderDateClose");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findClosedManagerFOrderId");
                    break;
            }
        }
        if (filterParam.equals("open")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderStatus");
                    break;
                case "zay":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderZay");
                    break;
                case "spec":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderSpec");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderDateClose");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findClosedCManagerFOrderId");
                    break;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            dBeg = format.parse(dateBeg);
            dEnd = format.parse(dateEnd);
        } catch (ParseException ex) {
            Logger.getLogger(GetterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        q.setParameter("datebeg", dBeg);
        q.setParameter("dateend", dEnd);
        q.setParameter("status1", statuses.get(4));//5
        q.setParameter("status2", statuses.get(6));//7
        q.setParameter("status3", statuses.get(5));//6
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getSpecialistClosedIncidents(Users specialist, String sort) {
        List statuses = getStatuses();
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2Status");
                break;
            case "name":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderName");
                break;
            case "dateo":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderStatus");
                break;
            case "datec":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderDatec");
                break;
            case "zay":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderZay");
                break;
            case "id":
                q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusOrderId");
                break;
        }
        q.setParameter("specialist", specialist);
        q.setParameter("status1", statuses.get(4));//5
        q.setParameter("status2", statuses.get(6));//7
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcincidents> getSpecialistClosedIncidentsF(Users specialist, String sort, String dateBeg, String dateEnd, String filterParam) {
        List statuses = getStatuses();
        Query q = null;
        if (filterParam.equals("close")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderStatus");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderDatec");
                    break;
                case "zay":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderZay");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findBySpecialist2StatusFOrderId");
                    break;
            }
        }
        if (filterParam.equals("open")) {
            switch (sort) {
                case "none":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusF");
                    break;
                case "name":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderName");
                    break;
                case "dateo":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderDate");
                    break;
                case "status":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderStatus");
                    break;
                case "datec":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderDatec");
                    break;
                case "zay":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderZay");
                    break;
                case "id":
                    q = em.createNamedQuery("Arcincidents.findByOSpecialist2StatusFOrderId");
                    break;
            }
        }
        Date dBeg = null;
        Date dEnd = null;
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            dBeg = format.parse(dateBeg);
            dEnd = format.parse(dateEnd);
        } catch (ParseException ex) {
            Logger.getLogger(GetterBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        q.setParameter("datebeg", dBeg);
        q.setParameter("dateend", dEnd);
        q.setParameter("specialist", specialist);
        q.setParameter("status1", statuses.get(4));//5
        q.setParameter("status2", statuses.get(6));//7
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List getSimilarIncidents(Incidents incident) {
        List<String> zagList = new ArrayList<String>();
        List resList = new ArrayList();
        Porter porter = new Porter();
        boolean ne = false;
        String queryText = "SELECT * FROM incidents.arcincidents as a WHERE a.KB = 1 AND (a.text like '%";
        String queryFrag = " OR a.text like '%";

        String incidentText = incident.getText();
        String resa = incidentText.replaceAll("\\,|\\.|\\-|\\(|\\)|\\+|\\/|\\*|\\:|\\;|\\\\|\\[|\\]|\\{|\\}|\\?|\\!|\\=|\\&|\\^|\\%|\\#|\\№|\\'|\"|\\$", "");
        resa = resa.trim();
        while (resa.contains("  ")) {
            resa = resa.replaceAll("  ", " ");
        }
        String[] mas = resa.split(" ");
        for (String elMas : mas) {
            if (elMas.equals("не")) {
                ne = true;
            } else {
                if (!elMas.equals(" ")) {
                    if (ne) {
                        zagList.add(porter.stem("не " + elMas));
                        ne = false;
                    } else {
                        zagList.add(porter.stem(elMas));
                    }
                }
            }
        }

        queryText = queryText + zagList.get(0) + "%'";

        for (int i = 1; i < zagList.size(); i++) {
            queryText = queryText + queryFrag + zagList.get(i) + "%'";
        }

        queryText = queryText + ");";
        Query q = em.createNativeQuery(queryText);
        resList = null;
        resList = q.getResultList();
        return resList;
    }

    @Override
    public List<Arcincidents> getArcincidents(boolean task) {
        Query q = null;
        q = em.createNamedQuery("Arcincidents.findByCount");
        if (task) {
            q.setParameter("id", "T%");
        } else {
            q.setParameter("id", "A%");
        }
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getIncidents(boolean task) {
        Query q = null;
        q = em.createNamedQuery("Incidents.findByCount");
        if (task) {
            q.setParameter("id", "T%");
        } else {
            q.setParameter("id", "A%");
        }
        List resultList = q.getResultList();
        return resultList;
    }

    /* allocated ===========================================================================================================*/
    @Override
    public List<Incidents> getAllocatedIncidents(String sort, Users manager) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findAllocated");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findAllocatedOrderName");
                break;
            case "date":
                q = em.createNamedQuery("Incidents.findAllocatedOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Incidents.findAllocatedOrderStatus");
                break;
            case "zay":
                q = em.createNamedQuery("Incidents.findAllocatedOrderZay");
                break;
            case "spec":
                q = em.createNamedQuery("Incidents.findAllocatedOrderSpec");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findAllocatedOrderId");
                break;
        }
        List statuses = getStatuses();
        q.setParameter("status1", statuses.get(1));//2
        q.setParameter("status2", statuses.get(2));//3
        q.setParameter("status3", statuses.get(3));//4
        q.setParameter("manager", manager);
        List resultList = q.getResultList();
        return resultList;
    }

    /* unallocated ===========================================================================================================*/
    @Override
    public List<Incidents> getUnallocatedIncidents(String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findUnallocated");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findUnallocatedOrderName");
                break;
            case "date":
                q = em.createNamedQuery("Incidents.findUnallocatedOrderDate");
                break;
            case "zay":
                q = em.createNamedQuery("Incidents.findUnallocatedOrderZay");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findUnallocatedOrderId");
                break;
        }
        List statuses = getStatuses();
        q.setParameter("status", statuses.get(0));//1
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getUnallocatedIncidentsNew() {
        Query q = null;
        q = em.createNamedQuery("Incidents.findUnallocatedNew");
        List statuses = getStatuses();
        q.setParameter("status", statuses.get(0));//1
        List resultList = q.getResultList();
        return resultList;
    }

    /* agree ===========================================================================================================*/
    @Override
    public List<Incidents> getAgreeIncidents(Users manager, String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Incidents.findAgree");
                break;
            case "name":
                q = em.createNamedQuery("Incidents.findAgreeOrderName");
                break;
            case "date":
                q = em.createNamedQuery("Incidents.findAgreeOrderDate");
                break;
            case "status":
                q = em.createNamedQuery("Incidents.findAgreeOrderStatus");
                break;
            case "zay":
                q = em.createNamedQuery("Incidents.findAgreeOrderZay");
                break;
            case "spec":
                q = em.createNamedQuery("Incidents.findAgreeOrderSpec");
                break;
            case "id":
                q = em.createNamedQuery("Incidents.findAgreeOrderId");
                break;
        }
        List statuses = getStatuses();
        q.setParameter("status", statuses.get(3));//4
        q.setParameter("manager", manager);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Incidents> getAgreeIncidentsNew(Users manager) {
        Query q = null;
        q = em.createNamedQuery("Incidents.findAgreeNew");
        List statuses = getStatuses();
        q.setParameter("status", statuses.get(3));//4
        q.setParameter("manager", manager);
        List resultList = q.getResultList();
        return resultList;
    }

    /* statuses ===========================================================================================================*/
    @Override
    public List<Statuses> getStatuses() {
        List resultList = em.createNamedQuery("Statuses.findAllOrder").getResultList();
        return resultList;
    }

    /* departs ===========================================================================================================*/
    @Override
    public List<Departs> getAllDeparts(String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Departs.findAll");
                break;
            case "name":
                q = em.createNamedQuery("Departs.findAllOrderName");
                break;
        }
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Departs> getDepartsSearch(String searchText) {
        Query q = em.createNamedQuery("Departs.findSearch");
        q.setParameter("depart", "%" + searchText + "%");
        List resultList = q.getResultList();
        return resultList;
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

    /* dposts ===========================================================================================================*/
    @Override
    public List<Posts> getAllPosts(String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Posts.findAll");
                break;
            case "name":
                q = em.createNamedQuery("Posts.findAllOrderName");
                break;
        }
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Posts> getPostsSearch(String searchText) {
        Query q = em.createNamedQuery("Posts.findSearch");
        q.setParameter("post", "%" + searchText + "%");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Posts> getPostsForEdit(Posts dpost) {
        List resultList = em.createNamedQuery("Posts.findAll").getResultList();
        Posts postA = null, postB = null;
        Iterator iterator = resultList.iterator();
        while (iterator.hasNext()) {
            postA = (Posts) iterator.next();
            if (dpost.equals(postA)) {
                postB = postA;
                iterator.remove();
            }
        }
        resultList.add(postB);
        return resultList;
    }

    /* types incidents ===========================================================================================================*/
    @Override
    public List<Typeincident> getAllTypesIncident(String sort) {
        Query q = null;
        switch (sort) {
            case "none":
                q = em.createNamedQuery("Typeincident.findAll");
                break;
            case "name":
                q = em.createNamedQuery("Typeincident.findAllOrderName");
                break;
        }
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Typeincident> getTypesIncidentSearch(String searchText) {
        Query q = em.createNamedQuery("Typeincident.findSearch");
        q.setParameter("typeincident", "%" + searchText + "%");
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

    /* comments ===========================================================================================================*/
    @Override
    public List<Comments> getComments(Incidents incident) {
        Query q = null;
        q = em.createNamedQuery("Comments.findByIncident");
        q.setParameter("incident", incident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arccomments> getArccomments(Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("Arccomments.findByIncident");
        q.setParameter("incident", arcincident);
        List resultList = q.getResultList();
        return resultList;
    }

    /* docs ===========================================================================================================*/
    @Override
    public List<Docs> getDocs(Incidents incident) {
        Query q = null;
        q = em.createNamedQuery("Docs.findByIncident");
        q.setParameter("incident", incident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcdocs> getArcdocs(Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("Arcdocs.findByIncident");
        q.setParameter("incident", arcincident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Docs> getReqs(Incidents incident) {
        Query q = null;
        q = em.createNamedQuery("Docs.findByIncidentReq");
        q.setParameter("incident", incident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcdocs> getArcReqs(Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("Arcdocs.findByArcIncidentReq");
        q.setParameter("arcincident", arcincident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Docs> getActDone(Incidents incident) {
        Query q = null;
        q = em.createNamedQuery("Docs.findByIncidentAD");
        q.setParameter("incident", incident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Arcdocs> getArcActDone(Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("Arcdocs.findByArcIncidentAD");
        q.setParameter("arcincident", arcincident);
        List resultList = q.getResultList();
        return resultList;
    }

    /* history ===========================================================================================================*/
    @Override
    public List<History> getHistory(Incidents incident) {
        Query q = null;
        q = em.createNamedQuery("History.findByIncident");
        q.setParameter("incident", incident);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List<Archistory> getArchistory(Arcincidents arcincident) {
        Query q = null;
        q = em.createNamedQuery("Archistory.findByIncident");
        q.setParameter("incident", arcincident);
        List resultList = q.getResultList();
        return resultList;
    }

    /* statistic ===========================================================================================================*/
    @Override
    public List getSpecialistsStatistics() {
        Query q = em.createNativeQuery("SELECT u.name, "
                + "tab_act_on_today.cnt AS cnt_act_on_today, "
                + "tab_end_today.cnt AS cnt_end_today, "
                + "tab_end_month.cnt AS cnt_end_month, "
                + "tab_end_all.cnt AS cnt_end_all, "
                + "u.login "
                + "FROM users AS u "
                + "LEFT OUTER JOIN ("
                + "SELECT i.specialist AS spec, COUNT(i.specialist) AS cnt "
                + "FROM incidents AS i "
                + "WHERE (i.status = 2 OR i.status = 3 OR i.status = 4) "
                + "AND i.specialist IS NOT NULL "
                + "GROUP BY i.specialist"
                + ") AS tab_act_on_today ON u.login = tab_act_on_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "AND a.dateClose = CURDATE() "
                + "GROUP BY a.specialist"
                + ") AS tab_end_today ON u.login = tab_end_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "AND (a.dateClose <= CURDATE() AND a.dateClose >= DATE(ADDDATE(NOW(), INTERVAL -(DAY(CURDATE()-1)) DAY))) "
                + "GROUP BY a.specialist"
                + ") AS tab_end_month ON u.login = tab_end_month.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "GROUP BY a.specialist"
                + ") AS tab_end_all ON u.login = tab_end_all.spec "
                + "WHERE u.depart = 3  AND u.login != 'auto' "
                + " GROUP BY u.name;");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List getSpecialistsStatsForLow() {
        Query q = em.createNativeQuery("SELECT "
                + "tab_act_on_today.cnt AS cnt_act_on_today, "
                + "tab_end_month.cnt AS cnt_end_month, "
                + "IFNULL(tab_act_on_today.cnt, 0) + IFNULL(tab_end_month.cnt, 0) as summa, "
                + "u.login "
                + "FROM users AS u "
                + "LEFT OUTER JOIN ("
                + "SELECT i.specialist AS spec, COUNT(i.specialist) AS cnt "
                + "FROM incidents AS i "
                + "WHERE (i.status = 2 OR i.status = 3 OR i.status = 4) "
                + "AND i.specialist IS NOT NULL "
                + "GROUP BY i.specialist"
                + ") AS tab_act_on_today ON u.login = tab_act_on_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "AND (a.dateClose <= CURDATE() AND a.dateClose >= DATE(ADDDATE(NOW(), INTERVAL -(DAY(CURDATE()-1)) DAY))) "
                + "GROUP BY a.specialist"
                + ") AS tab_end_month ON u.login = tab_end_month.spec "
                + "WHERE u.depart = 3  AND u.login != 'auto' "
                + "AND u.changePassword < 2 "
                + "GROUP BY u.name;");
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List getOneSpecialistsStatistics(String specialist) {
        Query q = em.createNativeQuery("SELECT u.name,"
                + "tab_act_on_today.cnt AS cnt_act_on_today,"
                + "tab_end_today.cnt AS cnt_end_today,"
                + "tab_end_month.cnt AS cnt_end_month,"
                + "tab_end_all.cnt AS cnt_end_all,"
                + "tab_cancel_today.cnt AS cnt_cancel_today,"
                + "tab_cancel_month.cnt AS cnt_cancel_month,"
                + "tab_cancel_all.cnt AS cnt_cancel_all,"
                + "u.login "
                + "FROM users AS u "
                + "LEFT OUTER JOIN ("
                + "SELECT i.specialist AS spec, COUNT(i.specialist) AS cnt "
                + "FROM incidents AS i "
                + "WHERE (i.status = 2 OR i.status = 3 OR i.status = 4) "
                + "AND i.specialist IS NOT NULL "
                + "GROUP BY i.specialist"
                + ") AS tab_act_on_today ON u.login = tab_act_on_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "AND a.dateClose = CURDATE() "
                + "GROUP BY a.specialist"
                + ") AS tab_end_today ON u.login = tab_end_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "AND (a.dateClose <= CURDATE() AND a.dateClose >= DATE(ADDDATE(NOW(), INTERVAL -(DAY(CURDATE()-1)) DAY))) "
                + "GROUP BY a.specialist"
                + ") AS tab_end_month ON u.login = tab_end_month.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.specialist IS NOT NULL "
                + "GROUP BY a.specialist"
                + ") AS tab_end_all ON u.login = tab_end_all.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 7) "
                + "AND a.specialist IS NOT NULL "
                + "AND a.dateClose = CURDATE() "
                + "GROUP BY a.specialist"
                + ") AS tab_cancel_today ON u.login = tab_cancel_today.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 7) "
                + "AND a.specialist IS NOT NULL "
                + "AND (a.dateClose <= CURDATE() AND a.dateClose >= DATE(ADDDATE(NOW(), INTERVAL -(DAY(CURDATE()-1)) DAY))) "
                + "GROUP BY a.specialist"
                + ") AS tab_cancel_month ON u.login = tab_cancel_month.spec "
                + "LEFT OUTER JOIN ("
                + "SELECT a.specialist AS spec, COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 7) "
                + "AND a.specialist IS NOT NULL "
                + "GROUP BY a.specialist"
                + ") AS tab_cancel_all ON u.login = tab_cancel_all.spec "
                + "WHERE u.login = ? AND u.login != 'auto' "
                + "GROUP BY u.name;");
        q.setParameter(1, specialist);
        List resultList = q.getResultList();
        return resultList;
    }

    @Override
    public List getYearStatistic(String year, String specialist, int period) {
        List resList = new ArrayList();
        Query q = em.createNativeQuery("SELECT COUNT(a.specialist) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE a.specialist IS NOT NULL AND a.specialist = ? "
                + "AND a.status = 5 AND year(a.dateClose) = ? "
                + "AND month(a.dateClose) = ? "
                + "GROUP BY a.specialist;");
        q.setParameter(1, specialist);
        q.setParameter(2, year);
        for (int i = period; i < period + 6; i++) {
            q.setParameter(3, Integer.toString(i));
            if (!q.getResultList().isEmpty()) {
                resList.add(q.getResultList().get(0));
            } else {
                resList.add("0");
            }
        }
        return resList;
    }

    @Override
    public List getIncidentsStatistics() {
        Query q = em.createNativeQuery("SELECT u.id, u.name, tab_end_all.cnt AS cnt_end_all "
                + "FROM typeincident AS u "
                + "LEFT OUTER JOIN ("
                + "SELECT a.typeIncident AS ti, COUNT(a.typeIncident) AS cnt "
                + "FROM arcincidents AS a "
                + "WHERE (a.status = 5) "
                + "AND a.typeIncident IS NOT NULL "
                + "GROUP BY a.typeIncident"
                + ") AS tab_end_all ON u.id = tab_end_all.ti "
                + "GROUP BY u.name;");
        List resultList = q.getResultList();
        return resultList;
    }

}
