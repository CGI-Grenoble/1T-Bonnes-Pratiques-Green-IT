package polytech.projets10.g1._1tbonnespratiquesgreenit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //TODO Fix CORS origin (frontend url does not work)
        CorsRegistration registration = registry.addMapping("/**");
        registration.allowedHeaders("*");
        registration.allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
