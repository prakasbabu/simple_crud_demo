package com.prakash.springboot.cruddemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CurdDemoInterceptorConfig  implements WebMvcConfigurer {
	
		@Autowired
	    private CrudDemoInterceptor apiInterceptor;
	
		@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(apiInterceptor).addPathPatterns("/api/**");
	        registry.addInterceptor(apiInterceptor).excludePathPatterns("/user/action/login/**");
	        registry.addInterceptor(apiInterceptor).excludePathPatterns("/redish/**");
	                
	    }

}
