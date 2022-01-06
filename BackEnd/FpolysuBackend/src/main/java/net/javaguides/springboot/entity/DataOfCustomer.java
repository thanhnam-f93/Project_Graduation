package net.javaguides.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;


@Entity
@javax.persistence.Table(name = "dataofcustomer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DataOfCustomer {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email_customer")
    private String emailCustomer;

    @Column(name = "_address")
    private String address;

    @Column(name = "content")
    private String conTent;

    @Column(name = "notes ")
    private String notes;
    @Column(name = "create_date")
    private String createDate;
    @ManyToOne
    @JoinColumn(name = "email")
    private User users;

    @JsonIgnore
    @OneToMany(mappedBy = "dataOfCustomer", fetch = FetchType.LAZY)
    private Collection<Modal> modal;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

//    public DataOfCustomer(int id, String fullName, String phone, String emailCustomer, String address, String conTent, String notes, User users) {
//        this.id = id;
//        this.fullName = fullName;
//        this.phone = phone;
//        this.emailCustomer = emailCustomer;
//        this.address = address;
//        this.conTent = conTent;
//        this.notes = notes;
//        this.users = users;
//    }
//
//    public DataOfCustomer() {
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailCustomer() {
        return emailCustomer;
    }

    public void setEmailCustomer(String emailCustomer) {
        this.emailCustomer = emailCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConTent() {
        return conTent;
    }

    public void setConTent(String conTent) {
        this.conTent = conTent;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
