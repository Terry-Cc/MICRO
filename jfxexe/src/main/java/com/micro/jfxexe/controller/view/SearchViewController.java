package com.micro.jfxexe.controller.view;

import com.micro.jfxexe.annotation.FXMLEvent;
import com.micro.jfxexe.annotation.FXMLListener;
import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.common.FXMLListenerType;
import com.micro.jfxexe.common.SortType;
import com.micro.jfxexe.domain.CellProperties;
import com.micro.jfxexe.factory.cell.SearchListCellFactory;
import com.micro.jfxexe.handler.SearchNoteEventHandler;
import com.micro.jfxexe.handler.SortTypeChangeHandler;
import com.micro.jfxexe.listener.SearchTextChangeListener;
import de.felixroske.jfxsupport.FXMLController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 查询窗口
 * @since 2022-11-22 13:27
 **/
@FXMLController
public class SearchViewController extends BaseViewController {

    public FlowPane searchView;

    @FXMLListener(listenerType = FXMLListenerType.TEXT_PROPERTY, listener = SearchTextChangeListener.class)
    public TextField searchView_SearchText;
    
    @FXMLEvent(eventType = FXMLEventType.SEARCH_NOTE_TYPE, handler = SearchNoteEventHandler.class)
    public Button searchView_SearchButton;
    public ListView<CellProperties> searchView_SearchList;
    public FlowPane searchView_SearchLabel;

    @FXMLEvent(eventType = FXMLEventType.SORT_CHANGE_TYPE, handler = SortTypeChangeHandler.class)
    public ComboBox<SortType> searchView_Sort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        // 数据初始化
        searchView_SearchList.setPadding(new Insets(0, 0, 0, -5));
        // 工厂
        searchView_SearchList.setCellFactory((ListView<CellProperties> f) -> new SearchListCellFactory());
        // 排序类型
        searchView_Sort.getItems().addAll(SortType.DEFAULT, SortType.TIME_UP, SortType.TIME_DOWN);
        searchView_Sort.setValue(SortType.DEFAULT);
    }
}
