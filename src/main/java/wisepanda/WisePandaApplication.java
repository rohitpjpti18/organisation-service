package wisepanda;

import java.util.Arrays;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import wisepanda.service.AppConfigService;

@SpringBootApplication
public class WisePandaApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(WisePandaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		AppConfigService.loadConfig();
		
	}

	@Bean
  	ServletRegistrationBean jsfServletRegistration (ServletContext servletContext) {
		//spring boot only works if this is set
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());

		//registration
		ServletRegistrationBean srb = new ServletRegistrationBean();
		srb.setServlet(new FacesServlet());
		srb.setUrlMappings(Arrays.asList("*.xhtml"));
		srb.setLoadOnStartup(1);
		return srb;
  	}
}
