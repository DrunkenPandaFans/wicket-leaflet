package sk.drunkenpanda.leaflet.example;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import sk.drunkenpanda.leaflet.example.config.DefaultMapboxConfiguration;
import sk.drunkenpanda.leaflet.example.config.MapboxConfiguration;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class WicketLeafletExampleBootstrap extends SpringBootServletInitializer implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(WicketLeafletExampleBootstrap.class)
                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return super.configure(builder);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public FilterRegistrationBean wicketFilter() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WicketFilter());
        filterRegistrationBean.addInitParameter(WicketFilter.APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        filterRegistrationBean.addInitParameter("applicationBean", "wicketApplication");
        filterRegistrationBean.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addServletNames("wicket-filter");

        return filterRegistrationBean;
    }

    @Bean
    public WicketApplication wicketApplication() {
        return new WicketApplication(this.applicationContext);
    }

    @Bean
    public MapboxConfiguration mapboxConfiguration(
            @Value("${mapbox.access.token}") String accessToken,
            @Value("${mapbox.map.id}") String mapId) {
        return new DefaultMapboxConfiguration(accessToken, mapId);
    }
}
