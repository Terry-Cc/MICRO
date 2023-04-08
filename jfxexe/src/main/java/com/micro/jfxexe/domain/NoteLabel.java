package com.micro.jfxexe.domain;


import java.time.LocalDateTime;

/**
 * @author XiongJiaMin
 * @apiNote 笔记标签
 * @since 2022-11-19 21:12
 **/
@SuppressWarnings("unused")
public class NoteLabel extends NoteBase {

    private static final long serialVersionUID = -8922020646428057975L;

    private String labelId;

    private String labelName;

    private Integer searchTime;

    // 是否查询文本
    private boolean isSearchText;

    public boolean isSearchText() {
        return isSearchText;
    }

    public void setSearchText(boolean searchText) {
        isSearchText = searchText;
    }

    public void addTime() {
        this.searchTime = this.searchTime == null ? 1 : this.searchTime + 1;
    }

    public Integer getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Integer searchTime) {
        this.searchTime = searchTime;
    }

    public NoteLabel() {
        this.setCreatedDate(LocalDateTime.now());
        this.setUpdatedDate(LocalDateTime.now());
        this.searchTime = 0;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }
}
