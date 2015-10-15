package com.ciena.sca.application;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@ComponentScan("com.ciena.lion, com.ciena.sca")

@SpringBootApplication
@EnableWebMvc
public class Application extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Value("${ssl.server.port}") int sslPort;
	@Value("${ssl.keystore.file}") String keystoreFile;
	@Value("${ssl.keystore.alias}") String keystoreAlias;
    @Value("${ssl.keystore.password}") String keystorePassword;
    	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector());
		return tomcat;
	}
    
	private Connector createSslConnector() {

        try {
            final String absoluteKeystoreFile = ResourceUtils.getFile(keystoreFile).getAbsolutePath();

    		logger.info("creating SSL Connector using keystore file {}", keystoreFile);

            Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();

    		connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(sslPort);
            connector.setAttribute("clientAuth", "false");
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(absoluteKeystoreFile);
			protocol.setKeystorePass(keystorePassword);
			protocol.setKeystoreType("PKCS12");
			protocol.setKeyAlias("tomcat");
			protocol.setTruststoreFile(absoluteKeystoreFile);
			protocol.setTruststorePass(keystorePassword);
			protocol.setTruststoreType("PKCS12");

	        return connector;
		}
        catch (Exception e) {
    		logger.error("error creating SSL Connector {}", e.getMessage());
    		e.printStackTrace();
    		
    		return null;
        }        
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
}
