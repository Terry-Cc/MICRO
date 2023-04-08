package com.micro.jfxexe.factory.cell;

import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.common.NodeUtils;
import com.micro.jfxexe.controller.view.SearchViewController;
import com.micro.jfxexe.domain.CellProperties;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.NoteLabel;
import com.micro.jfxexe.factory.IStaticFactorySupport;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 查询列表处理工厂
 * @since 2022-11-18 17:04
 **/
public class SearchListCellFactory extends ListCell<CellProperties> implements IStaticFactorySupport {

    protected static Logger logger = LoggerFactory.getLogger(SearchListCellFactory.class);

    @Override
    protected void updateItem(CellProperties item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            FlowPane flowPane = new FlowPane();
            flowPane.setBorder(new Border(new BorderStroke(Color.rgb(177, 177, 177), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            FlowPane flowPaneH = new FlowPane();
            flowPaneH.setPrefHeight(75);
            TextArea textArea = new TextArea(item.note.getNoteText());
            textArea.setPrefHeight(100);
            textArea.setPrefWidth(968);
            textArea.setEditable(false);
            flowPaneH.getChildren().add(textArea);
            flowPane.getChildren().add(flowPaneH);
            FlowPane flowPaneM = new FlowPane();
            flowPaneM.setPrefWidth(968);
            if (noteFactory.existNote(item.getNote())) {
                String noteId = item.getNote().getNoteId();
                List<NoteLabel> noteLabels = noteFactory.listNoteLabelByNoteId(noteId);
                SearchViewController viewController = viewFactory.getView(SearchViewController.class);
                if (!CommonUtils.isEmpty(noteLabels)) {
                    for (NoteLabel noteLabel : noteLabels) {
                        LabelF labelF = NodeUtils.creatLabel(noteLabel);
                        LabelF delLabel = new LabelF("✖");
                        delLabel.setTextFill(Color.WHITE);
                        delLabel.onMouseClickedProperty().setValue(event -> {
                            noteFactory.consumption(noteLabel);
                            fireSearchNote();
                        });
                        labelF.onMouseEnteredProperty().setValue(event -> labelF.setGraphic(delLabel));
                        labelF.onMouseExitedProperty().setValue(event -> labelF.setGraphic(null));
                        labelF.onMouseClickedProperty().setValue(event -> {
                            if (noteFactory.existNote(noteLabel)) {
                                LabelF labelSearch = NodeUtils.creatLabel(noteLabel);
                                LabelF delLabelSearch = new LabelF("✖");
                                delLabelSearch.setTextFill(Color.WHITE);
                                delLabelSearch.onMouseClickedProperty().setValue(e -> {
                                    viewController.searchView_SearchLabel.getChildren().remove(labelSearch);
                                    fireSearchNote();
                                });
                                labelSearch.onMouseEnteredProperty().setValue(e -> labelSearch.setGraphic(delLabelSearch));
                                labelSearch.onMouseExitedProperty().setValue(e -> labelSearch.setGraphic(null));
                                ObservableList<Node> labels = viewController.searchView_SearchLabel.getChildren();
                                boolean flag = true;
                                if (!CommonUtils.isEmpty(labels)) {
                                    for (Node label : labels) {
                                        LabelF labelN = (LabelF) label;
                                        if (labelN.equals(labelSearch)) {
                                            flag = false;
                                            break;
                                        }
                                    }
                                }
                                if (flag) {
                                    viewController.searchView_SearchLabel.getChildren().add(labelSearch);
                                    fireSearchNote();
                                }
                            }
                        });
                        flowPaneM.getChildren().add(labelF);
                    }
                }
                LabelF addLabel = new LabelF("✚");
                TextField genLabel = new TextField();
                genLabel.setPromptText("enter labelName");
                genLabel.setPrefWidth(150);
                addLabel.setStyle("-fx-padding: 5, 0, 0, 0");
                addLabel.onMouseClickedProperty().setValue(event -> {
                    addLabel.setGraphic(addLabel.getGraphic() == null ? genLabel : null);
                    String labelText = genLabel.getText();
                    if (!CommonUtils.isEmpty(labelText)) {
                        NoteLabel newNoteLabel = new NoteLabel();
                        newNoteLabel.setLabelName(labelText);
                        newNoteLabel.setLabelId(NodeUtils.creatId(labelText));
                        noteFactory.production(newNoteLabel);
                        noteFactory.addNoteRelationship(noteId, newNoteLabel.getLabelId());
                        genLabel.setText(null);
                        fireSearchNote();
                    }
                });
                flowPane.onMouseExitedProperty().setValue(event -> {
                    addLabel.setGraphic(null);
                    genLabel.setText(null);
                });
                flowPaneM.getChildren().add(addLabel);
            }
            flowPane.getChildren().add(flowPaneM);
            setGraphic(flowPane);
        } else {
            setGraphic(null);
            setText(null);
        }
    }
}
