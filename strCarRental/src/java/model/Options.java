/*
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
import javax.persistence.OneToMany;

/**
 *
 * @author Ronal
 */
@Entity
public class Options implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String options;
    private Double perDayCost;
    private String unit;
    @OneToMany(mappedBy = "options")
    private List<ReservationOption> reservationOptions;
    
    public Options() {
    }

    public Options(Long id, String options, Double perDayCost, String unit, List<ReservationOption> reservationOptions) {
        this.id = id;
        this.options = options;
        this.perDayCost = perDayCost;
        this.unit = unit;
        this.reservationOptions = reservationOptions;
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
        if (!(object instanceof Options)) {
            return false;
        }
        Options other = (Options) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Options[ id=" + id + " ]";
    }

    /**
     * @return the options
     */
    public String getOptions() {
        return options;
    }

    /**
     * @param options the options to set
     */
    public void setOptions(String options) {
        this.options = options;
    }

    /**
     * @return the perDayCost
     */
    public Double getPerDayCost() {
        return perDayCost;
    }

    /**
     * @param perDayCost the perDayCost to set
     */
    public void setPerDayCost(Double perDayCost) {
        this.perDayCost = perDayCost;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
