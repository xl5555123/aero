package com.tencent.aero.model;

import javax.persistence.*;

@Entity
public class UserPlayed {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String uin;

    @Column(nullable = false)
    private Long musicId;

}
