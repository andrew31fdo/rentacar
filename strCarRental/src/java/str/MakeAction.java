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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Make;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class MakeAction extends ActionSupport implements ModelDriven<Make>, Preparable {

    private Make make =new Make();;
    private List<Make> makeList = new ArrayList<Make>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
        //System.out.println("make.name:" + make.getName()+" type:"+make.getMakeType().getId());
    }
    
    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public void setMakeList(ArrayList<Make> makeList) {
        this.makeList = makeList;
    }

    public List<Make> getMakeList() {
        return makeList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("make.name:" + make.getName()+" type:"+make.getMakeType().getId());
        MakeDAO.saveOrUpdateMake(make);
        addActionMessage(make.getName() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        makeList = MakeDAO.makeList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("make.id"));
        if (cust != null) {
            String outMessage = MakeDAO.deleteMake(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("make.id"));
        if (cust != null) {
            make = MakeDAO.listMakeById(cust);
        }
        System.out.println( "#Make name:" + make.getName());
        return "add";
    }
    public Make getModel() {
        return make;
    }
}
