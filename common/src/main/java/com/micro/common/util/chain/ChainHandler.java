package com.micro.common.util.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author XiongJiaMin
 * @apiNote 责任链父级 (抽象)
 * @since 2023-01-03 15:02
 **/
public abstract class ChainHandler {

    protected static final Logger logger = LoggerFactory.getLogger(ChainHandler.class);

    private ChainHandler nextHandler;

    /**
     * @since 2023/4/17 22:51
     * @description <p>
     *  优先级
     * </p>
     */
    private int order;

    /**
     * @since 2023/4/17 22:51
     * @description <p>
     *  责任链名称
     * </p>
     */
    private String chainName;

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    /**
     * 设置执行序列, 数值越小优先级越高
     * @param order 序列
     */
    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    /**
     * 设置下一个处理器
     * @param nextHandler 下一个处理器
     */
    public void setNextHandler(ChainHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public ChainHandler getNextHandler() {
        return nextHandler;
    }

    /**
     * 核心执行器,
     * @param chainParam 处理参数
     */
    public final void process (ChainParam chainParam) {
        logger.info(String.join(" ", "ChainHandler start process -----", this.getClass().getName(), "order is", String.valueOf(getOrder()), "chain name is", getChainName()));
        handler(chainParam);
        if (getNextHandler() != null) {
            getNextHandler().process(chainParam);
        }
    }

    /**
     * 责任链处理的内容
     * @param chainParam 责任链传递的参数
     */
    public abstract void handler (ChainParam chainParam);
}
