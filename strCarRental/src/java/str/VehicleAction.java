/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.FeatureDAO;
import dao.LocationDAO;
import dao.VehicleDAO;
import dao.VehicleModelDAO;
import dao.VehicleClassDAO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Feature;
import model.Location;
import model.Vehicle;
import model.VehicleClass;
import model.VehicleModel;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class VehicleAction extends ActionSupport implements ModelDriven<Vehicle>, Preparable {

    private Vehicle vehicle = new Vehicle();
    private List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    private List<VehicleClass> vehicleClassList;
    private List<VehicleModel> vehicleModelList;
    private List<Location> locationList;
    private List<Feature> featureList;
    private File secImage;
    private String secImageContentType;
    private String secImageFileName;

    public void prepare() throws Exception {
        System.out.println("prepare");
        vehicleClassList = VehicleClassDAO.vehicleClassList();
        vehicleModelList = VehicleModelDAO.vehicleModelList();
        locationList = LocationDAO.locationList();
        featureList = FeatureDAO.featureList();
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setSecImageFileName(String secImageFileName) {
        this.secImageFileName = secImageFileName;
    }

    public void setSecImageContentType(String secImageContentType) {
        this.secImageContentType = secImageContentType;
    }

    public void setSecImage(File secImage) {
        this.secImage = secImage;
    }

    public String getSecImageFileName() {
        return secImageFileName;
    }

    public String getSecImageContentType() {
        return secImageContentType;
    }

    public File getSecImage() {
        return secImage;
    }

    public void setFeatureList(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void setVehicleModelList(List<VehicleModel> vehicleModelList) {
        this.vehicleModelList = vehicleModelList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public void setVehicleClassList(List<VehicleClass> vehicleClassList) {
        this.vehicleClassList = vehicleClassList;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public List<VehicleClass> getVehicleClassList() {
        return vehicleClassList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public String add() {
        return "add";
    }
    
    public String pictureUpload() {
        vehicleList = VehicleDAO.vehicleList();
        return "upload";
    }

    public String save() {
        //  System.out.println("vehicle.name:" + vehicle.getVehicle()+" type:"+vehicle.getVehicleType().getId());
        VehicleDAO.saveOrUpdateVehicle(vehicle);
        addActionMessage(vehicle.getNumberPlate() + " - record updated!");
        return "confirm";
    }

    public String upload() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        //ServletContext context = request.getContextPath();
        String filePath = request.getContextPath();
                //context.getInitParameter("UploadDirectory");
        System.out.println("Server path:" + filePath);
        System.out.println("this.secImageFileName " + this.secImageFileName);
        System.out.println("source path:"+secImage.getAbsolutePath());
        File fileToCreate = new File(filePath, this.secImageFileName);
        try {
            FileUtils.copyFile(secImage, fileToCreate);
            addActionMessage("successfully added/updated.");
            System.out.println("Server path:" + filePath);
        System.out.println("this.secImageFileName " + this.secImageFileName);
            return "uploaded";
        } catch (IOException ex) {
            System.out.println("Error in Test" + ex.getMessage());
            addActionError("Error occured in Test. Please contact your administrator.\n" + ex.getMessage());
            return ERROR;
        }
    }

    public String viewAll() {
        vehicleList = VehicleDAO.vehicleList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicle.id"));
        if (cust != null) {
            String outMessage = VehicleDAO.deleteVehicle(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("vehicle.id"));
        if (cust != null) {
            vehicle = VehicleDAO.listVehicleById(cust);
        }
        System.out.println("#Vehicle name:" + vehicle.getNumberPlate());
        return "add";
    }

    public Vehicle getModel() {
        return vehicle;
    }
}
