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

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  查询的字符串
     * </p>
     */
    private String searchText;

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  查询的标签列表
     * </p>
     */
    private List<LabelF> labels;

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  排序类型
     * </p>
     */
    private SortType sortType;

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  查询的笔记列表
     * </p>
     */
    private List<Note> notes;

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  事件类型
     * </p>
     */
    private FXMLNEvent event;

    /**
     * @since 2023/4/18 16:47
     * @description <p>
     *  未查询到笔记标志位
     * </p>
     */
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
