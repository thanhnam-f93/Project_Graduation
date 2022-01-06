package net.javaguides.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@javax.persistence.Table(name = "servicefee")
public class ServiceFee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_service")
    private String nameService;

    @Column(name = "price")
    private double price;

    @JsonIgnore
    @OneToMany(mappedBy = "serviceFee", fetch = FetchType.LAZY)
    private Collection<PaymentHistory> paymentHistories;

    public ServiceFee() {
    }

    public ServiceFee(int id, String nameService, double price, Collection<PaymentHistory> paymentHistories) {
        this.id = id;
        this.nameService = nameService;
        this.price = price;
        this.paymentHistories = paymentHistories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
