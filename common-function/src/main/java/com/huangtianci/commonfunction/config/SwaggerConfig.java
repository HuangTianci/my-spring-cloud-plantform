package com.huangtianci.commonfunction.config;

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
 * @author Huang Tianci
 * @date 2017/11/23
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
					.apiInfo(apiInfo())
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.huangtianci"))
					.paths(PathSelectors.any())
					.build();
	}
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("通用功能")
				.description("通用功能接口说明文档")
				.contact(new Contact("Huang Tianci", null, "tianci.huang@qq.com"))
				.license("huangtianci.com").licenseUrl("#")
				.version("1.0-SNAPSHOT")
				.build();
	}

}
