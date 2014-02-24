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
import model.Make;
import model.VehicleModel;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleModelDAO {

    public static void saveOrUpdateVehicleModel(VehicleModel vehicleModel) {
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
            Make make = em.find(Make.class, vehicleModel.getMake().getId());
            vehicleModel.setMake(make);
            if (vehicleModel.getId() == null) {
                em.persist(vehicleModel);
                System.out.println("Added "+vehicleModel.getDescription());
            } else {
                em.merge(vehicleModel);
                System.out.println("updated"+vehicleModel.getDescription());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleModelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<VehicleModel> vehicleModelList() {
        List<VehicleModel> vehicleModels = null;
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
            Query query = em.createQuery("SELECT c FROM VehicleModel c");
            vehicleModels = query.getResultList();
            System.out.println("listCount:"+vehicleModels.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleModelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicleModels;
    }

    public static VehicleModel listVehicleModelById(Long vehicleModelId) {
        VehicleModel vehicleModel = null;

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
            System.out.println( "#VehicleModel ID" + vehicleModelId);
            vehicleModel = em.find(VehicleModel.class, vehicleModelId);
            System.out.println( "#VehicleModel naME" + vehicleModel.getDescription());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleModelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicleModel;
    }

    public static String deleteVehicleModel(Long vehicleModelId) {
        VehicleModel vehicleModel=null;
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
            vehicleModel = em.find(VehicleModel.class, vehicleModelId);
            if (vehicleModel != null) {
                msg = vehicleModel.getDescription() + " - vehicleModel record deleted!";
                em.remove(vehicleModel);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = vehicleModel.getDescription()+ " - vehicleModel record NOT deleted!";
            Logger.getLogger(VehicleModelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
