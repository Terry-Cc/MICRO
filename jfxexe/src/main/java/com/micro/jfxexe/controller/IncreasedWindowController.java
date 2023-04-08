package com.micro.jfxexe.controller;

import com.micro.jfxexe.controller.view.IncreasedViewController;
import com.micro.jfxexe.controller.view.MenuViewController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 新增页面
 * @since 2022-11-22 16:24
 **/
@FXMLController
@SuppressWarnings("unused")
public class IncreasedWindowController extends BaseWindowController {

    public FlowPane increasedWindow;

    public MenuViewController menuViewController;

    public IncreasedViewController increasedViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
