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
@Table(name = "PRODUCTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p")
    , @NamedQuery(name = "Products.findByProductid", query = "SELECT p FROM Products p WHERE p.productid = :productid")
    , @NamedQuery(name = "Products.findByProductcode", query = "SELECT p FROM Products p WHERE p.productcode = :productcode")
    , @NamedQuery(name = "Products.findByProductname", query = "SELECT p FROM Products p WHERE p.productname LIKE :productname")
    , @NamedQuery(name = "Products.findByProducttype", query = "SELECT p FROM Products p WHERE p.producttype = :producttype")
    , @NamedQuery(name = "Products.findByCloth", query = "SELECT p FROM Products p WHERE p.producttype = :shirt"
            + "                                           UNION"
            + "                                           SELECT p FROM Products p WHERE p.producttype = :bra"
            + "                                           UNION"
            + "                                           SELECT p FROM Products p WHERE p.producttype = :hood"
            + "                                           UNION"
            + "                                           SELECT p FROM Products p WHERE p.producttype = :pant"
            + "                                           UNION"
            + "                                           SELECT p FROM Products p WHERE p.producttype = :short")
    , @NamedQuery(name = "Products.findByShoes", query = "SELECT p FROM Products p WHERE p.producttype = :sneaker"
            + "                                           UNION"
            + "                                           SELECT p FROM Products p WHERE p.producttype = :slipper")
    , @NamedQuery(name = "Products.findByFilter", query = "SELECT p FROM Products p WHERE p.productname LIKE :productname"
            + "                                            AND p.productband LIKE :productbrand"
            + "                                            AND p.producttype LIKE :producttype"
            + "                                            AND p.color LIKE :color"
            + "                                            AND p.price >= :start AND p.price <= :end")
    , @NamedQuery(name = "Products.findByProductband", query = "SELECT p FROM Products p WHERE p.productband = :productband")
    , @NamedQuery(name = "Products.findByColor", query = "SELECT p FROM Products p WHERE p.color = :color")
    , @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price >= :start AND p.price <= :end")})
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PRODUCTID")
    private Integer productid;
    @Size(max = 8)
    @Column(name = "PRODUCTCODE")
    private String productcode;
    @Size(max = 50)
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Size(max = 10)
    @Column(name = "PRODUCTTYPE")
    private String producttype;
    @Size(max = 8)
    @Column(name = "PRODUCTBAND")
    private String productband;
    @Size(max = 10)
    @Column(name = "COLOR")
    private String color;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRICE")
    private Double price;
    @OneToMany(mappedBy = "productid")
    private List<Wishlishs> wishlishsList;
    @OneToMany(mappedBy = "productid")
    private List<Orderlists> orderlistsList;
    @OneToMany(mappedBy = "productid")
    private List<Sizes> sizesList;

    public Products() {
    }

    public Products(String productcode, String productname, String producttype, String productband, String color, Double price) {
        this.productcode = productcode;
        this.productname = productname;
        this.producttype = producttype;
        this.productband = productband;
        this.color = color;
        this.price = price;
    }

    public Products(Integer productid) {
        this.productid = productid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
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

    public String getProductband() {
        return productband;
    }

    public void setProductband(String productband) {
        this.productband = productband;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlTransient
    public List<Wishlishs> getWishlishsList() {
        return wishlishsList;
    }

    public void setWishlishsList(List<Wishlishs> wishlishsList) {
        this.wishlishsList = wishlishsList;
    }

    @XmlTransient
    public List<Orderlists> getOrderlistsList() {
        return orderlistsList;
    }

    public void setOrderlistsList(List<Orderlists> orderlistsList) {
        this.orderlistsList = orderlistsList;
    }

    @XmlTransient
    public List<Sizes> getSizesList() {
        return sizesList;
    }

    public void setSizesList(List<Sizes> sizesList) {
        this.sizesList = sizesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productid != null ? productid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productid == null && other.productid != null) || (this.productid != null && !this.productid.equals(other.productid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "comfortpay.model.Products[ productid=" + productid + " ]";
    }

}
