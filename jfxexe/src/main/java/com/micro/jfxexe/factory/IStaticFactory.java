package com.micro.jfxexe.factory;

/**
 * @author XiongJiaMin
 * @apiNote factory
 * @since 2022-11-25 09:53
 **/
public interface IStaticFactory<T> {

    void initialize ();

    void production (T produce);

    void consumption (T produce);
}
