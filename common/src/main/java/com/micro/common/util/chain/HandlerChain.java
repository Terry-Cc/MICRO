package com.micro.common.util.chain;

import com.micro.common.anno.ChainOrder;
import com.micro.common.exception.BusinessException;
import com.micro.common.exception.InspectedParameterException;
import com.micro.common.util.other.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 实现:
 * 创建处理器继承 ChainHandler,
 * 实现 handler(处理) 方法,
 * 可用注解 @ChainOrder 对 ChainHandler 定义优先级 和 责任链分组名称,
 * 数值越小优先级越高, 然后根据 name 分组, name 默认名称 default,
 * 最后注入 HandlerChain, 并指定扫描路径,
 * 他会自动扫描指定路径下的 ChainHandler 并进行优先级排序, 获取指定的组名下的 chain,
 * 然后调用 HandlerChain.process() 即可,
 * 多个责任链请分包存放然后指定不同的路径即可
 * @author XiongJiaMin
 * @apiNote 责任链处理器
 * @since 2023-01-03 15:11
 **/
@SuppressWarnings("unused")
public class HandlerChain {

    private static final Logger logger = LoggerFactory.getLogger(HandlerChain.class);

    private static final char RPL_INTERVAL = '.';

    private static final char PKG_INTERVAL = '/';

    private static final String CLASS_SUFFIX = ".class";

    private static final String DEFAULT_CHAIN_NAME = "default";

    private List<ChainHandler> chainHandlerList = new ArrayList<>(10);

    public HandlerChain (String packageName, String chainName) {
        try {
            chainName = CommonUtils.isEmpty(chainName) ? DEFAULT_CHAIN_NAME : chainName;
            scanChainHandler(packageName, chainName);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    private void scanChainHandler (String packageName, String chainName) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (CommonUtils.isEmpty(packageName)) {
            throw new InspectedParameterException("chain handler packageName can not be empty.");
        }
        Enumeration<URL> resources = this.getClass().getClassLoader().getResources(packageName);
        Map<Integer, List<ChainHandler>> chainMap = new HashMap<>();
        while (resources.hasMoreElements()) {
            URL url = resources.nextElement();
            File pkgFile = new File(url.getFile());
            File[] files = pkgFile.listFiles();
            if (CommonUtils.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                if (file.getName().endsWith(CLASS_SUFFIX)) {
                    String name = file.getName().replace(CLASS_SUFFIX, "");
                    Class<?> clz = Class.forName((packageName + name).replace(PKG_INTERVAL, RPL_INTERVAL));
                    if (!ChainHandler.class.isAssignableFrom(clz)
                            || ChainHandler.class.equals(clz)) {
                        continue;
                    }
                    ChainOrder[] annotations = clz.getAnnotationsByType(ChainOrder.class);
                    if (CommonUtils.isEmpty(annotations)) {
                        continue;
                    }
                    ChainOrder annotation = annotations[0];
                    if (!chainName.equals(annotation.name())) {
                        continue;
                    }
                    ChainHandler chainHandler = (ChainHandler) clz.newInstance();
                    chainHandler.setOrder(annotation.order());
                    chainHandler.setChainName(chainName);
                    List<ChainHandler> chainHandlers = chainMap.computeIfAbsent(annotation.order(), v -> new ArrayList<>());
                    chainHandlers.add(chainHandler);
                    logger.info(String.join(" ", "HandlerChain scan chain handler -----", clz.getName(), "order is", String.valueOf(annotation.order()), "chain name is", annotation.name()));
                }
            }
        }
        if (!CommonUtils.isEmpty(chainMap)) {
            chainHandlerList = chainMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            ChainHandler previousHandler = null;
            for (ChainHandler chainHandler : chainHandlerList) {
                if (previousHandler != null) {
                    previousHandler.setNextHandler(chainHandler);
                }
                previousHandler = chainHandler;
            }
            logger.info(String.join(" ", "HandlerChain scan chain handler ----- register success, size:", String.valueOf(chainHandlerList.size())));
        }
    }

    /**
     * 每次都从第一个执行器开始往下执行
     * @param chainParam 参数
     */
    public void process (ChainParam chainParam) {
        if (!CommonUtils.isEmpty(chainHandlerList)) {
            chainHandlerList.get(0).process(chainParam);
        }
    }
}
