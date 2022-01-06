package net.javaguides.springboot.entity;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private List<Menu> menu = new ArrayList<>();

    private List<Button> buttons = new ArrayList<>();

    private List<Modal> modal = new ArrayList<>();

    private List<DataOfCustomer> dataOfCustomers = new ArrayList<>();

    public Book() {
    }

    public List<Modal> getModal() {
        return modal;
    }

    public void setModal(List<Modal> modal) {
        this.modal = modal;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public List<Button> getButton() {
        return buttons;
    }

    public void setButton(List<Button> button) {
        this.buttons = button;
    }

    public List<DataOfCustomer> getDataOfCustomers() {
        return dataOfCustomers;
    }

    public void setDataOfCustomers(List<DataOfCustomer> dataOfCustomers) {
        this.dataOfCustomers = dataOfCustomers;
    }
}
