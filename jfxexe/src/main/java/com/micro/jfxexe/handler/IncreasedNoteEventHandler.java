package com.micro.jfxexe.handler;

import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.common.FXMLNEvent;
import com.micro.jfxexe.common.IKAnalyzerUtil;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.common.NodeUtils;
import com.micro.jfxexe.controller.view.IncreasedViewController;
import com.micro.jfxexe.domain.Note;
import com.micro.jfxexe.domain.NoteLabel;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongJiaMin
 * @apiNote 新增
 * @since 2022-11-22 17:49
 **/
@Component
public class IncreasedNoteEventHandler extends DefaultEventHandler {

    @Override
    public void handle(FXMLNEvent event) {
        IncreasedViewController increasedViewController = viewFactory.getView(IncreasedViewController.class);
        if (FXMLEventType.INCREASED_NOTE_TYPE.equalsType(event)) {
            // 笔记
            String text = increasedViewController.increasedView_FlowPane_First_TextArea.getText();
            // 标签
            ObservableList<Node> children = increasedViewController.increasedView_FlowPane_First_FlowPane.getChildren();
            if (CommonUtils.isEmpty(text) && children.size() == 1) {
                NodeUtils.confirmation("your note and labels are all empty, are you kiding me ?");
                return;
            }
            Note note = null;
            List<NoteLabel> labels = null;
            // 生成笔记
            if (!CommonUtils.isEmpty(text)) {
                note = new Note();
                note.setNoteId(NodeUtils.creatId(text));
                note.setNoteText(text);
                noteFactory.production(note);
            }
            // 生成标签
            if (children.size() > 1) {
                labels = new ArrayList<>();
                NoteLabel noteLabel;
                for (Node node : children) {
                    LabelF label = (LabelF) node;
                    if (!CommonUtils.isEmpty(label.getLabelId())) {
                        noteLabel = new NoteLabel();
                        noteLabel.setLabelId(label.getLabelId());
                        noteLabel.setLabelName(label.getLabelName());
                        labels.add(noteLabel);
                    }
                }
                noteFactory.production(labels);
            }
            if (note != null) {
                NoteLabel timeLabel = new NoteLabel();
                String time = note.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                timeLabel.setLabelId(NodeUtils.creatId(time));
                timeLabel.setLabelName(time);
                noteFactory.production(timeLabel);
                labels = labels == null ? new ArrayList<>() : labels;
                labels.add(timeLabel);
            }
            // 生成笔记和标签的映射关系
            noteFactory.addNoteRelationship(note, labels);
            logger.info(String.join(" ", "IncreasedNoteEventHandler ----- add success note:", text, "labels quantity is", String.valueOf(children.size())));
            increasedViewController.increasedView_FlowPane_First_TextArea.clear();
            increasedViewController.increasedView_FlowPane_First_FlowPane.getChildren().remove(1, children.size());
            NodeUtils.confirmation("add success");
        } else if (FXMLEventType.INCREASED_CUT_NOTE_TYPE.equalsType(event)) {
            // 笔记
            String text = increasedViewController.increasedView_FlowPane_First_TextArea.getText();
            if (CommonUtils.isEmpty(text)) {
                NodeUtils.confirmation("none note to cut !");
                return;
            }
            List<String> stringList = IKAnalyzerUtil.cut(text);
            if (!CommonUtils.isEmpty(stringList)) {
                for (String labelName : stringList) {
                    LabelF newLabel = NodeUtils.creatLabel(labelName);
                    LabelF delLabel = new LabelF("✖");
                    delLabel.setTextFill(Color.WHITE);
                    delLabel.onMouseClickedProperty().setValue(e -> increasedViewController.increasedView_FlowPane_First_FlowPane.getChildren().remove(newLabel));
                    newLabel.onMouseEnteredProperty().setValue(e -> newLabel.setGraphic(delLabel));
                    newLabel.onMouseExitedProperty().setValue(e -> newLabel.setGraphic(null));
                    ObservableList<Node> labels = increasedViewController.increasedView_FlowPane_First_FlowPane.getChildren();
                    boolean flag = true;
                    for (Node node : labels) {
                        LabelF label = (LabelF) node;
                        if (!CommonUtils.isEmpty(label.getLabelId()) && label.equals(newLabel)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        increasedViewController.increasedView_FlowPane_First_FlowPane.getChildren().add(newLabel);
                    }
                }
            }
            logger.info(String.join(" ", "IncreasedNoteEventHandler ----- cut note success:", text, "labels quantity is", String.valueOf(stringList.size())));
            NodeUtils.confirmation("cut note success");
        }
    }
}