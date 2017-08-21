package sk.drunkenpanda.leaflet.example.pages;

import javax.inject.Inject;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sk.drunkenpanda.leaflet.example.WicketLeafletExampleBootstrap;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@ContextConfiguration(classes = WicketLeafletExampleBootstrap.class)
public class TestHomePage {

    @Inject
    private WebApplication webApplication;

    private WicketTester tester;

    @Before
    public void setUp() {
        this.tester = new WicketTester(this.webApplication);
    }

    @Test
    public void homepageRendersSuccessfully() {
        //start and render the test page
        tester.startPage(HomePage.class);

        //assert rendered page class
        tester.assertRenderedPage(HomePage.class);
    }
}
