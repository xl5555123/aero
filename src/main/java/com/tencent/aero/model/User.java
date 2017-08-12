package com.tencent.aero.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String uin;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private Long singerId;

    @Column(nullable = false)
    private Long styleId;

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSingerId(Long singerId) {
        this.singerId = singerId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public Long getId() {
        return id;
    }

    public String getUin() {
        return uin;
    }

    public String getNickname() {
        return nickname;
    }

    public Long getSingerId() {
        return singerId;
    }

    public Long getStyleId() {
        return styleId;
    }
}
