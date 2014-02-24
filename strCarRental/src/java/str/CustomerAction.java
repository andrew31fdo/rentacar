/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package str;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import dao.CustomerDAO;
import dao.CustTypeDAO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Customer;
import model.CustType;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ronal
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer>, Preparable {

    private Customer customer =new Customer();;
    private List<Customer> customerList = new ArrayList<Customer>();
    private List<CustType> custTypeList;
    

    public void prepare() throws Exception { 
        System.out.println("prepare");
        
        custTypeList= CustTypeDAO.custTypeList();
        //System.out.println("customer.name:" + customer.getName()+" type:"+customer.getCustType().getId());
    }

    public void setCustTypeList(List<CustType> custTypeList) {
        this.custTypeList = custTypeList;
    }

    public List<CustType> getCustTypeList() {
        return custTypeList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public String add() {
        return "add";
    }

    public String save() {
      //  System.out.println("customer.name:" + customer.getName()+" type:"+customer.getCustType().getId());
        CustomerDAO.saveOrUpdateCustomer(customer);
        addActionMessage(customer.getName1() + " - customer record updated!");
        return "confirm";
    }

    public String viewAll() {
        customerList = CustomerDAO.customerList();
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("customer.id"));
        if (cust != null) {
            String outMessage = CustomerDAO.deleteCustomer(cust);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long cust = Long.parseLong(request.getParameter("customer.id"));
        if (cust != null) {
            customer = CustomerDAO.listCustomerById(cust);
        }
        System.out.println( "#Customer name:" + customer.getName1());
        return "add";
    }
    public Customer getModel() {
        return customer;
    }
}
