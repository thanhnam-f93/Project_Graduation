package net.javaguides.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "button_fake")
public class ButtonFake {

    @Id
    @Column(name = "id_button")
    private int id_button;

    @Column(name = "name_button")
    private String name_button;

    @Column(name = "color_text")
    private String color_text;

    @Column(name = "color_background")
    private String color_background;

    @Column(name = "color_icon")
    private String color_icon;

    @Column(name = "link")
    private String link;

    @Column(name = "icon")
    private String icon;

    public ButtonFake() {
    }

    public ButtonFake(int id_button, String name_button, String color_text, String color_background, String color_icon, String link, String icon) {
        this.id_button = id_button;
        this.name_button = name_button;
        this.color_text = color_text;
        this.color_background = color_background;
        this.color_icon = color_icon;
        this.link = link;
        this.icon = icon;
    }

    public int getId_button() {
        return id_button;
    }

    public void setId_button(int id_button) {
        this.id_button = id_button;
    }

    public String getName_button() {
        return name_button;
    }

    public void setName_button(String name_button) {
        this.name_button = name_button;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getColor_text() {
        return color_text;
    }

    public void setColor_text(String color_text) {
        this.color_text = color_text;
    }

    public String getColor_background() {
        return color_background;
    }

    public void setColor_background(String color_background) {
        this.color_background = color_background;
    }

    public String getColor_icon() {
        return color_icon;
    }

    public void setColor_icon(String color_icon) {
        this.color_icon = color_icon;
    }


}


