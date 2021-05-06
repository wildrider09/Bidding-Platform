package com.nitesh.biddingPlatform.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String gender;
    @OneToMany(mappedBy = "productOwner", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "bidOwner", cascade = CascadeType.ALL)
    List<ProductBids> bids = new ArrayList<>();

    /*@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Trade> tradeList;*/

    /*
    {
    "firstName": "Nitesh",
    "lastName": "Jain",
    "email": "nj@gmail.com",
    "contactNo": "9876543210",
    "gender": "Male"
}
     */


    public User(int id, String firstName, String lastName, String email, String contactNo, String gender, List<Product> products, List<ProductBids> bids) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.products = products;
        this.bids = bids;
    }

    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<ProductBids> getBids() {
        return bids;
    }

    public void setBids(List<ProductBids> bids) {
        this.bids = bids;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", gender='" + gender + '\'' +
                ", products=" + products +
                ", bids=" + bids +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public User shallowCopyForProducts() throws CloneNotSupportedException {
        User clonedUser = (User) this.clone();
        List<Product> products = clonedUser.getProducts();
        for(Product product : products){
            product.setUser(null);
            product.setBids(null);
        }
        List<ProductBids> bids = clonedUser.getBids();
        for(ProductBids bid : bids){
            bid.setBidOwner(null);
            bid.setProductToBid(null);
        }
        clonedUser.setProducts(products);
        clonedUser.setBids(bids);
        return clonedUser;
    }

}
