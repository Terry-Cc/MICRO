package com.micro.jfxexe.listener;

import javafx.beans.value.ObservableValue;
import org.springframework.stereotype.Component;


/**
 * @author XiongJiaMin
 * @apiNote 搜索文本监听
 * @since 2022-11-16 17:05
 **/
@Component
public class SearchTextChangeListener extends DefaultEventListener<String> {

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        fireActualSearchNote();
    }
}
