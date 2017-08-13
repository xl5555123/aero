package com.tencent.aero.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Music {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long attachedFileId;

    @Column(nullable = false)
    private Long totalTime;

    @Column(nullable = false)
    private Long chorusStart;

    @Column(nullable = false)
    private Long chorusEnd;

    @Column(nullable = false)
    private Date created;

    @Column(nullable = false)
    private Date updated;

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

    public Music() {

    }

    public Music(Music music) {
        this.id = music.getId();
        this.name = music.getName();
        this.attachedFileId = music.getAttachedFileId();
        this.totalTime = music.getTotalTime();
        this.chorusStart = music.getChorusStart();
        this.chorusEnd = music.getChorusEnd();
        this.created = music.getCreated();
        this.updated = music.getUpdated();
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

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Long getChorusStart() {
        return chorusStart;
    }

    public void setChorusStart(Long chorusStart) {
        this.chorusStart = chorusStart;
    }

    public Long getChorusEnd() {
        return chorusEnd;
    }

    public void setChorusEnd(Long chorusEnd) {
        this.chorusEnd = chorusEnd;
    }
}
