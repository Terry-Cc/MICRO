package com.micro.jfxexe.domain;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author XiongJiaMin
 * @apiNote 笔记存储
 * @since 2022-11-21 14:08
 **/
@SuppressWarnings("unused")
public class NoteCollection extends NoteBase {

    private static final long serialVersionUID = 9133464110578051991L;

    private Map<String, Note> notes;

    private Map<String, NoteLabel> noteLabels;

    public NoteCollection () {}

    public NoteCollection(LocalDateTime createdDate, LocalDateTime updatedDate, Map<String, Note> notes, Map<String, NoteLabel> noteLabels) {
        super(createdDate, updatedDate);
        this.notes = notes;
        this.noteLabels = noteLabels;
    }

    public Map<String, Note> getNotes() {
        return notes;
    }

    public void setNotes(Map<String, Note> notes) {
        this.notes = notes;
    }

    public Map<String, NoteLabel> getNoteLabels() {
        return noteLabels;
    }

    public void setNoteLabels(Map<String, NoteLabel> noteLabels) {
        this.noteLabels = noteLabels;
    }
}
