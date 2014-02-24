/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import model.Location;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class LocationDAO {

    public static void saveOrUpdateLocation(Location location) {
        try {
            ServletContext ctx;
            EntityManagerFactory emf;
            EntityManager em;
            UserTransaction txn;
            ctx = ServletActionContext.getServletContext();
            emf = (EntityManagerFactory) ctx.getAttribute("emf");
            System.out.println(ctx + "#" + emf);
            em = emf.createEntityManager();
            txn = (UserTransaction) ctx.getAttribute("utx");

            txn.begin();
            if (location.getId() == null) {
                em.persist(location);
                System.out.println("Addes"+location.getLocName());
            } else {
                em.merge(location);
                System.out.println("updated"+location.getLocName());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Location> locationList() {
        List<Location> locations = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        try {
            txn.begin();
            Query query = em.createQuery("SELECT c FROM Location c");
            locations = query.getResultList();
            System.out.println("listCount:"+locations.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locations;
    }

    public static Location listLocationById(Long locationId) {
        Location location = null;

        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        try {
            txn.begin();
            System.out.println( "#Location ID" + locationId);
            location = em.find(Location.class, locationId);
            System.out.println( "#Location naME" + location.getLocName());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return location;
    }

    public static String deleteLocation(Long locationId) {
        Location location=null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        String msg="";
        try {
            txn.begin();
            location = em.find(Location.class, locationId);
            if (location != null) {
                msg = location.getLocName() + " - location record deleted!";
                em.remove(location);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = location.getLocName() + " - location record NOT deleted!";
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
