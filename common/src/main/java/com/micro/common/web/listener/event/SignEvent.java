package com.micro.common.web.listener.event;

import com.micro.common.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * @author XiongJiaMin
 * @apiNote 登录事件
 * @since 2022-09-07 16:36
 **/
public class SignEvent extends ApplicationEvent {

    private static final long serialVersionUID = -2095715546204463971L;

    private User user;

    public SignEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
