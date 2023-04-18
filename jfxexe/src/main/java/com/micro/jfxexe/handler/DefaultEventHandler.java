package com.micro.jfxexe.handler;

import com.micro.jfxexe.common.FXMLNEvent;
import com.micro.jfxexe.factory.IStaticFactorySupport;
import javafx.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XiongJiaMin
 * @apiNote 默认Handler
 * @since 2022-11-23 12:53
 **/
@SuppressWarnings("unused")
public abstract class DefaultEventHandler implements EventHandler<FXMLNEvent>, IStaticFactorySupport {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public DefaultEventHandler() {}

    /**
     * @since 2023/4/18 16:58
     * @description <p>
     *  默认的事件处理器,核心处理方法
     * </p>
     * @param event 处理的事件
     */
    @Override
    public abstract void handle(FXMLNEvent event);
}
