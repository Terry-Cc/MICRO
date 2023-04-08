package com.micro.jfxexe.handler;

import com.micro.common.util.chain.HandlerChain;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.common.SortType;
import com.micro.jfxexe.controller.view.SearchViewController;
import com.micro.jfxexe.domain.CellProperties;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.common.FXMLNEvent;
import com.micro.jfxexe.domain.SearchParam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author XiongJiaMin
 * @apiNote 搜索
 * @since 2022-11-18 12:04
 **/
@Component
public class SearchNoteEventHandler extends DefaultEventHandler {

    @Resource(name = "searchChainHandler")
    private HandlerChain searchChainHandler;

    @Override
    public void handle(FXMLNEvent event) {
        if (FXMLEventType.SEARCH_NOTE_TYPE.equalsType(event)) {
            logger.info("note search ------------------------------ start");
            SearchViewController viewController = viewFactory.getView(SearchViewController.class);
            // 查询内容
            String text = viewController.searchView_SearchText.getText();
            // 选择的标签
            ObservableList<Node> labels = viewController.searchView_SearchLabel.getChildren();
            // 排序类型
            SortType sortType = viewController.searchView_Sort.getSelectionModel().getSelectedItem();
            // 查询参数
            SearchParam searchParam = new SearchParam();
            searchParam.setSearchText(text);
            searchParam.setLabels(labels.stream().map(node -> (LabelF)node).collect(Collectors.toList()));
            searchParam.setSortType(sortType);
            searchParam.setEvent(event);
            // 查询
            searchChainHandler.process(searchParam);
            List<Note> notes = searchParam.getNotes();
            List<CellProperties> cells = new ArrayList<>();
            if (searchParam.isNone()) {
                Note none = new Note();
                none.setNoteText("The query content has no match, the following shows all the contents !");
                cells.add(new CellProperties(none));
            }
            if (!CommonUtils.isEmpty(notes)) {
                cells.addAll(notes.stream()
                        .map(CellProperties::new)
                        .collect(Collectors.toList()));
            }
            logger.info(String.join(" ", "note search ----- note quantity:", String.valueOf(cells.size())));
            viewController.searchView_SearchList.setItems(FXCollections.observableArrayList(cells));
            logger.info("note search ------------------------------ end");
        }
    }
}
