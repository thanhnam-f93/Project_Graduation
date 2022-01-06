package net.javaguides.springboot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@javax.persistence.Table(name = "payment_history")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "_status")
    private boolean status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "total_price")
    private double totalPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "email")
    private User users;


    @ManyToOne
    @JoinColumn(name = "id_service")
    private ServiceFee serviceFee;

    public PaymentHistory() {
    }

    public PaymentHistory(int id, Date dateEnd, boolean status, Date dateStart, double totalPrice, User users, ServiceFee serviceFee) {
        this.id = id;
        this.dateEnd = dateEnd;
        this.status = status;
        this.dateStart = dateStart;
        this.totalPrice = totalPrice;
        this.users = users;
        this.serviceFee = serviceFee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public ServiceFee getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(ServiceFee serviceFee) {
        this.serviceFee = serviceFee;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}