/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import model.Reservation;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class ReservationDAO {

    public static void saveOrUpdateReservation(Reservation reservation) {
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
            if (reservation.getId() == null) {
                em.merge(reservation);
                System.out.println("Added "+reservation.getId());
            } else {
                em.merge(reservation);
                System.out.println("updated"+reservation.getId());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Reservation> reservationList() {
        List<Reservation> reservations = null;
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
            Query query = em.createQuery("SELECT c FROM Reservation c");
            reservations = query.getResultList();
            System.out.println("listCount:"+reservations.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }
    
    public static List<Reservation> dropOffListbyDate(int noOfDays) {
        List<Reservation> reservations = null;
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
            Date dayOne = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateOne = dateFormat.format(dayOne);
            
            Calendar dayTwo = Calendar.getInstance();
            dayTwo.add(Calendar.DAY_OF_MONTH, noOfDays);
            dateFormat.setCalendar(dayTwo);
            String dateTwo = dateFormat.format(dayTwo.getTime());
            
            String sql = "SELECT r FROM Reservation r WHERE r.endDate between '"+dateOne+"' and '"+dateTwo+"'";
            System.out.println("sql:"+sql);
            Query query = em.createQuery(sql);
            reservations = query.getResultList();
            System.out.println("listCount:"+reservations.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }
    public static List<Reservation> pickupListbyDate(int noOfDays) {
        List<Reservation> reservations = null;
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
            Date dayOne = Calendar.getInstance().getTime();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateOne = dateFormat.format(dayOne);
            
            Calendar dayTwo = Calendar.getInstance();
            dayTwo.add(Calendar.DAY_OF_MONTH, noOfDays);
            dateFormat.setCalendar(dayTwo);
            String dateTwo = dateFormat.format(dayTwo.getTime());
            
            String sql = "SELECT r FROM Reservation r WHERE SUBSTRING(r.startDate,1,10) BETWEEN '"+dateOne+"' AND '"+dateTwo+"'";
            System.out.println("sql:"+sql);
            Query query = em.createQuery(sql);
            reservations = query.getResultList();
            System.out.println("listCount:"+reservations.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservations;
    }
    
    

    public static Reservation listReservationById(Long rateId) {
        Reservation reservation = null;

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
            System.out.println( "#Reservation ID" + rateId);
            reservation = em.find(Reservation.class, rateId);
            System.out.println( "#Reservation naME" + reservation.getId());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservation;
    }

    public static String deleteReservation(Long rateId) {
        Reservation reservation=null;
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
            reservation = em.find(Reservation.class, rateId);
            if (reservation != null) {
                msg = reservation.getId() + " - reservation record deleted!";
                em.remove(reservation);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = reservation.getId()+ " - reservation record NOT deleted!";
            Logger.getLogger(ReservationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
    
}
