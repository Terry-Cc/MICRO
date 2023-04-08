package com.micro.jfxexe.common;

import com.micro.common.util.other.CommonUtils;
import com.micro.common.util.other.FileUtils;
import com.micro.jfxexe.domain.AlertF;
import com.micro.jfxexe.domain.LabelF;
import com.micro.jfxexe.domain.NoteLabel;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author XiongJiaMin
 * @apiNote 工具
 * @since 2022-11-17 13:46
 **/
@SuppressWarnings("unused")
public class NodeUtils {

    public static final String CANCEL = "cancel";

    public static final String APPLY = "apply";

    public static final String LABEL_DEL_GRAPHIC = "label_del_graphic";

    public static void confirmation(String msg) {
        alert(Alert.AlertType.CONFIRMATION, msg);
    }

    public static void none(String msg) {
        alert(Alert.AlertType.NONE, msg);
    }

    public static void alert(Alert.AlertType alertType, String msg) {
        Alert alert = new Alert(alertType, msg);
        alert.showAndWait();
    }

    public static String alertAndGet(String title, String headerText, String contentText, Stage stage) {
        AlertF<String> alert = creatAlert(title, headerText, contentText, stage, null);
        TextField textField = new TextField();
        textField.setPrefSize(300, 12);
        alert.setGraphic(textField);
        ButtonType cancel = new ButtonType(CANCEL, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType apply = new ButtonType(APPLY, ButtonBar.ButtonData.APPLY);
        alert.getButtonTypes().addAll(cancel, apply);
        alert.onCloseRequestProperty().setValue(event -> alert.setResultF(textField.getText()));
        alert.showAndWait();
        ButtonType buttonType = alert.getResult();
        if (CANCEL.equals(buttonType.getText())) {
            return null;
        }
        return alert.getResultF();
    }

    public static <T>AlertF<T> creatAlert(String title, String headerText, String contentText, Stage stage, Node graphic) {
        AlertF<T> alert = new AlertF<>(Alert.AlertType.NONE);
        if (!CommonUtils.isEmpty(title)) {
            alert.setTitle(title);
        }
        if (!CommonUtils.isEmpty(headerText)) {
            alert.setHeaderText(headerText);
        }
        if (!CommonUtils.isEmpty(contentText)) {
            alert.setContentText(contentText);
        }
        if (graphic != null) {
            alert.setGraphic(graphic);
        }
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        alert.setWidth(stageWidth / 2);
        alert.setHeight(stageHeight / 2);
        return alert;
    }

    public static LabelF creatLabel(NoteLabel label) {
        return creatLabel(label.getLabelName(), label.getLabelId());
    }

    public static LabelF creatLabel(String labelName) {
        return creatLabel(labelName, null);
    }

    public static LabelF creatLabel(String labelName, String labelId) {
        if (CommonUtils.isEmpty(labelName)) {
            return null;
        }
        LabelF labelF = new LabelF();
        labelF.setLabelName(labelName);
        if (CommonUtils.isEmpty(labelId)) {
            labelF.setLabelId(creatId(labelName));
        } else {
            labelF.setLabelId(labelId);
        }
        labelF.setText(labelName);
        labelF.setPrefHeight(25);
        labelF.setTextFill(Color.WHITE);
        labelF.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        labelF.setBorder(new Border(new BorderStroke(Color.WHITE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        labelF.setAlignment(Pos.CENTER);
        labelF.setStyle("-fx-text-alignment: center; -fx-padding: 5, 0, 0, 0");
        labelF.setLayoutY(5);
        return labelF;
    }

    public static String creatId(String text) {
        return FileUtils.getHashByStr(text, "SHA-256");
    }
}
