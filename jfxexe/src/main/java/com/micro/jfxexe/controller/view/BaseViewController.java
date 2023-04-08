package com.micro.jfxexe.controller.view;

import com.micro.common.util.other.ClassLocator;
import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.JFxApp;
import com.micro.jfxexe.annotation.FXMLEvent;
import com.micro.jfxexe.annotation.FXMLJump;
import com.micro.jfxexe.annotation.FXMLListener;
import com.micro.jfxexe.common.FXMLListenerType;
import com.micro.jfxexe.common.SortType;
import com.micro.jfxexe.controller.BaseController;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote base view
 * @since 2022-11-24 13:43
 **/
public class BaseViewController extends BaseController implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
        this.context = context;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL location, ResourceBundle resources) {
        Map<Object, List<Annotation>> fieldAnnotationMap = ClassLocator.getFieldsToAnnotation(this);
        for (Map.Entry<Object, List<Annotation>> entry : fieldAnnotationMap.entrySet()) {
            Object node = entry.getKey();
            List<Annotation> annotations = entry.getValue();
            if (node != null && !CommonUtils.isEmpty(annotations)) {
                for (Annotation annotation : annotations) {
                    // 事件
                    if (annotation instanceof FXMLEvent) {
                        FXMLEvent event = (FXMLEvent) annotation;
                        if (node instanceof Button) {
                            Button button = (Button) node;
                            button.addEventHandler(event.eventType().type, context.getBean(event.handler()));
                            button.setOnMouseClicked(e -> button.fireEvent(event.eventType().getEvent()));
                        } else if (node instanceof ComboBox) {
                            ComboBox<SortType> comboBox = (ComboBox<SortType>) node;
                            comboBox.addEventHandler(event.eventType().type, context.getBean(event.handler()));
                            comboBox.setOnAction(e -> comboBox.fireEvent(event.eventType().getEvent()));
                        }
                    }
                    // 页面跳转
                    if (annotation instanceof FXMLJump) {
                        FXMLJump event = (FXMLJump) annotation;
                        if (node instanceof Menu) {
                            Menu menu = (Menu) node;
                            Label page = new Label(menu.getText());
                            page.setOnMouseClicked(act -> JFxApp.showView(event.jumpView()));
                            menu.setGraphic(page);
                            menu.setText(null);
                        } else if (node instanceof Button) {
                            Button node_ = (Button) node;
                            node_.setOnMouseClicked(e -> JFxApp.showView(event.jumpView()));
                        }
                    }
                    // 监听器
                    if (annotation instanceof FXMLListener) {
                        FXMLListener listener = (FXMLListener) annotation;
                        FXMLListenerType listenerType = listener.listenerType();
                        if (node instanceof TextField) {
                            TextField textField = (TextField) node;
                            if (FXMLListenerType.TEXT_PROPERTY.equals(listenerType)) {
                                textField.textProperty().addListener((ChangeListener<String>) context.getBean(listener.listener()));
                            }
                        }
                    }
                }
            }
        }
        // 暂存
        viewFactory.production(this);
    }
}
