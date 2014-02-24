/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.RateTypeDAO;
import dao.RateDAO;
import dao.VehicleDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Rate;
import model.RateType;
import model.Vehicle;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class RateAction extends ActionSupport implements ModelDriven<Rate>, Preparable {

    private Rate rate =new Rate();
    private List<Rate> rateList = new ArrayList<Rate>();
    private List<Rate> activeRateList = new ArrayList<Rate>();
    private List<RateType> rateTypeList;
    private List<Vehicle> vehicleList;
    private final int rowsPerPage = 10;
    private int pageNow = 1;

    /**
     * Get the value of vehicleList
     *
     * @return the value of vehicleList
     */
    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * Set the value of vehicleList
     *
     * @param vehicleList new value of vehicleList
     */
    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public void prepare() throws Exception { 
        System.out.println("prepare");
        rateTypeList = RateTypeDAO.rateTypeList();
        vehicleList = VehicleDAO.vehicleList();
    }

    public List<RateType> getRateTypeList() {
        return rateTypeList;
    }

    public void setRateTypeList(List<RateType> rateTypeList) {
        this.rateTypeList = rateTypeList;
    }
    
    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public void setRateList(ArrayList<Rate> rateList) {
        this.setRateList(rateList);
    }

    public List<Rate> getRateList() {
        return rateList;
    }

    public String add() {
        return "add";
    }

    public String save() {
        RateDAO.saveOrUpdateRate(rate);
        addActionMessage(rate.getRateType().getDescription() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        setRateList(RateDAO.rateList());
        if(pageNow<1){
            pageNow=1;
        }
        int start = (pageNow - 1) * rowsPerPage;
        int end = start+rowsPerPage;
        
        if (end>rateList.size()){
            end = rateList.size();
        }
        setActiveRateList(rateList.subList(start, end));
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("rate.id"));
        if (cust != null) {
            String outMessage = RateDAO.deleteRate(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("rate.id"));
        if (cust != null) {
            rate = RateDAO.listRateById(cust);
        }
        System.out.println( "#Rate name:" + rate.getRateType());
        return "add";
    }
    public Rate getModel() {
        return rate;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    public List<Rate> getActiveRateList() {
        return activeRateList;
    }

    public void setActiveRateList(List<Rate> activeRateList) {
        this.activeRateList = activeRateList;
    }
}
