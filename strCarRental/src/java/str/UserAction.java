/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.User;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class UserAction extends ActionSupport implements ModelDriven<User>, Preparable {

    private User user =new User();;
    private List<User> userList = new ArrayList<User>();
    private List<String> roleList = new ArrayList<String>();

    public void prepare() throws Exception { 
        System.out.println("prepare");
        roleList.add("Admin");
        roleList.add("User");
        roleList.add("Staff");
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("user.name:" + user.getName()+" type:"+user.getUserType().getId());
        UserDAO.saveOrUpdateUser(user);
        addActionMessage(user.getName() + " - record updated!");
        return "confirm";
    }

    public String viewAll() {
        userList = UserDAO.userList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("user.id"));
        if (cust != null) {
            String outMessage = UserDAO.deleteUser(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("user.id"));
        if (cust != null) {
            user = UserDAO.listUserById(cust);
        }
        System.out.println( "#User name:" + user.getName());
        return "add";
    }
    public User getModel() {
        return user;
    }
}
