package com.micro.jfxexe.common;

import javafx.event.EventType;

/**
 * @author XiongJiaMin
 * @apiNote enum
 * @since 2022-11-24 14:28
 **/
@SuppressWarnings("unused")
public enum FXMLEventType {

    // 默认
    DEFAULT_TYPE(new EventType<>("default")),
    // 搜索
    SEARCH_NOTE_TYPE(new EventType<>("search-note")),
    // 新增
    INCREASED_NOTE_TYPE(new EventType<>("increased-note")),
    // 新增分词
    INCREASED_CUT_NOTE_TYPE(new EventType<>("increased-cut-note")),
    // 排序
    SORT_CHANGE_TYPE(new EventType<>("sort-change-note"));

    public final EventType<FXMLNEvent> type;

    FXMLEventType(EventType<FXMLNEvent> type) {
        this.type = type;
    }

    public FXMLNEvent getEvent() {
        return new FXMLNEvent(this.type);
    }

    public boolean equalsType(FXMLNEvent var1) {
        return var1 != null && this.type.getName().equals(var1.getEventType().getName());
    }
}
