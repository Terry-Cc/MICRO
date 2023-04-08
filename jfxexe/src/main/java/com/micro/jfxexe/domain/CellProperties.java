package com.micro.jfxexe.domain;

import java.io.Serializable;

/**
 * @author XiongJiaMin
 * @apiNote 列表参数
 * @since 2022-11-18 17:09
 **/
@SuppressWarnings("unused")
public class CellProperties implements Serializable {

    private static final long serialVersionUID = 9165627757424981460L;

    public Note note;

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public CellProperties() {}

    public CellProperties(Note note) {
        this.note = note;
    }
}
