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
import model.Options;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class OptionsDAO {

    public static void saveOrUpdateOptions(Options options) {
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
            if (options.getId() == null) {
                em.persist(options);
                System.out.println("Addes"+options.getOptions());
            } else {
                em.merge(options);
                System.out.println("updated"+options.getOptions());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Options> optionList() {
        List<Options> options = null;
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
            Query query = em.createQuery("SELECT c FROM Options c");
            options = query.getResultList();
            System.out.println("listCount:"+options.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return options;
    }

    public static Options listOptionsById(Long optionsId) {
        Options options = null;

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
            System.out.println( "#Options ID" + optionsId);
            options = em.find(Options.class, optionsId);
            System.out.println( "#Options naME" + options.getOptions());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return options;
    }

    public static String deleteOptions(Long optionsId) {
        Options options=null;
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
            options = em.find(Options.class, optionsId);
            if (options != null) {
                msg = options.getOptions() + " - options record deleted!";
                em.remove(options);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = options.getOptions() + " - options record NOT deleted!";
            Logger.getLogger(OptionsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
