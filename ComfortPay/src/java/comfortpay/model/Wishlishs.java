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
@Table(name = "WISHLISHS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wishlishs.findAll", query = "SELECT w FROM Wishlishs w")
    , @NamedQuery(name = "Wishlishs.findByWishlishid", query = "SELECT w FROM Wishlishs w WHERE w.wishlishid = :wishlishid")})
public class Wishlishs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WISHLISHID")
    private Integer wishlishid;
    @JoinColumn(name = "ACCOUNTID", referencedColumnName = "ACCOUNTID")
    @ManyToOne
    private Account accountid;
    @JoinColumn(name = "PRODUCTID", referencedColumnName = "PRODUCTID")
    @ManyToOne
    private Products productid;

    public Wishlishs() {
    }

    public Wishlishs(Integer wishlishid) {
        this.wishlishid = wishlishid;
    }

    public Integer getWishlishid() {
        return wishlishid;
    }

    public void setWishlishid(Integer wishlishid) {
        this.wishlishid = wishlishid;
    }

    public Account getAccountid() {
        return accountid;
    }

    public void setAccountid(Account accountid) {
        this.accountid = accountid;
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
        hash += (wishlishid != null ? wishlishid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlishs)) {
            return false;
        }
        Wishlishs other = (Wishlishs) object;
        if ((this.wishlishid == null && other.wishlishid != null) || (this.wishlishid != null && !this.wishlishid.equals(other.wishlishid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.model.Wishlishs[ wishlishid=" + wishlishid + " ]";
    }
    
}
