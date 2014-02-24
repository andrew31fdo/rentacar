/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.FeatureDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Feature;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class FeatureAction extends ActionSupport implements ModelDriven<Feature>, Preparable {

    private Feature feature =new Feature();
    private List<Feature> featureList = new ArrayList<Feature>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
        //System.out.println("feature.name:" + feature.getName()+" type:"+feature.getFeatureType().getId());
    }
    
    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("feature.name:" + feature.getName()+" type:"+feature.getFeatureType().getId());
        FeatureDAO.saveOrUpdateFeature(feature);
        addActionMessage(feature.getDescription() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        featureList = FeatureDAO.featureList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("feature.id"));
        if (cust != null) {
            String outMessage = FeatureDAO.deleteFeature(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("feature.id"));
        if (cust != null) {
            feature = FeatureDAO.listFeatureById(cust);
        }
        System.out.println( "#Feature name:" + feature.getDescription());
        return "add";
    }
    public Feature getModel() {
        return feature;
    }
}
