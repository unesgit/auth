package ma.cam.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Value("${idms.api.swagger-package:com.gemalto.ics.dc.idms.product.security.api.exposed}")
	private String swaggerPackage;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(swaggerPackage)).paths(PathSelectors.any()).build()
				.apiInfo(metaData());
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Request Register api").description("swagger ")
				.termsOfServiceUrl("http://localhost:8084/request/").version("1.0").build();
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("IDMS Core API for Request Register").description("\"µservices for IDMS\"")
				.version("1.0.0").license("IDMS License").licenseUrl("#")
				.contact(new Contact("Admin Platform", "https://confluence.gemalto.com", "admin@email.com")).build();
	}

}