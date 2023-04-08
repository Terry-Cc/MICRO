package com.micro.jfxexe;

import com.micro.jfxexe.factory.IStaticFactorySupport;
import com.micro.jfxexe.view.HomePageWindowView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JFxApp extends AbstractJavaFxApplicationSupport {

    public static void main(String[] args) {
        launch(JFxApp.class, HomePageWindowView.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // 同步更新
        IStaticFactorySupport.noteFactory.setSync(true);
        // 开启缓存
        IStaticFactorySupport.noteFactory.setCache(true);
        super.start(stage);
    }
}
