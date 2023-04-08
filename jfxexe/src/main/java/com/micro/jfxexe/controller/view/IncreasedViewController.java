package com.micro.jfxexe.controller.view;

import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.annotation.FXMLEvent;
import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.common.NodeUtils;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.handler.IncreasedNoteEventHandler;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 新增页面
 * @since 2022-11-22 16:23
 **/
@FXMLController
public class IncreasedViewController extends BaseViewController {

    public FlowPane increasedView;
    public FlowPane increasedView_FlowPane_First;
    public Label increasedView_FlowPane_First_Label_First;
    public TextArea increasedView_FlowPane_First_TextArea;
    public Label increasedView_FlowPane_First_Label_Second;
    public FlowPane increasedView_FlowPane_First_FlowPane;
    public FlowPane increasedView_FlowPane_Second;

    @FXMLEvent(eventType = FXMLEventType.INCREASED_NOTE_TYPE, handler = IncreasedNoteEventHandler.class)
    public Button increasedView_FlowPane_Second_Button_First;
    @FXMLEvent(eventType = FXMLEventType.INCREASED_CUT_NOTE_TYPE, handler = IncreasedNoteEventHandler.class)
    public Button increasedView_FlowPane_Second_Button_Second;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        // 新增标签
        LabelF addLabel = new LabelF("✚");
        TextField genLabel = new TextField();
        genLabel.setPromptText("enter labelName");
        genLabel.setPrefWidth(150);
        addLabel.setStyle("-fx-padding: 5, 0, 0, 0");
        addLabel.onMouseClickedProperty().setValue(event -> {
            addLabel.setGraphic(addLabel.getGraphic() == null ? genLabel : null);
            String labelText = genLabel.getText();
            if (!CommonUtils.isEmpty(labelText)) {
                LabelF newLabel = NodeUtils.creatLabel(labelText);
                LabelF delLabel = new LabelF("✖");
                delLabel.setTextFill(Color.WHITE);
                delLabel.onMouseClickedProperty().setValue(e -> increasedView_FlowPane_First_FlowPane.getChildren().remove(newLabel));
                newLabel.onMouseEnteredProperty().setValue(e -> newLabel.setGraphic(delLabel));
                newLabel.onMouseExitedProperty().setValue(e -> newLabel.setGraphic(null));
                increasedView_FlowPane_First_FlowPane.getChildren().add(newLabel);
                genLabel.setText(null);
            }
        });
        increasedView_FlowPane_First_FlowPane.onMouseExitedProperty().setValue(event -> {
            addLabel.setGraphic(null);
            genLabel.setText(null);
        });
        increasedView_FlowPane_First_FlowPane.getChildren().add(addLabel);
    }
}
