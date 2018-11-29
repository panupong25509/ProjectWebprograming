/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Joknoi
 */
@Entity
@Table(name = "SIZES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sizes.findAll", query = "SELECT s FROM Sizes s")
    , @NamedQuery(name = "Sizes.findBySizeid", query = "SELECT s FROM Sizes s WHERE s.sizeid = :sizeid")
    , @NamedQuery(name = "Sizes.findBySize", query = "SELECT s FROM Sizes s WHERE s.size = :size")
    , @NamedQuery(name = "Sizes.findByQuantity", query = "SELECT s FROM Sizes s WHERE s.quantity = :quantity")})
public class Sizes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SIZEID")
    private Integer sizeid;
    @Size(max = 4)
    @Column(name = "SIZE")
    private String size;
    @Column(name = "QUANTITY")
    private Integer quantity;
    @OneToMany(mappedBy = "sizeid")
    private List<Orderlists> orderlistsList;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private Products productid;

    public Sizes() {
    }

    public Sizes(String size, Integer quantity, Products productid) {
        this.size = size;
        this.quantity = quantity;
        this.productid = productid;
    }

    public Sizes(Integer sizeid) {
        this.sizeid = sizeid;
    }

    public Integer getSizeid() {
        return sizeid;
    }

    public void setSizeid(Integer sizeid) {
        this.sizeid = sizeid;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public List<Orderlists> getOrderlistsList() {
        return orderlistsList;
    }

    public void setOrderlistsList(List<Orderlists> orderlistsList) {
        this.orderlistsList = orderlistsList;
    }

    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
        this.productid = productid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sizeid != null ? sizeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sizes)) {
            return false;
        }
        Sizes other = (Sizes) object;
        if ((this.sizeid == null && other.sizeid != null) || (this.sizeid != null && !this.sizeid.equals(other.sizeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.model.Sizes[ sizeid=" + sizeid + " ]";
    }
    
}
