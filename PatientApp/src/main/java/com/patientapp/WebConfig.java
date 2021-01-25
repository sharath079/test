package com.patientapp;
import javax.servlet.http.HttpServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.patientapp.request.util.RequestHandlerServlet;
@Configuration
public class WebConfig
{
	@Bean
	public ServletRegistrationBean<HttpServlet> requestHandlerServlet()
	{
		ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
		servRegBean.setServlet(new RequestHandlerServlet());
		servRegBean.addUrlMappings("/*");
		servRegBean.setLoadOnStartup(1);
		System.out.println("Request handler servlet instantiated !!");
		return servRegBean;
	}
}
