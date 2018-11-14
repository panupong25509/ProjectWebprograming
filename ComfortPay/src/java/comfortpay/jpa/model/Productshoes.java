/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.jpa.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joknoi
 */
@Entity
@Table(name = "PRODUCTSHOES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productshoes.findAll", query = "SELECT p FROM Productshoes p")
    , @NamedQuery(name = "Productshoes.findByProductcode", query = "SELECT p FROM Productshoes p WHERE p.productcode = :productcode")
    , @NamedQuery(name = "Productshoes.findByProductid", query = "SELECT p FROM Productshoes p WHERE p.productid = :productid")
    , @NamedQuery(name = "Productshoes.findByProductname", query = "SELECT p FROM Productshoes p WHERE p.productname = :productname")
    , @NamedQuery(name = "Productshoes.findByProducttype", query = "SELECT p FROM Productshoes p WHERE p.producttype = :producttype")
    , @NamedQuery(name = "Productshoes.findByDescription", query = "SELECT p FROM Productshoes p WHERE p.description = :description")
    , @NamedQuery(name = "Productshoes.findByColor", query = "SELECT p FROM Productshoes p WHERE p.color = :color")
    , @NamedQuery(name = "Productshoes.findBySize", query = "SELECT p FROM Productshoes p WHERE p.size = :size")
    , @NamedQuery(name = "Productshoes.findByPrice", query = "SELECT p FROM Productshoes p WHERE p.price = :price")})
public class Productshoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "PRODUCTCODE")
    private String productcode;
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private int productid;
    @Size(max = 100)
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Size(max = 20)
    @Column(name = "PRODUCTTYPE")
    private String producttype;
    @Size(max = 200)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 20)
    @Column(name = "COLOR")
    private String color;
    @Column(name = "SIZE")
    private Integer size;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;

    public Productshoes() {
    }

    public Productshoes(String productcode) {
        this.productcode = productcode;
    }

    public Productshoes(String productcode, int productid) {
        this.productcode = productcode;
        this.productid = productid;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productcode != null ? productcode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productshoes)) {
            return false;
        }
        Productshoes other = (Productshoes) object;
        if ((this.productcode == null && other.productcode != null) || (this.productcode != null && !this.productcode.equals(other.productcode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.jpa.model.Productshoes[ productcode=" + productcode + " ]";
    }
    
}
