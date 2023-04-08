package com.micro.jfxexe.handler;

import com.micro.jfxexe.common.FXMLEventType;
import com.micro.jfxexe.common.FXMLNEvent;
import org.springframework.stereotype.Component;

/**
 * @author XiongJiaMin
 * @apiNote 排序改变监听
 * @since 2023-01-03 10:12
 **/
@Component
public class SortTypeChangeHandler extends DefaultEventHandler {

    @Override
    public void handle(FXMLNEvent event) {
        if (FXMLEventType.SORT_CHANGE_TYPE.equalsType(event)) {
            fireSearchNote();
        }
    }
}
