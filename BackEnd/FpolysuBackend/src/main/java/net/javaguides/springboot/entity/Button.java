package net.javaguides.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@javax.persistence.Table(name = "button")
public class Button {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "type_button")
    private String typeButton;

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

    @Column(name = "caption_content")
    private String captionContent;

    @ManyToOne
    @JoinColumn(name = "id_menu")
    private Menu menu;
    @JsonIgnore
    @OneToMany(mappedBy = "buttons", fetch = FetchType.LAZY)
    private Collection<Modal> modal;


    public Button() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeButton() {
        return typeButton;
    }

    public void setTypeButton(String typeButton) {
        this.typeButton = typeButton;
    }

    public String getName_button() {
        return name_button;
    }

    public void setName_button(String name_button) {
        this.name_button = name_button;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Collection<Modal> getModal() {
        return modal;
    }


    public void setModal(Collection<Modal> modal) {
        this.modal = modal;
    }

    public String getCaptionContent() {
        return captionContent;
    }

    public void setCaptionContent(String captionContent) {
        this.captionContent = captionContent;
    }

}
