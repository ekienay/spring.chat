package com.unurnment.chat.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "msg")
public class Massage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String tag;

    @Column(name = "crr_dt")
    private String current_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String fileName;

    public String getUserName(){
        return user != null ? user.getUsername() : "-empty-";
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

    public Massage() {
    }

    public Massage(String text, String tag, User user) {
        this.user = user;
        this.text = text;
        this.tag = tag;
    }
}
