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
import model.Customer;
import model.CustType;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class CustomerDAO {

    public static void saveOrUpdateCustomer(Customer customer) {
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
            CustType customerType = em.find(CustType.class, customer.getCustType().getId());
            customer.setCustType(customerType);
            if (customer.getId() == null) {
                em.persist(customer);

            } else {
                em.merge(customer);

            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Customer> customerList() {
        List<Customer> customers = null;
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
            Query query = em.createQuery("SELECT c FROM Customer c");
            customers = query.getResultList();
            System.out.println("listCount:" + customers.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    public static Customer listCustomerById(Long customerId) {
        Customer customer = null;

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
            System.out.println("#Customer ID" + customerId);
            customer = em.find(Customer.class, customerId);

            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static Customer listCustomerByEmail(String email) {
        Customer customer = null;

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
            System.out.println("#Customer Email" + email);
            String qry = "SELECT c FROM Customer c WHERE c.email = '" + email + "'";
            System.out.println("qry:" + qry);
            Query query = em.createQuery(qry);
            //query.setParameter(1, email);
            if (query.getResultList().size() > 0) {
                customer = (Customer) query.getResultList().get(0);
                System.out.println("CustomerName:" + customer.getName1());
            }
            System.out.println("CustomerListSize:" + query.getResultList().size());
            
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public static String deleteCustomer(Long customerId) {
        Customer customer = null;
        ServletContext ctx;
        EntityManagerFactory emf;
        EntityManager em;
        UserTransaction txn;
        ctx = ServletActionContext.getServletContext();
        emf = (EntityManagerFactory) ctx.getAttribute("emf");
        System.out.println(ctx + "#" + emf);
        em = emf.createEntityManager();
        txn = (UserTransaction) ctx.getAttribute("utx");
        String msg = "";
        try {
            txn.begin();
            customer = em.find(Customer.class, customerId);
            if (customer != null) {
                msg = customer.getName1() + " - customer record deleted!";
                em.remove(customer);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = customer.getName1() + " - customer record NOT deleted!";
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
