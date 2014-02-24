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
import dao.CustomerDAO;
import dao.LocationDAO;
import dao.OptionsDAO;
import dao.ReservationDAO;
import dao.VehicleClassDAO;
import dao.VehicleDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Customer;
import model.Location;
import model.Options;
import model.Reservation;
import model.ReservationOption;
import model.Vehicle;
import model.VehicleClass;
import org.apache.struts2.ServletActionContext;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.CustType;
import model.Rate;

/**
 *
 * @author Ronal
 */
public class ReservationAction extends ActionSupport implements ModelDriven<Reservation>, Preparable {

    private Reservation reservation = new Reservation();
    private List<Reservation> reservationList = new ArrayList<Reservation>();
    private List<Reservation> activeReservationList = new ArrayList<Reservation>();
    private List<Reservation> dropOffList = new ArrayList<Reservation>();
    private List<Reservation> pickUpList = new ArrayList<Reservation>();
    private List<Rate> rateList = new ArrayList<Rate>();
    private List<Customer> customerList;
    private List<Location> locationList;
    private Location myLocation;
    private List<Vehicle> vehicleList;
    private List<VehicleClass> vehicleClassList;
    private VehicleClass myVehicleClass;
    private Customer customer;
    private List<Options> optionsList;
    private List<Integer> oneFiveList = new ArrayList();
    private String pickupDate;
    private String DropOffDate;
    private List<CustType> custTypeList;
    static final Properties properties = new Properties();
    private final int rowsPerPage = 10;
    private int pageNow = 1;

    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    public void prepare() throws Exception {
        System.out.println("prepare...");
        setVehicleList(VehicleDAO.vehicleList());
        setVehicleClassList(VehicleClassDAO.vehicleClassList());
        setCustomerList(CustomerDAO.customerList());
        setLocationList(LocationDAO.locationList());
        setOptionsList(OptionsDAO.optionList());
        oneFiveList.add(1);
        oneFiveList.add(2);
        oneFiveList.add(3);
        oneFiveList.add(4);
        oneFiveList.add(5);
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public List<Reservation> getActiveReservationList() {
        return activeReservationList;
    }

    public void setActiveReservationList(List<Reservation> activeReservationList) {
        this.activeReservationList = activeReservationList;
    }

    public void setOneFiveList(List<Integer> oneFiveList) {
        this.oneFiveList = oneFiveList;
    }

    public List<Integer> getOneFiveList() {
        return oneFiveList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getDropOffDate() {
        return DropOffDate;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickUpList(List<Reservation> pickUpList) {
        this.pickUpList = pickUpList;
    }

    public List<Reservation> getPickUpList() {
        return pickUpList;
    }

    public void setDropOffList(List<Reservation> dropOffList) {
        this.dropOffList = dropOffList;
    }

    public List<Reservation> getDropOffList() {
        return dropOffList;
    }

    public void setDropOffDate(String DropOffDate) {
        this.DropOffDate = DropOffDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public VehicleClass getMyVehicleClass() {
        return myVehicleClass;
    }

    public void setMyVehicleClass(VehicleClass myVehicleClass) {
        this.myVehicleClass = myVehicleClass;
    }

    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    public void setVehicleClassList(List<VehicleClass> vehicleClassList) {
        this.vehicleClassList = vehicleClassList;
    }

    public List<VehicleClass> getVehicleClassList() {
        return vehicleClassList;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setCustTypeList(List<CustType> custTypeList) {
        this.custTypeList = custTypeList;
    }

    public List<CustType> getCustTypeList() {
        return custTypeList;
    }

    public void setRateList(List<Rate> rateList) {
        this.rateList = rateList;
    }

    public List<Rate> getRateList() {
        return rateList;
    }

    public String add() {
        return "add";
    }
    public String finalConfirmation() {
        System.out.println("FinalConfirmation");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Reservation myReservation = (Reservation) request.getSession().getAttribute("myReservation");
        ReservationDAO.saveOrUpdateReservation(myReservation);
        sendBookingConfirmation(myReservation);
        return "final";
    }

    public String saveCustomer() {
        System.out.println("SaveCustomer");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Reservation myReservation = (Reservation) request.getSession().getAttribute("myReservation");
        myReservation.setCustomer(reservation.getCustomer());
        System.out.println("resv cust:"+reservation.getCustomer().getName1());
        System.out.println("resv cust:"+reservation.getCustomer().getName2());
        System.out.println("resv cust:"+reservation.getCustomer().getCity());
        
        request.getSession().setAttribute("myReservation", myReservation);
        return "confirm";
    }
    public String getCustomerDet() {
        System.out.println("CustomerDet");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Reservation myReservation = (Reservation) request.getSession().getAttribute("myReservation");
        myReservation.setReservationOptions(reservation.getReservationOptions());
        myReservation.setOptionTotal(reservation.getOptionTotal());
        double net = reservation.getOptionTotal() + myReservation.getBase();
        myReservation.setNet(net);
        String email = reservation.getCustomer().getEmail();
        
        Customer customer1 = CustomerDAO.listCustomerByEmail(email);
        //System.out.println("CusID:" + customer1.getId());
        if (customer1 != null) {
            System.out.println("customer set..");
            myReservation.setCustomer(customer1);
            reservation.setCustomer(customer1);
            setCustomer(customer1);
        }
        request.getSession().setAttribute("myReservation", myReservation);
        custTypeList = CustTypeDAO.custTypeList();
        return "customer";
    }
 public String getOption() {
        System.out.println("2.options....");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long vehicleID = Long.parseLong(request.getParameter("vehicle.id"));

        Reservation myReservation = (Reservation) request.getSession().getAttribute("myReservation");
        myReservation.setVehicle(VehicleDAO.listVehicleById(vehicleID));
        rateList = myReservation.getVehicle().getRates();
        Double mon = 0.0;
        Double wek = 0.0;
        Double dly = 0.0;
        for (Rate rate : rateList) {
            if (rate.getRateType().getNoDays() == 30) {
                mon = rate.getPerDayCost();
            }
            if (rate.getRateType().getNoDays() == 7) {
                wek = rate.getPerDayCost();
            }
            if (rate.getRateType().getNoDays() == 1) {
                dly = rate.getPerDayCost();
            }
        }
        String calcMethod = "";
        int noDays = myReservation.getNoOfDays();
        Double amount = 0.0;
        if (noDays > 30) {
            amount += ((int) noDays / 30) * 30 * mon;
            calcMethod += ((int) noDays / 30) * 30 + " Days *" + mon;
            noDays = noDays % 30;
        }
        if (noDays > 7) {
            amount += ((int) noDays / 7) * 7 * wek;
            calcMethod += " + " + ((int) noDays / 7) * 7 + " Days *" + wek;
            noDays = noDays % 7;
        }
        amount += noDays * dly;
        calcMethod += " + " + noDays + " Days *" + dly;

        myReservation.setTax(0.0);
        myReservation.setCalcMethod(calcMethod);
        myReservation.setBase(amount);
        myReservation.setOptionTotal(0.0);
        myReservation.setNet(amount);

        request.getSession().setAttribute("myReservation", myReservation);

        return "option";
    }
    public String getVehicle() {
        System.out.println("1.Vehicles");
        //vehicleList = VehicleDAO.vehicleList();
        vehicleList = VehicleDAO.vehicleList(pickupDate, DropOffDate, myVehicleClass.getId());
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        reservation.setStartDate(pickupDate);
        reservation.setEndDate(DropOffDate);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        Date frmDate = new Date();
        Date toDate = new Date();
        try {
            frmDate = sdf.parse(pickupDate);
            toDate = sdf.parse(DropOffDate);
        } catch (ParseException ex) {
            Logger.getLogger(ReservationAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        int noOfDays = (int) ((toDate.getTime() - frmDate.getTime()) / (1000 * 60 * 60 * 24));
        System.out.println(frmDate + "#" + toDate + "#" + noOfDays);
        reservation.setNoOfDays(noOfDays);
        reservation.setLocation(LocationDAO.listLocationById(myLocation.getId()));
        Random r = new Random( System.currentTimeMillis() );
        reservation.setReferance(10000 + r.nextInt(20000)+"");
        request.getSession().setAttribute("myReservation", reservation);
        return "vehicle";
    }

   

    public String dashboard() {
        //  load lists for dashboard
        int noOfDays =2;
        dropOffList = ReservationDAO.dropOffListbyDate(noOfDays);
        pickUpList = ReservationDAO.pickupListbyDate(noOfDays);
        return "dash";
    }
    public String save() {
        //  System.out.println("reservation.name:" + reservation.getName()+" type:"+reservation.getReservationType().getId());
        ReservationDAO.saveOrUpdateReservation(reservation);
        addActionMessage(reservation.getCustomer().getName1() + " - record updated!");
        return "confirmSave";
    }

    public String viewAll() {
        setReservationList(ReservationDAO.reservationList());
        if(pageNow<1){
            pageNow=1;
        }
        System.out.println("pg:"+pageNow);
        int start = (pageNow - 1) * rowsPerPage;
        int end = start+rowsPerPage;
        
        if (end>reservationList.size()){
            end = reservationList.size();
        }
        System.out.println("resvtot:"+reservationList.size()+"start"+start+"end"+end);
        setActiveReservationList(reservationList.subList(start, end));
        System.out.println("resvactive:"+activeReservationList.size());
        return "viewAll";
    }

    public String delete() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long resId = Long.parseLong(request.getParameter("reservation.id"));
        if (resId != null) {
            String outMessage = ReservationDAO.deleteReservation(resId);
            addActionMessage(outMessage);
        }
        return "confirm";
    }

    public String edit() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        Long resId = Long.parseLong(request.getParameter("reservation.id"));
        if (resId != null) {
            reservation = ReservationDAO.listReservationById(resId);
        }
        System.out.println("#Reservation name:" + reservation.getCustomer().getName1());
        return "add";
    }

    public Reservation getModel() {
        return reservation;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Options> getOptionsList() {
        return optionsList;
    }

    public void setOptionsList(List<Options> optionsList) {
        this.optionsList = optionsList;
    }

    public void sendBookingConfirmation(Reservation reservation1) {
        String Subject = "Confirmation of your booking from car4rent";
        String content = "<h4>Dear "+reservation1.getCustomer().getName1()+",</h4>";
        content += "<table cellpadding='5' cellspacing='1'>  <tbody>";
        content += "<tr><td align='left' colspan='4'>Rental Details</td></tr>";
        content += "<tr><td bgcolor='#f2f2f2' align='left' colspan='2'><strong>Pick-up Location</strong></td>";
        content += "<td bgcolor='#f2f2f2' align='left' colspan='2'><strong>Reference</strong></td></tr>";
        content += "<tr><td bgcolor='#ffffff' align='left' colspan='2'>"+reservation1.getLocation().getLocName()+"</td>";
        content += "<td bgcolor='#ffffff' align='left' colspan='2'>"+reservation1.getReferance()+"</td></tr>";
        content += "<tr><td bgcolor='#f2f2f2' align='left' colspan='1'><strong>Pick-up Date &amp; Time</strong></td><td bgcolor='#f2f2f2' align='left' colspan='1'><strong>Drop-off Date &amp; Time</strong></td><td bgcolor='#f2f2f2' align='left' colspan='2'></td></tr>";
        content += "<tr><td bgcolor='#FFFFFF' align='left' colspan='1'>"+reservation1.getStartDate()+"</td><td bgcolor='#FFFFFF' align='left' colspan='1'>"+reservation1.getEndDate()+"</td><td bgcolor='#FFFFFF' align='center' colspan='2'><strong>= "+reservation1.getNoOfDays()+" Days</strong></td> </tr>";
        content += "<tr><td bgcolor='#f2f2f2' align='left'><strong>Car Type</strong></td><td bgcolor='#f2f2f2' align='center'><strong>Car Vendor</strong></td><td bgcolor='#f2f2f2' align='center'><strong>Car Model</strong></td>";
        content += "<td bgcolor='#f2f2f2' align='right'><strong>Gross Total</strong></td></tr>";
        content += "<tr><td bgcolor='#FFFFFF' align='left'>"+reservation1.getVehicle().getVehicleClass().getDescription()+"</td><td bgcolor='#FFFFFF' align='center'>"+reservation1.getVehicle().getVehicleModel().getMake().getName()+"</td><td bgcolor='#FFFFFF' align='center'>"+reservation1.getVehicle().getVehicleModel().getDescription()+"</td><td bgcolor='#FFFFFF' align='right'>"+reservation1.getBase()+"</td></tr>";
        content += "<tr><td bgcolor='#f2f2f2' align='left' colspan='4'><strong>Rental Options</strong></td></tr>";
        content += "<tr><td bgcolor='#FFFFFF' align='left' colspan='3'>Options Total</td><td bgcolor='#FFFFFF' align='right'>"+reservation1.getOptionTotal()+"</td></tr>";
        content += "<tr><td colspan='3' align='right'>Sub Total</td><td align='right'>"+reservation1.getNet()+"</td></tr>";
        content += "<tr><td colspan='3' align='right'>Tax(0.00%)</td><td align='right'>0.00</td></tr>";
        content += "<tr><td colspan='3' align='right'>Grand Total</td><td align='right'>"+reservation1.getNet()+"</td></tr>";
        content += "</tbody></table><br /><br />Regards,<br />Car4Rent team<br />0777 999 888";
        boolean sent = sendMail(reservation1.getCustomer().getEmail(), Subject, content);
        System.out.println("sent:" + sent);
    }

    public boolean sendMail(String to, String subject, String body) {
        final String from = "car4rentnow@gmail.com";
        final String password = "nowcar4rent";
        boolean ret = true;
        try {
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                //Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            //message.setText(body);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (Exception e) {
            ret = false;
            e.printStackTrace();
        }
        return ret;
    }
}
