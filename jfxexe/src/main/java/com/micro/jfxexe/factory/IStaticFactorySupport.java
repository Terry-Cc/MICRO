package com.micro.jfxexe.factory;

import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.controller.view.SearchViewController;

/**
 * @author XiongJiaMin
 * @apiNote interface
 * @since 2022-11-23 16:16
 **/
public interface IStaticFactorySupport {

    DefaultNoteFactory noteFactory = DefaultNoteFactory.getInstance();

    DefaultFXMLViewFactory viewFactory = DefaultFXMLViewFactory.getInstance();

    /**
     * @since 2023/4/18 16:53
     * @description <p>
     *  激活查询事件
     * </p>
     */
    default void fireSearchNote() {
        SearchViewController searchView = viewFactory.getView(SearchViewController.class);
        if (searchView != null) {
            searchView.searchView_SearchButton.fireEvent(FXMLEventType.SEARCH_NOTE_TYPE.getEvent());
        }
    }

    /**
     * @since 2023/4/18 16:53
     * @description <p>
     *  激活查询事件(实时)
     * </p>
     */
    default void fireActualSearchNote() {
        SearchViewController searchView = viewFactory.getView(SearchViewController.class);
        if (searchView != null) {
            searchView.searchView_SearchButton.fireEvent(FXMLEventType.SEARCH_NOTE_TYPE.getEvent().setActualTime(true));
        }
    }
}
