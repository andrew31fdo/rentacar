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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.RateType;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class RateTypeAction extends ActionSupport implements ModelDriven<RateType>, Preparable {

    private RateType rateType =new RateType();
    private List<RateType> rateTypeList = new ArrayList<RateType>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
        //System.out.println("rateType.name:" + rateType.getName()+" type:"+rateType.getRateTypeType().getId());
    }
    
    public RateType getRateType() {
        return rateType;
    }

    public void setRateType(RateType rateType) {
        this.rateType = rateType;
    }

    public void setRateTypeList(ArrayList<RateType> rateTypeList) {
        this.rateTypeList = rateTypeList;
    }

    public List<RateType> getRateTypeList() {
        return rateTypeList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("rateType.name:" + rateType.getName()+" type:"+rateType.getRateTypeType().getId());
        RateTypeDAO.saveOrUpdateRateType(rateType);
        addActionMessage(rateType.getDescription() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        rateTypeList = RateTypeDAO.rateTypeList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("rateType.id"));
        if (cust != null) {
            String outMessage = RateTypeDAO.deleteRateType(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("rateType.id"));
        if (cust != null) {
            rateType = RateTypeDAO.listRateTypeById(cust);
        }
        System.out.println( "#RateType name:" + rateType.getDescription());
        return "add";
    }
    public RateType getModel() {
        return rateType;
    }
}
