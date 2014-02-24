/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.CustTypeDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.CustType;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class CustTypeAction extends ActionSupport implements ModelDriven<CustType>, Preparable {

    private CustType custType=new CustType();
    private List<CustType> custTypeList = new ArrayList<CustType>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
    }

    public CustType getCustType() {
        return custType;
    }

    public void setCustType(CustType custType) {
        this.custType = custType;
    }

    public void setCustTypeList(ArrayList<CustType> custTypeList) {
        this.custTypeList = custTypeList;
    }

    public List<CustType> getCustTypeList() {
        return custTypeList;
    }

    public String add() {
        return "add";
    }

    public String save() {
        System.out.println("custType.name:" + custType.getDescription());
        CustTypeDAO.saveOrUpdateCustType(custType);
        addActionMessage(custType.getDescription()+ " -  record updated!");
        return "confirm";
    }

    public String viewAll() {
        custTypeList = CustTypeDAO.custTypeList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("custType.id"));
        if (cust != null) {
            String outMessage = CustTypeDAO.deleteCustType(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("custType.id"));
        if (cust != null) {
            custType = CustTypeDAO.listCustTypeById(cust);
        }
        System.out.println( "#CustType name:" + custType.getDescription());
        return "add";
    }
    
    public CustType getModel() {
        return custType;
    }
    
  
}
