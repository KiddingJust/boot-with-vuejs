package org.kidding.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
				.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
				.allowedOrigins("http://localhost:8200", "http://127.0.0.1:8200", "http://35.237.254.22:5000","http://localhost" , "http://10.142.0.3:5000", "http://127.0.0.1:5000", "http://localhost:8080", "http://127.0.0.1:8080", "http://localhost:5000", "http://localhost:80", "http://127.0.0.1:80");
	}
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/", "index.html");
//    }

	
}
