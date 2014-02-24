/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import model.User;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class UserDAO {

    public static void saveOrUpdateUser(User user) {
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
            if (user.getId() == null) {
                em.persist(user);
                System.out.println("Addes"+user.getName());
            } else {
                em.merge(user);
                System.out.println("updated"+user.getName());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<User> userList() {
        List<User> users = null;
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
            Query query = em.createQuery("SELECT c FROM User c");
            users = query.getResultList();
            System.out.println("listCount:"+users.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public static User ValidateUser(String userId, String password) {
        User user = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        User curUser=null;
        try {
            txn.begin();
            String sql = "SELECT u FROM User u WHERE u.userID = '"+userId+"'";
            System.out.println("sql"+sql);
            Query query = em.createQuery(sql);
            if (query.getResultList().size()>0){
                user=(User) query.getResultList().get(0);
                curUser = user;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String timeStamp = sdf.format(new Date());
                System.out.println("time"+timeStamp);
                user.setLastLogin(timeStamp);
                em.merge(user);
            }       
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return curUser;
    }

    public static User listUserById(Long userId) {
        User user = null;

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
            System.out.println( "#User ID" + userId);
            user = em.find(User.class, userId);
            System.out.println( "#User naME" + user.getName());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public static String deleteUser(Long userId) {
        User user=null;
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
            user = em.find(User.class, userId);
            if (user != null) {
                msg = user.getName() + " - user record deleted!";
                em.remove(user);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = user.getName() + " - user record NOT deleted!";
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
