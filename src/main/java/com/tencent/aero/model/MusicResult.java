package com.tencent.aero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class MusicResult {

    private Long id;
    /*
    title:		<字符串>	// 歌曲名
    artist:		<字符串>	// 歌手名
    album:		<字符串>	// 专辑名
    url:		<字符串>	// 歌曲网络位置 url
    cover_url:	<字符串>	// 歌曲封面网络位置 url
    favourite:*/
    private String title;

    private String artist;

    private String url;

    private String cover_url;

    private Boolean favourite;

    private Long chorusStart;


    private Long chorusEnd;

    public MusicResult(UserMusic userMusic, Music music) {
        this.id = userMusic.getMusicId();
        this.title = music.getName();
        this.favourite = userMusic.getTag();
        this.url = String.format("/api/files/%d", music.getAttachedFileId());
        this.chorusStart = music.getChorusStart();
        this.chorusEnd = music.getChorusEnd();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
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
