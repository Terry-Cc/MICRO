package com.micro.jfxexe.config;

import com.micro.common.util.chain.HandlerChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author XiongJiaMin
 * @apiNote 责任链配置
 * @since 2023-01-04 14:43
 **/
@Configuration
public class ChainConfig {

    @Bean(name = "searchChainHandler")
    public HandlerChain creatSearchChain () {
        return new HandlerChain("com/micro/jfxexe/handler/chain/", "searchNoteChain");
    }
}
