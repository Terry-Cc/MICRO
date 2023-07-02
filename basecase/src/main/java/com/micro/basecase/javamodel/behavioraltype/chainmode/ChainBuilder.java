package com.micro.basecase.javamodel.behavioraltype.chainmode;

/**
 * @author Terry
 * @version 1.0
 * @description <p>
 *  责任链构建器
 * </p>
 * @since 2023/7/2 11:25
 */
public class ChainBuilder {

    private Handler head;

    private Handler tail;

    public ChainBuilder add(Handler handler) {
        if (null == this.head) {
            this.head = this.tail = handler;
            return this;
        }
        this.tail.next(handler);
        this.tail = handler;
        return this;
     }

    private void execute(Handler handler) {
        handler.handler();
        if (null != handler.next()) {
            execute(handler.next());
        }
    }

    public void start() {
        execute(this.head);
    }
}
