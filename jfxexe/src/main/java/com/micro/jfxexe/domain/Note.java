package com.micro.jfxexe.domain;

import java.time.LocalDateTime;

/**
 * @author XiongJiaMin
 * @apiNote 笔记
 * @since 2022-11-19 18:04
 **/
@SuppressWarnings("unused")
public class Note extends NoteBase {

    private static final long serialVersionUID = 6065013264022620248L;

    private String noteId;

    private String noteText;

    private Integer searchTime;

    public void addTime() {
        this.searchTime = searchTime + 1;
    }

    public Integer getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Integer searchTime) {
        this.searchTime = searchTime;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Note() {
        this.setCreatedDate(LocalDateTime.now());
        this.setUpdatedDate(LocalDateTime.now());
        this.searchTime = 0;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}
