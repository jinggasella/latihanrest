package com.eksad.latihanrest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	@Bean
	public Docket eksadAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.latihanrest")) // package nya harus benar
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
					new Tag("Brand", "Brand Management API"),
					new Tag("Product", "Product Management API"),
					new Tag("Data Manipulation API", "Delete, Update, and Save Data Brand & Product"),
					new Tag("Get Data API", "Brand & Product")
						)
				;	
	}

	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo (
				"Product Data Management REST API",  // judul API
				"Rest API Collection for Product Data Management", //deskripsi
				"1.0.0", 
				"http://google.com", 
				new Contact("Jingga Sella", "https://twitter.com/jinggaaa21", "jinggasella22@gmail.com"), //contact developer
				"Jingga 2.0", 
				"http://www.google.com/license", 
				Collections.emptyList()); // vendor extension.dikasih nilai kosong aja. masih belum banyak yg pakai
		return apiInfo;
	}
	
}
