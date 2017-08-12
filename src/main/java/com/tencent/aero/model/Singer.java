package com.tencent.aero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 歌手
 *
 * @author larryxing
 */
@Entity
public class Singer {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 歌手名
     */
    @Column(nullable = false)
    private String name;

    /**
     * 歌手头像文件
     */
    @Column(nullable = false)
    private Long attachedFileId;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

    /**
     * 是否为男歌手
     */
    @Column(nullable = false)
    private boolean isMan;

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAttachedFileId() {
        return attachedFileId;
    }

    public void setAttachedFileId(Long attachedFileId) {
        this.attachedFileId = attachedFileId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
