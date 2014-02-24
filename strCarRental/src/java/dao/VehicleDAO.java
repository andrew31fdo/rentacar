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
import model.Vehicle;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleDAO {

    public static void saveOrUpdateVehicle(Vehicle vehicle) {
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
            if (vehicle.getId() == null) {
                em.persist(vehicle);
                System.out.println("Addes"+vehicle.getNumberPlate());
            } else {
                em.merge(vehicle);
                System.out.println("updated"+vehicle.getNumberPlate());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Vehicle> vehicleList() {
        List<Vehicle> vehicles = null;
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
            Query query = em.createQuery("SELECT c FROM Vehicle c order by c.numberPlate");
            vehicles = query.getResultList();
            System.out.println("listCount:"+vehicles.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;
    }
    public static List<Vehicle> vehicleList(String stDate,String enDate,Long clsID) {
        List<Vehicle> vehicles = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        String clsDescription = VehicleClassDAO.listVehicleClassById(clsID).getDescription();
        try {
            txn.begin();
            
            String sql = " SELECT v FROM Vehicle v WHERE ";
            String sqlWhere = " v.vehicleClass.id = " + clsID +" AND ";
            sql+= (clsDescription.substring(0, 1).equals("="))?"":sqlWhere;
            sql+= " v.id NOT IN (SELECT r.vehicle.id FROM Reservation r WHERE r.startDate <= '"+stDate+"' AND r.endDate >= '"+enDate+"')";
           // sql= " SELECT r FROM Reservation r WHERE r.startdate <= '"+stDate+"' AND r.enddate >= '"+enDate+"'";
            System.out.println("sql:"+sql);
            Query query = em.createQuery(sql);
            vehicles = query.getResultList();
            System.out.println("listCount:"+vehicles.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicles;
    }

    public static Vehicle listVehicleById(Long vehicleId) {
        Vehicle vehicle = null;

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
            System.out.println( "#Vehicle ID" + vehicleId);
            vehicle = em.find(Vehicle.class, vehicleId);
            System.out.println( "#Vehicle naME" + vehicle.getNumberPlate());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vehicle;
    }

    public static String deleteVehicle(Long vehicleId) {
        Vehicle vehicle=null;
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
            vehicle = em.find(Vehicle.class, vehicleId);
            if (vehicle != null) {
                msg = vehicle.getNumberPlate() + " - vehicle record deleted!";
                em.remove(vehicle);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = vehicle.getNumberPlate() + " - vehicle record NOT deleted!";
            Logger.getLogger(VehicleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
