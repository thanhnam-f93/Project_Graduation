package net.javaguides.springboot.entity;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "modal")
public class Modal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "input_name")
    private String inputName;

    @Column(name = "input_value")
    private String inputValue;

    @ManyToOne
    @JoinColumn(name = "id_dataofcustomer")
    private DataOfCustomer dataOfCustomer;
    @ManyToOne
    @JoinColumn(name = "id_button")

    private Button buttons;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputValue() {
        return inputValue;
    }

    public void setInputValue(String inputValue) {
        this.inputValue = inputValue;
    }

    public Button getButtons() {
        return buttons;
    }

    public void setButtons(Button buttons) {
        this.buttons = buttons;
    }

    public DataOfCustomer getDataOfCustomer() {
        return dataOfCustomer;
    }

    public void setDataOfCustomer(DataOfCustomer dataOfCustomer) {
        this.dataOfCustomer = dataOfCustomer;
    }
}
