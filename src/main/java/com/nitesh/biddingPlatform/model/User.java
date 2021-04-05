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
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User(int id, String firstName, String lastName, String email, String contactNo, String gender, List<Product> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.products = products;
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
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public User shallowCopy() throws CloneNotSupportedException {
        User clonedUser = (User) this.clone();
        List<Product> products = this.getProducts();
        List<Product> newProductList = new ArrayList<>();
        for (Product product : products) {
            newProductList.add(product.shallowCopy());
        }
        clonedUser.setProducts(newProductList);
        return clonedUser;
    }
}
