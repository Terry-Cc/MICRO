package com.micro.jfxexe.controller.view;

import com.micro.jfxexe.annotation.FXMLJump;
import com.micro.jfxexe.view.SearchWindowView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 主页
 * @since 2022-11-22 15:19
 **/
@FXMLController
public class HomePageViewController extends BaseViewController {

    public FlowPane homePageView;
    public FlowPane homePageView_FlowPane_First;

    @FXMLJump(jumpView = SearchWindowView.class)
    public Button homePageView_Button_First;
    public FlowPane homePageView_FlowPane_Second;
    public Label homePageView_FlowPane_Second_Label_First;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
