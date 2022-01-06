package net.javaguides.springboot.model.response;

import net.javaguides.springboot.entity.Modal;

import java.util.ArrayList;
import java.util.List;

public class DataOfCustomerAndModal {
    private String fullname;
    private String phone;
    private String emailCustomer;
    private String address;
    private String content;
    private String notes;
    private String createDate;
    private List<Modal> modal = new ArrayList<>();

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Modal> getModal() {
        return modal;
    }

    public void setModal(List<Modal> modal) {
        this.modal = modal;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


}
