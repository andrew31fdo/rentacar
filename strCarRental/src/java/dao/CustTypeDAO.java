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
import model.CustType;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class CustTypeDAO {

    
    public static String saveOrUpdateCustType(CustType custType) {
        String msg = "";
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
            if (custType.getId() == null) {
                em.persist(custType);
                msg = custType.getDescription()+ " -  record added!";
            } else {
                em.merge(custType);
                msg = custType.getDescription() + " -  record updated!";
            }
            txn.commit();
        } catch (Exception ex) {
            msg = custType.getDescription()+ " - record NOT updated!";
            Logger.getLogger(CustTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    public static List<CustType> custTypeList() {
        List<CustType> custTypes = null;
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
            Query query = em.createQuery("SELECT c FROM CustType c");
            custTypes = query.getResultList();
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return custTypes;
    }

    public static CustType listCustTypeById(Long custTypeId) {
        CustType custType = null;
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
            custType = em.find(CustType.class, custTypeId);
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return custType;
    }

    public static String deleteCustType(Long custTypeId) {
        CustType custType;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        String msg = "";
        try {
            txn.begin();
            custType = em.find(CustType.class, custTypeId);
            if (custType != null) {
                msg = custType.getDescription() + " -  record deleted!";
                em.remove(custType);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = custTypeId + " -  record NOT deleted!";
            Logger.getLogger(CustTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
