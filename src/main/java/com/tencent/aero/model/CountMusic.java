package com.tencent.aero.model;

import javax.persistence.*;

@Entity
public class CountMusic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int count;

    public Long getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CountMusic(int count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CountMusic() {
    }
}
