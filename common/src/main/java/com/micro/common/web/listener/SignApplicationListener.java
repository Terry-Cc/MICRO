package com.micro.common.web.listener;

import com.micro.common.web.listener.event.SignEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author XiongJiaMin
 * @apiNote 登录监听器
 * @since 2022-09-07 16:37
 **/
@Component
public class SignApplicationListener implements ApplicationListener<SignEvent> {

    private final static Logger logger = LogManager.getLogger(SignApplicationListener.class);

    @Override
    public void onApplicationEvent(SignEvent signEvent) {
        if (null == signEvent.getUser()) {
            return;
        }
        logger.info("用户 {} 已登录,访问次数 {} 次", signEvent.getUser().getUserName(), signEvent.getUser().getVisitTime());
    }
}
