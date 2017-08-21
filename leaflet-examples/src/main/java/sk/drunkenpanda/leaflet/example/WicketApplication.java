package sk.drunkenpanda.leaflet.example;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;

import sk.drunkenpanda.leaflet.DefaultLeafletSettings;
import sk.drunkenpanda.leaflet.Leaflet;
import sk.drunkenpanda.leaflet.example.pages.HomePage;

/**
 * Application object for examples web application.
 *
 */
public class WicketApplication extends WebApplication {

    private final ApplicationContext applicationContext;

    public WicketApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getComponentInstantiationListeners().add(
                new SpringComponentInjector(this, applicationContext));

        final DefaultLeafletSettings settings = new DefaultLeafletSettings.Builder()
                .setAutoAppendResources(true)
                .build();
        Leaflet.install(this, settings);
    }
}
