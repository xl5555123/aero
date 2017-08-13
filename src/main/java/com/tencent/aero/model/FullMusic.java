package com.tencent.aero.model;

import java.util.List;

public class FullMusic extends Music {
    private List<Style> styles;
    private List<Singer> singers;

    public FullMusic() {

    }

    public FullMusic(Music music) {
        super(music);
    }

    public List<Style> getStyles() {
        return styles;
    }

    public void setStyles(List<Style> styles) {
        this.styles = styles;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }

}
