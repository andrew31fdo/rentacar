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
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class RateTypeDAO {

    public static void saveOrUpdateRateType(RateType rateType) {
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
            if (rateType.getId() == null) {
                em.persist(rateType);
                System.out.println("Addes"+rateType.getDescription());
            } else {
                em.merge(rateType);
                System.out.println("updated"+rateType.getDescription());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<RateType> rateTypeList() {
        List<RateType> rateTypes = null;
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
            Query query = em.createQuery("SELECT c FROM RateType c");
            rateTypes = query.getResultList();
            System.out.println("listCount:"+rateTypes.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rateTypes;
    }

    public static RateType listRateTypeById(Long rateTypeId) {
        RateType rateType = null;

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
            System.out.println( "#RateType ID" + rateTypeId);
            rateType = em.find(RateType.class, rateTypeId);
            System.out.println( "#RateType naME" + rateType.getDescription());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(RateTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rateType;
    }

    public static String deleteRateType(Long rateTypeId) {
        RateType rateType=null;
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
            rateType = em.find(RateType.class, rateTypeId);
            if (rateType != null) {
                msg = rateType.getDescription() + " - rateType record deleted!";
                em.remove(rateType);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = rateType.getDescription()+ " - rateType record NOT deleted!";
            Logger.getLogger(RateTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
