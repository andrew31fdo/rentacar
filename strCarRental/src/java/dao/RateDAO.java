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
import model.RateType;
import model.Rate;
import model.Vehicle;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class RateDAO {

    public static void saveOrUpdateRate(Rate rate) {
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
            System.out.println(rate.getRateType().getId());
            RateType rateType = em.find(RateType.class, rate.getRateType().getId());
            rate.setRateType(rateType);
            Vehicle vehicle =  em.find(Vehicle.class,rate.getVehicle().getId());
            rate.setVehicle(vehicle);
            vehicle.getRates().add(rate);
            if (rate.getId() == null) {
                em.persist(rate);
                System.out.println("Added "+rate.getId());
            } else {
                em.merge(rate);
                System.out.println("updated"+rate.getId());
            }
            em.merge(vehicle);
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Rate> rateListVehicle(long vehicleId) {
        List<Rate> rates = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        try {
            txn.begin();
            Query query = em.createQuery("SELECT r FROM Rate r WHERE r.VEHICLE_ID =?1");
            query.setParameter(1, vehicleId);
            rates = query.getResultList();
            System.out.println("listCount:"+rates.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rates;
    }
    public static List<Rate> rateListPaging(int pageSize) {
        List<Rate> rates = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        try {
            txn.begin();
            Query query = em.createQuery("SELECT r FROM Rate r");
            query.setFirstResult(pageSize);
query.setMaxResults(pageSize);
            rates = query.getResultList();
            System.out.println("listCount:"+rates.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rates;
    }
    public static List<Rate> rateList() {
        List<Rate> rates = null;
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
            Query query = em.createQuery("SELECT c FROM Rate c");
            rates = query.getResultList();
            System.out.println("listCount:"+rates.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rates;
    }

    public static Rate listRateById(Long rateId) {
        Rate rate = null;

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
            System.out.println( "#Rate ID" + rateId);
            rate = em.find(Rate.class, rateId);
            System.out.println( "#Rate naME" + rate.getRateType());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rate;
    }

    public static String deleteRate(Long rateId) {
        Rate rate=null;
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
            rate = em.find(Rate.class, rateId);
            if (rate != null) {
                msg = rate.getRateType() + " - rate record deleted!";
                em.remove(rate);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = rate.getRateType()+ " - rate record NOT deleted!";
            Logger.getLogger(RateDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
