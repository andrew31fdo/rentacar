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
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class MakeDAO {

    public static void saveOrUpdateMake(Make make) {
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
            if (make.getId() == null) {
                em.persist(make);
                System.out.println("Addes"+make.getName());
            } else {
                em.merge(make);
                System.out.println("updated"+make.getName());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(MakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Make> makeList() {
        List<Make> makes = null;
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
            Query query = em.createQuery("SELECT c FROM Make c");
            makes = query.getResultList();
            System.out.println("listCount:"+makes.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(MakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return makes;
    }

    public static Make listMakeById(Long makeId) {
        Make make = null;

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
            System.out.println( "#Make ID" + makeId);
            make = em.find(Make.class, makeId);
            System.out.println( "#Make naME" + make.getName());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(MakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return make;
    }

    public static String deleteMake(Long makeId) {
        Make make=null;
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
            make = em.find(Make.class, makeId);
            if (make != null) {
                msg = make.getName() + " - make record deleted!";
                em.remove(make);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = make.getName() + " - make record NOT deleted!";
            Logger.getLogger(MakeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
