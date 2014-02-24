/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lis;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ronal
 */
public class servletListner implements ServletContextListener{
@PersistenceUnit(unitName="strCarRentalPU")
private EntityManagerFactory emf;
@Resource
private UserTransaction utx;

    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("emf", emf);
        ctx.setAttribute("utx", utx);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Cotext distroyed");
        ServletContext ctx = sce.getServletContext();
        ctx.removeAttribute("emf");
        ctx.removeAttribute("utx");
    }
    
}
