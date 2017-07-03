package edu.scau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import edu.scau.interceptor.CharacterEncodingInterceptor;
import edu.scau.interceptor.LoginInterceptor;

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
}

@Configuration
class Configurer extends WebMvcAutoConfigurationAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new CharacterEncodingInterceptor());
//		registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/admin_login");
	}
	
}
