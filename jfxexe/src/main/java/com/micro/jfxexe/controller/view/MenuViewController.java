package com.micro.jfxexe.controller.view;

import com.micro.jfxexe.annotation.FXMLJump;
import com.micro.jfxexe.view.HomePageWindowView;
import com.micro.jfxexe.view.IncreasedWindowView;
import com.micro.jfxexe.view.SearchWindowView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author XiongJiaMin
 * @apiNote 菜单
 * @since 2022-11-22 14:20
 **/
@FXMLController
public class MenuViewController extends BaseViewController {

    public FlowPane menuView;
    public MenuBar menuView_MenuBar;

    @FXMLJump(jumpView = HomePageWindowView.class)
    public Menu menuView_MenuBar_HomePage;

    @FXMLJump(jumpView = SearchWindowView.class)
    public Menu menuView_MenuBar_Search;

    @FXMLJump(jumpView = IncreasedWindowView.class)
    public Menu menuView_MenuBar_Increased;
    public Menu menuView_MenuBar_File;
    public MenuItem menuView_MenuBar_File_Open;
    public Menu menuView_MenuBar_Edit;
    public MenuItem menuView_MenuBar_Edit_FrontSize;
    public Menu menuView_MenuBar_Help;
    public MenuItem menuView_MenuBar_Help_About;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
    }
}
