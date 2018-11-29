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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joknoi
 */
@Entity
@Table(name = "ORDERLISTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orderlists.findAll", query = "SELECT o FROM Orderlists o")
    , @NamedQuery(name = "Orderlists.findByOrderlistid", query = "SELECT o FROM Orderlists o WHERE o.orderlistid = :orderlistid")
    , @NamedQuery(name = "Orderlists.findByQuantity", query = "SELECT o FROM Orderlists o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "Orderlists.findByTotalprice", query = "SELECT o FROM Orderlists o WHERE o.totalprice = :totalprice")})
public class Orderlists implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDERLISTID")
    private Integer orderlistid;
    @Column(name = "QUANTITY")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "TOTALPRICE")
    private Double totalprice;
    @JoinColumn(name = "ORDERID", referencedColumnName = "ORDERID")
    @ManyToOne
    private Orders orderid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private Products productid;
    @JoinColumn(name = "SIZEID", referencedColumnName = "SIZEID")
    @ManyToOne
    private Sizes sizeid;

    public Orderlists() {
    }

    public Orderlists(Integer orderlistid) {
        this.orderlistid = orderlistid;
    }

    public Orderlists(Integer quantity, Double totalprice, Orders orderid, Products productid, Sizes sizeid) {
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.orderid = orderid;
        this.productid = productid;
        this.sizeid = sizeid;
    }

    
    public Integer getOrderlistid() {
        return orderlistid;
    }

    public void setOrderlistid(Integer orderlistid) {
        this.orderlistid = orderlistid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Orders getOrderid() {
        return orderid;
    }

    public void setOrderid(Orders orderid) {
        this.orderid = orderid;
    }

    public Products getProductid() {
        return productid;
    }

    public void setProductid(Products productid) {
        this.productid = productid;
    }

    public Sizes getSizeid() {
        return sizeid;
    }

    public void setSizeid(Sizes sizeid) {
        this.sizeid = sizeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderlistid != null ? orderlistid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orderlists)) {
            return false;
        }
        Orderlists other = (Orderlists) object;
        if ((this.orderlistid == null && other.orderlistid != null) || (this.orderlistid != null && !this.orderlistid.equals(other.orderlistid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.model.Orderlists[ orderlistid=" + orderlistid + " ]";
    }
    
}
