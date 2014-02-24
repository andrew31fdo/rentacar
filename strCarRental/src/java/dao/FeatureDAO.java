/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletContext;
import javax.transaction.UserTransaction;
import model.Feature;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class FeatureDAO {

    public static void saveOrUpdateFeature(Feature feature) {
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
            if (feature.getId() == null) {
                em.persist(feature);
                System.out.println("Addes"+feature.getDescription());
            } else {
                em.merge(feature);
                System.out.println("updated"+feature.getDescription());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Feature> featureList() {
        List<Feature> features = null;
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
            Query query = em.createQuery("SELECT c FROM Feature c");
            features = query.getResultList();
            System.out.println("listCount:"+features.size());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return features;
    }

     public static Map<Long,String> featureMap() {
        Map<Long,String> featureMap = null;
        List<Feature> features = null;
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
            Query query = em.createQuery("SELECT c FROM Feature c");
            features = query.getResultList();
            System.out.println("listCount:"+features.size());
            for(int i=0;i<features.size();i++){
                featureMap.put(features.get(i).getId(), features.get(i).getDescription());
            }
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return featureMap;
    }

    public static Feature listFeatureById(Long featureId) {
        Feature feature = null;

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
            System.out.println( "#Feature ID" + featureId);
            feature = em.find(Feature.class, featureId);
            System.out.println( "#Feature naME" + feature.getDescription());
            txn.commit();
        } catch (Exception ex) {
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return feature;
    }

    public static String deleteFeature(Long featureId) {
        Feature feature=null;
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
            feature = em.find(Feature.class, featureId);
            if (feature != null) {
                msg = feature.getDescription() + " - feature record deleted!";
                em.remove(feature);
            }
            txn.commit();
        } catch (Exception ex) {
            msg = feature.getDescription()+ " - feature record NOT deleted!";
            Logger.getLogger(FeatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }
}
