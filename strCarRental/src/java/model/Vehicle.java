/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Ronal
 */
@Entity
public class Vehicle implements Serializable {

    /**
     * @return the serialVersionUID
     */
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numberPlate;
    private String chaseNumber;
    private String color;
    private String firstRegistration;
    private Integer dailyMilage;
    private Integer currentMilage;
    private String imagePath;
    private String status;
    private String name;

    @ManyToOne
    private VehicleModel vehicleModel;
    @ManyToOne
    private Location location;
    @ManyToMany
    private List<Feature> features;
    @ManyToOne
    private VehicleClass vehicleClass;    
    @OneToMany(mappedBy = "vehicle")
    private List<Rate> rates;
    
    public Vehicle() {
    }
    
    public String getName() {
        return vehicleModel.getMake().getName() +" "+vehicleModel.getDescription()+ "-"+ numberPlate ;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Vehicle(Long id, String numberPlate, String chaseNumber, String color, String firstRegistration, Integer dailyMilage, Integer currentMilage, String imagePath, String status, VehicleModel vehicleModel, Location location, List<Feature> features, VehicleClass vehicleClass, List<Rate> rates) {
        this.id = id;
        this.numberPlate = numberPlate;
        this.chaseNumber = chaseNumber;
        this.color = color;
        this.firstRegistration = firstRegistration;
        this.dailyMilage = dailyMilage;
        this.currentMilage = currentMilage;
        this.imagePath = imagePath;
        this.status = status;
        this.vehicleModel = vehicleModel;
        this.location = location;
        this.features = features;
        this.vehicleClass = vehicleClass;
        this.rates = rates;
    }

   public Integer getCurrentMilage() {
        return currentMilage;
    }

    public void setCurrentMilage(Integer currentMilage) {
        this.currentMilage = currentMilage;
    }

    
    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
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
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Vehicle[ id=" + id + " ]";
    }

    /**
     * @return the vehicleClass
     */
    public VehicleClass getVehicleClass() {
        return vehicleClass;
    }

    /**
     * @param vehicleClass the vehicleClass to set
     */
    public void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    /**
     * @return the numberPlate
     */
    public String getNumberPlate() {
        return numberPlate;
    }

    /**
     * @param numberPlate the numberPlate to set
     */
    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    /**
     * @return the chaseNumber
     */
    public String getChaseNumber() {
        return chaseNumber;
    }

    /**
     * @param chaseNumber the chaseNumber to set
     */
    public void setChaseNumber(String chaseNumber) {
        this.chaseNumber = chaseNumber;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the firstRegistration
     */
    public String getFirstRegistration() {
        return firstRegistration;
    }

    /**
     * @param firstRegistration the firstRegistration to set
     */
    public void setFirstRegistration(String firstRegistration) {
        this.firstRegistration = firstRegistration;
    }

    /**
     * @return the dailyMilage
     */
    public Integer getDailyMilage() {
        return dailyMilage;
    }

    /**
     * @param dailyMilage the dailyMilage to set
     */
    public void setDailyMilage(Integer dailyMilage) {
        this.dailyMilage = dailyMilage;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the status
     */
    public String getstatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setstatus(String status) {
        this.status = status;
    }

    /**
     * @return the vehicleModel
     */
    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    /**
     * @param vehicleModel the vehicleModel to set
     */
    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the features
     */
    public List<Feature> getFeatures() {
        return features;
    }

    /**
     * @param features the features to set
     */
    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
    
}
