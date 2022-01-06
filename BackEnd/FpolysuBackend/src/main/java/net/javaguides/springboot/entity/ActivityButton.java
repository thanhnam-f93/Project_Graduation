package net.javaguides.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "activity_button")
public class ActivityButton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "created_at")
    private Date CreatedAt = new Date();
    @Column(name = "from_url")
    private String fromUrl;

    @Column(name = "equipment")
    private Boolean equipment;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "languages")
    private String languages;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "id_button")
    private int buttonId;

    public ActivityButton(int buttonId) {
        this.buttonId = buttonId;
    }

    public ActivityButton(String fromUrl, int buttonId) {
        this.fromUrl = fromUrl;
        this.buttonId = buttonId;
    }
}
