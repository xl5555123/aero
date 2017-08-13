package com.tencent.aero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserMusic {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String uin;

    @Column(nullable = false)
    private Long musicId;

    @Column(nullable = false)
    private Boolean tag;

    public UserMusic(String uin, Long musicId, Boolean tag) {
        this.uin = uin;
        this.musicId = musicId;
        this.tag = tag;
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
