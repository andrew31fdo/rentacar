/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.MakeDAO;
import dao.VehicleModelDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.VehicleModel;
import model.Make;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleModelAction extends ActionSupport implements ModelDriven<VehicleModel>, Preparable {

    private VehicleModel vehicleModel =new VehicleModel();
    private List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>();
    private List<Make> makeList;

    public void prepare() throws Exception { 
        System.out.println("prepare");
        makeList = MakeDAO.makeList();
        //System.out.println("vehicleModel.name:" + vehicleModel.getName()+" type:"+vehicleModel.getVehicleModelType().getId());
    }

    public List<Make> getMakeList() {
        return makeList;
    }

    public void setMakeList(List<Make> makeList) {
        this.makeList = makeList;
    }
    
    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setVehicleModelList(ArrayList<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("vehicleModel.name:" + vehicleModel.getName()+" type:"+vehicleModel.getVehicleModelType().getId());
        VehicleModelDAO.saveOrUpdateVehicleModel(vehicleModel);
        addActionMessage(vehicleModel.getDescription() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        vehicleModelList = VehicleModelDAO.vehicleModelList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicleModel.id"));
        if (cust != null) {
            String outMessage = VehicleModelDAO.deleteVehicleModel(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicleModel.id"));
        if (cust != null) {
            vehicleModel = VehicleModelDAO.listVehicleModelById(cust);
        }
        System.out.println( "#VehicleModel name:" + vehicleModel.getDescription());
        return "add";
    }
    public VehicleModel getModel() {
        return vehicleModel;
    }
}
