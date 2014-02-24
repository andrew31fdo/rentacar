/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import java.util.Date;
import java.util.Map;
import model.User;

/**
 *
 * @author Ronal
 */
public class ControlAction extends ActionSupport {

    private String userId;
    private String passwd;

    public ControlAction() {
    }

    public ControlAction(String userId, String passwd) {
        this.userId = userId;
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String login() throws Exception {
        System.out.println("log pro1");
        User user = UserDAO.ValidateUser(userId, passwd);
        
        if (user != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("logined", "true");
            session.put("context", new Date());
            session.put("logUser", user.getUserID());
            session.put("lastLogin", user.getLastLogin());
            session.put("logUserName", user.getName());
            return "wel";
        } else {
            addActionError("Invalid User ID or Password");
            return "login";
        }
    }

    public String logout() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.remove("logined");
        session.remove("context");
        session.remove("logUser");
        session.remove("lastLogin");
        session.remove("logUserName");
        return "login";
    }

    public String welcome() {
        return "wel";
    }

    public String logPage() {
        return "login";
    }
}
