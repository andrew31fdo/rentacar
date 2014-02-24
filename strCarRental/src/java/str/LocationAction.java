/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.LocationDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Location;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class LocationAction extends ActionSupport implements ModelDriven<Location>, Preparable {

    private Location location =new Location();;
    private List<Location> locationList = new ArrayList<Location>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
        //System.out.println("location.name:" + location.getName()+" type:"+location.getLocationType().getId());
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setLocationList(ArrayList<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("location.name:" + location.getName()+" type:"+location.getLocationType().getId());
        LocationDAO.saveOrUpdateLocation(location);
        addActionMessage(location.getLocName() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        locationList = LocationDAO.locationList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("location.id"));
        if (cust != null) {
            String outMessage = LocationDAO.deleteLocation(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("location.id"));
        if (cust != null) {
            location = LocationDAO.listLocationById(cust);
        }
        System.out.println( "#Location name:" + location.getLocName());
        return "add";
    }
    public Location getModel() {
        return location;
    }
}
