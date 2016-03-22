/*
 * Copyright 2014 Jan Ferko.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sk.drunkenpanda.leaflet;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import sk.drunkenpanda.leaflet.behaviors.LeafletAjaxEventBehavior;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.Event;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;
import sk.drunkenpanda.leaflet.json.model.JsonEntity;

/**
 * Base class for Wicket-Leaflet tests.
 *
 * @author Jan Ferko
 */
public abstract class AbstractLeafletTest {

    /** Helper for testing Wicket components. */
    private WicketTester tester;

    @Before
    public void before() {
        WebApplication application = createApplication();

        this.tester = new WicketTester(application);
    }

    /**
     * Creates new web application instance with installed Leaflet library.
     * Application is configured by overriding different methods in this class,
     * e.g. you can configure Leaflet  by overriding {@link #getSettings() } method.
     *
     * @return new web application instace with installed Leaflet.
     */
    private WebApplication createApplication() {
        final LeafletSettings settings = getSettings();
        return new WebApplication() {

            @Override
            protected void init() {
                super.init();
                // create based on settings
                Leaflet.install(this, settings);

                // execute children init setup
                AbstractLeafletTest.this.init(this);
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return AbstractLeafletTest.this.getHomePage();
            }
        };
    }

    /**
     * Returns instance of wicket tester.
     * The instance of tester uses application created by {@link #createApplication()}.
     *
     * @return helper for testing Wicket components.
     */
    protected WicketTester getTester() {
        return this.tester;
    }

    /**
     * Returns created application.
     *
     * @return application used by wicket tester
     */
    protected WebApplication getApplication() {
        return this.tester.getApplication();
    }

    /**
     * Helper method that allows to configure additional application settings.
     *
     * @param application application used in tests.
     */
    protected void init(WebApplication application) {
    }

    /**
     * Returns class of home page.
     *
     * @return class of home page
     */
    protected Class<? extends Page> getHomePage() {
        return Page.class;
    }

    /**
     * Returns Leaflet library settings used in tests.
     * Default implementation returns {@link DefaultLeafletSettings}.
     *
     * @return Leaflet library settings used in tests
     */
    protected LeafletSettings getSettings() {
        return new DefaultLeafletSettings();
    }

    /**
     * Prepares mock HTTP request that triggers given behavior and contains parameter for event type with json payload as value.
     * JSON payload must be serializable with {@link JsonRenderer}.
     *
     * @param <E> event that is triggered by this request
     * @param <J> type of JSON payload
     * @param tester the wicket tester which triggers behavior
     * @param behavior the behavior that should be triggered
     * @param eventType the type of event that should be triggered
     * @param jsonPayload the JSON payload set as value for parameter with event type
     * @return mock HTTP request that triggers Leaflet event behavior
     */
    protected <E extends Event, J extends JsonEntity<E>> MockHttpServletRequest prepareRequest(WicketTester tester,
            LeafletAjaxEventBehavior<E, J> behavior, MapEventType eventType, J jsonPayload) {
        JsonRenderer jsonRenderer = JsonRendererFactory.getJsonRenderer();
        String json = jsonRenderer.toJson(jsonPayload);

        return this.prepareRequest(tester, behavior, eventType.getJavascriptName(), json);
    }

    /**
     * Prepares request that triggers AJAX behavior and contains parameter with given name and value.
     *
     * @param tester the wicket tester which triggers behavior
     * @param behavior the behavior that should be triggered
     * @param parameterName the name of parameter
     * @param parameterValue the value of parameter
     * @return mock HTTP request that triggers given behavior
     */
    protected MockHttpServletRequest prepareRequest(WicketTester tester, AbstractAjaxBehavior behavior,
            String parameterName, String parameterValue) {
        MockHttpServletRequest request = new MockHttpServletRequest(tester.getApplication(), tester.getHttpSession(), tester.getServletContext());

        final String url = behavior.getCallbackUrl().toString();
        request.addHeader("Wicket-Ajax", "true");
        request.addHeader("Wicket-Ajax-BaseURL", url);
        request.setURL(url);

        request.setParameter(parameterName, parameterValue);

        return request;
    }
}
