package com.unurnment.chat.model;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Massage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String tag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private String fileName;

    public String getUserName(){
        return user != null ? user.getUsername() : "-empty-";
    }

    public Massage() {
    }

    public Massage(String text, String tag, User user) {
        this.user = user;
        this.text = text;
        this.tag = tag;
    }
}
