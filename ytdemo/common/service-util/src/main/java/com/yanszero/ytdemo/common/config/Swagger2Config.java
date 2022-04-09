package com.yanszero.ytdemo.common.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置信息
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket baseApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("baseApi")
                .apiInfo(baseApiInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/base_info/.*")))
                .build();

    }

    @Bean
    public Docket businessApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("businessApi")
                .apiInfo(businessApiInfo())
                .select()
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/business/.*")))
                .build();

    }

    private ApiInfo baseApiInfo(){

        return new ApiInfoBuilder()
                .title("基本資訊API清單")
                .description("本清單描述了微服務接口定義")
                .version("1.0")
                .contact(new Contact("yanszero", "http://yanszero.com", "ating9997@gmail.com"))
                .build();
    }

    private ApiInfo businessApiInfo(){

        return new ApiInfoBuilder()
                .title("相關商業管理系统-API清單")
                .description("本清單描述了微服務接口定義")
                .version("1.0")
                .contact(new Contact("yanszero", "http://yanszero.com", "ating9997@gmail.com"))
                .build();
    }


}
