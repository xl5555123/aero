package com.tencent.aero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 音乐的风格，如：摇滚、流行、轻音乐等。
 *
 * @author larryxing
 */

@Entity
public class Style {

    /**
     * 风格的唯一标识，删除风格后再增加风格不会再有这个id的风格
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 风格的名称，更改后id不变，且以前绑定该风格的音乐的风格名随之改变
     * 风格不能重复
     */
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    protected Style() {

    }

    public Style(String name) {
        this.name = name;
        created = new Date();
        updated = new Date();

    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
