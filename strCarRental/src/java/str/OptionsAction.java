/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.OptionsDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Options;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class OptionsAction extends ActionSupport implements ModelDriven<Options>, Preparable {

    private Options options =new Options();;
    private List<Options> optionsList = new ArrayList<Options>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
    }
    
    public Options getoptions() {
        return options;
    }

    public void setoptions(Options options) {
        this.options = options;
    }

    public void setoptionsList(ArrayList<Options> optionsList) {
        this.optionsList = optionsList;
    }

    public List<Options> getoptionsList() {
        return optionsList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("options.name:" + options.getName()+" type:"+options.getoptionsType().getId());
        OptionsDAO.saveOrUpdateOptions(options);
        addActionMessage(options.getOptions() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        optionsList = OptionsDAO.optionList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("options.id"));
        if (cust != null) {
            String outMessage = OptionsDAO.deleteOptions(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("options.id"));
        if (cust != null) {
            options = OptionsDAO.listOptionsById(cust);
        }
        System.out.println( "#Options name:" + options.getOptions());
        return "add";
    }
    public Options getModel() {
        return options;
    }
}
