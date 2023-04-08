package com.micro.jfxexe.controller;

import com.micro.jfxexe.controller.view.MenuViewController;
import com.micro.jfxexe.controller.view.SearchViewController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author XiongJiaMin
 * @apiNote 查询窗口
 * @since 2022-11-22 14:47
 **/
@FXMLController
@SuppressWarnings("unused")
public class SearchWindowController extends BaseWindowController {

    public FlowPane mainWindow;

    public MenuViewController menuViewController;

    public SearchViewController searchViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
