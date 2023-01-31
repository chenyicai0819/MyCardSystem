package com.chen.mycardsystembackstage.config;

import com.google.api.client.util.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Project : MyCardSystem - SwaggerConfig
 * Powered by GeorgeChen On 2023-01-31 11:29:37
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Configuration
// 开启swagger2
@EnableSwagger2
public class SwaggerConfig {

    private static String version = "2023.01.31";

    @Bean
    public Docket docket() {
        ApiInfo apiInfo =
                new ApiInfoBuilder()
                        .title("MyCardSystem系统API")
                        .description("MyCardSystem系统采用SpringBoot开发，API文档集成Swagger")
                        .version(version)
                        .contact(new Contact("GeorgeChen", "https://github.com/chenyicai0819/MyCardSystem", "mycard0819@qq.com"))
                        .license("MIT License")
                        .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // 配置swagger显示的controller，如果不配置则默认扫描所有后端接口
                .apis(RequestHandlerSelectors.basePackage("com.chen.mycardsystembackstage.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
