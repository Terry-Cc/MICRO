package com.micro.jfxexe.controller;

import com.micro.jfxexe.JFxApp;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote base window
 * @since 2022-11-24 13:41
 **/
public class BaseWindowController extends BaseController {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 窗口初始化
        Stage primaryStage = JFxApp.getStage();
        primaryStage.setWidth(1000);
        primaryStage.setResizable(false);
    }
}
