package com.micro.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

/**
 * <p>
 *     全局统一管理
 *     访问地址: https://localhost:10000/swagger-ui.html
 * </p>
 * @program: www
 * @description: Swagger2配置类
 * @author: XiongJiaMin
 * @create: 2021-11-30 16:22
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * <p>
     *      groupName 组名
     *      apiInfo 接口信息
     *      apis 选择扫描接口
     * </p>
     * @return Docket
     */
    @Bean
    public Docket getDocketF () {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("MrLi")
                .apiInfo(getApiInfoF())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.micro"))
                .build();
    }

    @Bean
    public Docket getDocketS () {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("MrWang")
                .apiInfo(getApiInfoS())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.micro"))
                .build();
    }

    /**
     * title 标题
     * description 介绍
     * version 版本
     * termsOfServiceUrl 服务条款路径 urn:tos表示默认
     * contact 作者姓名 联系地址 邮箱
     * license 许可证版本
     * licenseUrl 许可证路径
     * @return ApiInfo
     */
    public ApiInfo getApiInfoF () {
        Contact contact = new Contact("MrLi", "MrLi.com", "MrLi@163.com");
        return new ApiInfo("MrLiTest",
                "这人很懒,没有介绍",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }

    public ApiInfo getApiInfoS () {
        Contact contact = new Contact("MrWang", "MrWang.com", "MrWang@163.com");
        return new ApiInfo("MrWangTest",
                "这人很懒,没有介绍",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
