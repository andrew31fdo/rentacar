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
public class Make implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String remarks;
    @OneToMany(mappedBy = "make")
    private List<VehicleModel> models;

    public void setModels(List<VehicleModel> models) {
        this.models = models;
    }

    public List<VehicleModel> getModels() {
        return models;
    }

    public Make(Long id, String name, String remarks, List<VehicleModel> models) {
        this.id = id;
        this.name = name;
        this.remarks = remarks;
        this.models = models;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getName() {
        return name;
    }

    public Make(Long id, String name, String remarks) {
        this.id = id;
        this.name = name;
        this.remarks = remarks;
    }

    public Make() {
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
        if (!(object instanceof Make)) {
            return false;
        }
        Make other = (Make) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Make[ id=" + id + " ]";
    }
    
}
