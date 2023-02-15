package br.com.epicaweb.config;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements Filter , WebMvcConfigurer {
	

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("https://epicawebapp-ui.azurewebsites.net, http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }  
	
	 @Override
	    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) {
	      HttpServletResponse response = (HttpServletResponse) res;
	      HttpServletRequest request = (HttpServletRequest) req;
	      System.out.println("WebConfig; "+request.getRequestURI());


	      response.setHeader("Access-Control-Allow-Origin",
				  request.getHeader("Origin").equals("https://epicasys.jardimdacolina.com.br/") ||
				  request.getHeader("Origin").equals("http://localhost:4200")
				  ? request.getHeader("Origin") : "");
	      response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
	      response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
	      response.setHeader("Access-Control-Max-Age", "3600");
	      response.setHeader("Access-Control-Allow-Credentials", "true");
	      response.setHeader("Access-Control-Expose-Headers", "Authorization");
	      response.addHeader("Access-Control-Expose-Headers", "responseType");
	      response.addHeader("Access-Control-Expose-Headers", "observe");
	      System.out.println("Request Method: "+request.getMethod());
	      if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
	          try {
	              chain.doFilter(req, res);
	          } catch(Exception e) {
	              e.printStackTrace();
	          }
	      } else {
	          System.out.println("Pre-flight");
	          response.setHeader("Access-Control-Allow-Origin", "https://epicawebapp-ui.azurewebsites.net; http://localhost:4200");
	          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT,OPTIONS");
	          response.setHeader("Access-Control-Max-Age", "3600");
	          response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
	          "USERID"+"ROLE"+"access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
	          response.setStatus(HttpServletResponse.SC_OK);
	      }
	    }
}
