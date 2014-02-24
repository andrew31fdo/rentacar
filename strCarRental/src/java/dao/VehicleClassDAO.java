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
import model.VehicleClass;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleClassDAO {

    public static void saveOrUpdateVehicleClass(VehicleClass vehicleClass) {
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
            if (vehicleClass.getId() == null) {
                em.persist(vehicleClass);
                System.out.println("Addes"+vehicleClass.getDescription());
            } else {
                em.merge(vehicleClass);
                System.out.println("updated"+vehicleClass.getDescription());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<VehicleClass> vehicleClassList() {
        List<VehicleClass> vehicleClasss = null;
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
            Query query = em.createQuery("SELECT c FROM VehicleClass c ORDER BY c.description");
            vehicleClasss = query.getResultList();
            System.out.println("listCount:"+vehicleClasss.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicleClasss;
    }

    public static VehicleClass listVehicleClassById(Long vehicleClassId) {
        VehicleClass vehicleClass = null;

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
            System.out.println( "#VehicleClass ID" + vehicleClassId);
            vehicleClass = em.find(VehicleClass.class, vehicleClassId);
            System.out.println( "#VehicleClass naME" + vehicleClass.getDescription());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicleClass;
    }

    public static String deleteVehicleClass(Long vehicleClassId) {
        VehicleClass vehicleClass=null;
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
            vehicleClass = em.find(VehicleClass.class, vehicleClassId);
            if (vehicleClass != null) {
                msg = vehicleClass.getDescription() + " - vehicleClass record deleted!";
                em.remove(vehicleClass);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = vehicleClass.getDescription() + " - vehicleClass record NOT deleted!";
            Logger.getLogger(VehicleClassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
