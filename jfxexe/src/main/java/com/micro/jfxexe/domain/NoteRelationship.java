package com.micro.jfxexe.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author XiongJiaMin
 * @apiNote 笔记关系映射
 * @since 2022-11-19 21:07
 **/
@SuppressWarnings("unused")
public class NoteRelationship extends NoteBase {

    private static final long serialVersionUID = 3625195333949406580L;

    private Map<String, List<String>> noteIdToLabelId;

    private Map<String, List<String>> labelIdToNoteId;

    public NoteRelationship() {}

    public NoteRelationship(LocalDateTime createdDate, LocalDateTime updatedDate, Map<String, List<String>> noteIdToLabelId, Map<String, List<String>> labelIdToNoteId) {
        super(createdDate, updatedDate);
        this.noteIdToLabelId = noteIdToLabelId;
        this.labelIdToNoteId = labelIdToNoteId;
    }

    public Map<String, List<String>> getNoteIdToLabelId() {
        return noteIdToLabelId;
    }

    public void setNoteIdToLabelId(Map<String, List<String>> noteIdToLabelId) {
        this.noteIdToLabelId = noteIdToLabelId;
    }

    public Map<String, List<String>> getLabelIdToNoteId() {
        return labelIdToNoteId;
    }

    public void setLabelIdToNoteId(Map<String, List<String>> labelIdToNoteId) {
        this.labelIdToNoteId = labelIdToNoteId;
    }
}
