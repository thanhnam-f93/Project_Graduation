package net.javaguides.springboot.dto;

public class PaymentDto {
    String email;
    double price;

    public PaymentDto(String email, double price) {
        this.email = email;
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
