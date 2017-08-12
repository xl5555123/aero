package com.tencent.aero.model;

import javax.persistence.*;

@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    public File() {

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
}
