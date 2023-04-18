package com.micro.jfxexe.factory;

/**
 * @author XiongJiaMin
 * @apiNote factory
 * @since 2022-11-25 09:53
 **/
public interface IStaticFactory<T> {

    /**
     * @since 2023/4/18 16:51
     * @description <p>
     *  工厂初始化方法
     * </p>
     */
    void initialize ();

    /**
     * @since 2023/4/18 16:52
     * @description <p>
     *  生产一个产品
     * </p>
     * @param produce 产品
     */
    void production (T produce);

    /**
     * @since 2023/4/18 16:52
     * @description <p>
     *  消费一个产品
     * </p>
     * @param produce 产品
     */
    void consumption (T produce);
}
