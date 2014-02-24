/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ronal
 */
@Entity
public class Reservation implements Serializable {
    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;
    @ManyToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;    
    private String startDate;
    private String endDate;
    private int noOfDays;
    private String calcMethod;
    private Double tax;
    private Double base;
    private Double optionTotal;
    private Double net;
    @OneToMany(mappedBy = "reservation",cascade = CascadeType.ALL)
    private List<ReservationOption> reservationOptions;
    private String remarks;
    private String referance;
    public Reservation() {
    }

    public Reservation(Long id, Customer customer, Location location, Vehicle vehicle, String startDate, String endDate, int noOfDays, String calcMethod, Double tax, Double base, Double optionTotal, Double net, List<ReservationOption> reservationOptions, String remarks, String referance) {
        this.id = id;
        this.customer = customer;
        this.location = location;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noOfDays = noOfDays;
        this.calcMethod = calcMethod;
        this.tax = tax;
        this.base = base;
        this.optionTotal = optionTotal;
        this.net = net;
        this.reservationOptions = reservationOptions;
        this.remarks = remarks;
        this.referance = referance;
    }

    public String getReferance() {
        return referance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setReferance(String referance) {
        this.referance = referance;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEndDate() {
        return endDate;
    }

    public Location getLocation() {
        return location;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reservation[ id=" + id + " ]";
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the noOfDays
     */
    public int getNoOfDays() {
        return noOfDays;
    }

    /**
     * @param noOfDays the noOfDays to set
     */
    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    /**
     * @return the calcMethod
     */
    public String getCalcMethod() {
        return calcMethod;
    }

    /**
     * @param calcMethod the calcMethod to set
     */
    public void setCalcMethod(String calcMethod) {
        this.calcMethod = calcMethod;
    }

    /**
     * @return the tax
     */
    public Double getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }

    /**
     * @return the base
     */
    public Double getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Double base) {
        this.base = base;
    }

    /**
     * @return the optionTotal
     */
    public Double getOptionTotal() {
        return optionTotal;
    }

    /**
     * @param optionTotal the optionTotal to set
     */
    public void setOptionTotal(Double optionTotal) {
        this.optionTotal = optionTotal;
    }

    /**
     * @return the net
     */
    public Double getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(Double net) {
        this.net = net;
    }

    /**
     * @return the reservationOptions
     */
    public List<ReservationOption> getReservationOptions() {
        return reservationOptions;
    }

    /**
     * @param reservationOptions the reservationOptions to set
     */
    public void setReservationOptions(List<ReservationOption> reservationOptions) {
        this.reservationOptions = reservationOptions;
    }
    
}
