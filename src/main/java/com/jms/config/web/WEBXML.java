package com.jms.config.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.jms.config.SpringActiveMQConfig;

public class WEBXML implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		ContextListener(servletContext);
		dispatcherServlet(servletContext);
	}

	private void dispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringMVCConfig.class);

		ServletRegistration.Dynamic servlet = servletContext.addServlet(
				"springMvc", new DispatcherServlet(webContext));
		servlet.addMapping("/");
		servlet.setAsyncSupported(true);
		servlet.setLoadOnStartup(1);

	}

	private void ContextListener(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringActiveMQConfig.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));
	}

}
