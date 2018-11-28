/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joknoi
 */
@Entity
@Table(name = "SIZE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Size1.findAll", query = "SELECT s FROM Size1 s")
    , @NamedQuery(name = "Size1.findBySizeid", query = "SELECT s FROM Size1 s WHERE s.sizeid = :sizeid")
    , @NamedQuery(name = "Size1.findBySize", query = "SELECT s FROM Size1 s WHERE s.size = :size")
    , @NamedQuery(name = "Size1.findByQuantity", query = "SELECT s FROM Size1 s WHERE s.quantity = :quantity")})
public class Size1 implements Serializable {

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
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private Products productid;

    public Size1() {
    }

    public Size1(Integer sizeid) {
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
        if (!(object instanceof Size1)) {
            return false;
        }
        Size1 other = (Size1) object;
        if ((this.sizeid == null && other.sizeid != null) || (this.sizeid != null && !this.sizeid.equals(other.sizeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.model.Size1[ sizeid=" + sizeid + " ]";
    }
    
}
