package com.sparkle.start_project.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//swagger文档配置
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 文档")
                        .version("1.0")
                        .description("接口说明文档")
                        // 添加作者信息配置
                        .contact(new Contact()
                                .name("Sparkle")      // 开发者或团队名称
                                .email("author@example.com") // 联系方式邮箱
                                .url("https://your-website.com") // 作者/团队官网
                        )
                );
    }
}