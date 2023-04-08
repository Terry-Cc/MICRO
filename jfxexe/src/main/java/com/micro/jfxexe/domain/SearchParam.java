package com.micro.jfxexe.domain;

import com.micro.common.util.chain.ChainParam;
import com.micro.jfxexe.common.FXMLNEvent;
import com.micro.jfxexe.common.SortType;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 搜索参数
 * @since 2023-01-04 16:13
 **/
@SuppressWarnings("unused")
public class SearchParam extends ChainParam {

    private String searchText;

    private List<LabelF> labels;

    private SortType sortType;

    private List<Note> notes;

    private FXMLNEvent event;

    // 未查询到笔记标志位
    private boolean isNone;

    public FXMLNEvent getEvent() {
        return event;
    }

    public void setEvent(FXMLNEvent event) {
        this.event = event;
    }

    public void setNone(boolean none) {
        isNone = none;
    }

    public boolean isNone() {
        return isNone;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<LabelF> getLabels() {
        return labels;
    }

    public void setLabels(List<LabelF> labels) {
        this.labels = labels;
    }

    public SortType getSortType() {
        return sortType;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }
}
