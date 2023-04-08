package com.micro.jfxexe.factory;

import com.micro.common.util.other.CommonUtils;
import com.micro.jfxexe.controller.view.BaseViewController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XiongJiaMin
 * @apiNote fxml view
 * @since 2022-11-25 10:19
 **/
@SuppressWarnings("unused")
public class DefaultFXMLViewFactory implements IStaticFactory<BaseViewController> {

    protected static Logger logger = LoggerFactory.getLogger(DefaultFXMLViewFactory.class);

    private Map<Class<? extends BaseViewController>, BaseViewController> viewControllerMap;

    private DefaultFXMLViewFactory() {
        this.initialize();
    }

    public static DefaultFXMLViewFactory getInstance () {
        return DefaultFXMLViewFactory.DefaultFXMLViewFactorySingle.INSTANCE;
    }

    private static class DefaultFXMLViewFactorySingle {
        public static final DefaultFXMLViewFactory INSTANCE = new DefaultFXMLViewFactory();
    }

    public Map<Class<? extends BaseViewController>, BaseViewController> getViewControllerMap() {
        return viewControllerMap;
    }

    public void setViewControllerMap(Map<Class<? extends BaseViewController>, BaseViewController> viewControllerMap) {
        this.viewControllerMap = viewControllerMap;
    }

    @Override
    public void initialize() {
        if (this.isEmpty()) {
            this.setViewControllerMap(new ConcurrentHashMap<>(16));
        }
    }

    @Override
    public void production(BaseViewController produce) {
        if (this.isExits(produce.getClass())) {
            this.getViewControllerMap().put(produce.getClass(), produce);
            logger.info(String.join(" ", "view factory ----- add:", produce.getClass().getSimpleName()));
        }
    }

    @Override
    public void consumption(BaseViewController produce) {
        if (this.isExits(produce.getClass())) {
            this.getViewControllerMap().remove(produce.getClass());
            logger.info(String.join(" ", "view factory ----- delete:", produce.getClass().getSimpleName()));
        }
    }

    public void update(BaseViewController produce) {
        this.getViewControllerMap().put(produce.getClass(), produce);
        logger.info(String.join(" ", "view factory ----- update:", produce.getClass().getSimpleName()));
    }

    /**
     * 获取视图实例
     * @param clz 类
     * @param <T> 视图泛型
     * @return window
     */
    public <T>T getView(Class<T> clz) {
        BaseViewController controller = this.getViewControllerMap().get(clz);
        if (clz.isInstance(controller)) {
            return clz.cast(controller);
        }
        return null;
    }

    public boolean isEmpty () {
        return CommonUtils.isEmpty(this.getViewControllerMap());
    }

    public boolean isExits (Class<? extends BaseViewController> clz) {
        return this.getView(clz) == null;
    }
}
