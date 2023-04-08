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

    @Override
    public abstract void handle(FXMLNEvent event);
}
