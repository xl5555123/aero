package com.tencent.aero.model;

import javax.persistence.*;

@Entity
public class UserMusic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String uin;

    @Column(nullable = false)
    private Long musicId;

    @Column(nullable = false)
    private Boolean tag;

    public Long getId() {
        return id;
    }

    public UserMusic(String uin, Long musicId, Boolean tag) {
        this.uin = uin;
        this.musicId = musicId;
        this.tag = tag;
    }

    public  UserMusic(){

    }
    public String getUin() {
        return uin;
    }

    public Long getMusicId() {
        return musicId;
    }

    public Boolean getTag() {
        return tag;
    }

    public void setTag(Boolean tag) {
        this.tag = tag;
    }
}
