/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.VehicleClassDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.VehicleClass;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleClassAction extends ActionSupport implements ModelDriven<VehicleClass>, Preparable {

    private VehicleClass vehicleClass =new VehicleClass();;
    private List<VehicleClass> vehicleClassList = new ArrayList<VehicleClass>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
    }
    
    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public void setVehicleClassList(ArrayList<VehicleClass> vehicleClassList) {
        this.vehicleClassList = vehicleClassList;
    }

    public List<VehicleClass> getVehicleClassList() {
        return vehicleClassList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("vehicleClass.name:" + vehicleClass.getVehicleClass()+" type:"+vehicleClass.getVehicleClassType().getId());
        VehicleClassDAO.saveOrUpdateVehicleClass(vehicleClass);
        addActionMessage(vehicleClass.getDescription() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        vehicleClassList = VehicleClassDAO.vehicleClassList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicleClass.id"));
        if (cust != null) {
            String outMessage = VehicleClassDAO.deleteVehicleClass(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicleClass.id"));
        if (cust != null) {
            vehicleClass = VehicleClassDAO.listVehicleClassById(cust);
        }
        System.out.println( "#VehicleClass name:" + vehicleClass.getDescription());
        return "add";
    }
    public VehicleClass getModel() {
        return vehicleClass;
    }
}
