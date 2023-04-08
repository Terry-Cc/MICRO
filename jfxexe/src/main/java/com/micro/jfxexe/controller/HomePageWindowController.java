package com.micro.jfxexe.controller;

import com.micro.jfxexe.controller.view.HomePageViewController;
import com.micro.jfxexe.controller.view.MenuViewController;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 主页
 * @since 2022-11-15 16:33
 **/
@FXMLController
@SuppressWarnings("unused")
public class HomePageWindowController extends BaseWindowController {

    public FlowPane homePageWindow;

    public MenuViewController menuViewController;

    public HomePageViewController homePageViewController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
