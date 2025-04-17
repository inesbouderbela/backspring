package tn.ucar.enicar.info.projetspring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            String resourceHandler = "/SpringMVC/api/uploads/**";
            String resourceLocation = "file:uploads/";

            logger.info("Configuration du handler de ressources:");
            logger.info("Handler: {}", resourceHandler);
            logger.info("Location: {}", resourceLocation);

            registry.addResourceHandler(resourceHandler)
                    .addResourceLocations(resourceLocation)
                    .setCachePeriod(3600);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("*");
    }

}