/*
 * Copyright 2016 Jan Ferko.
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

package sk.drunkenpanda.leaflet.behaviors;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.ErrorEvent;
import sk.drunkenpanda.leaflet.events.LocationEvent;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.LatLngBounds;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Jan Ferko
 */
public final class LocationEventBehaviorTest extends AbstractLeafletTest {

    private LocationEvent locationEvent;

    private ErrorEvent errorEvent;

    @Before
    public void setUp() {
        this.locationEvent = LocationEvent.builder()
                .accuracy(98.0)
                .altitude(345.0)
                .altitudeAccuracy(76.0)
                .heading(15.0)
                .speed(20.0)
                .timestamp(new Date().getTime())
                .type(MapEventType.LOCATION_FOUND)
                .latLng(LatLng.of(35.67, -103.45))
                .latLngBounds(
                        LatLngBounds.of(
                                LatLng.of(67.0, 78.9),
                                LatLng.of(43.12, 23.43))
                )
                .build();

        this.errorEvent = ErrorEvent.of(
                MapEventType.LOCATION_ERROR,
                "The Answer to the Ultimate Question of Life, the Universe, and Everything is 42.",
                42
        );
    }

    @Test
    public void testBindToLocationErrorEvent() {
        WicketTester tester = this.getTester();

        final Map map = new Map("map");
        final TestLocationEventBehavior behavior = new TestLocationEventBehavior();
        map.add(behavior);

        tester.startComponentInPage(map);

        assertThat(behavior.getInitializationScript())
                .isNotNull()
                .startsWith(map.getMapVarName())
                .contains("on('" + MapEventType.LOCATION_ERROR.getJavascriptName() + "'");
    }

    @Test
    public void testRespondToLocationEvent() {
        WicketTester tester = this.getTester();

        final Map map = new Map("map");
        final TestLocationEventBehavior behavior = new TestLocationEventBehavior();
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, MapEventType.LOCATION_FOUND, this.locationEvent);
        tester.processRequest(request);

        assertThat(behavior.actualLocationEvent)
                .isNotNull()
                .isEqualToComparingFieldByField(this.locationEvent);
        assertThat(behavior.actualError).isNull();
    }

    @Test
    public void testRespondToLocationError() {
        WicketTester tester = this.getTester();

        final Map map = new Map("map");
        final TestLocationEventBehavior behavior = new TestLocationEventBehavior();
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, MapEventType.LOCATION_ERROR, this.errorEvent);
        tester.processRequest(request);

        assertThat(behavior.actualError)
                .isNotNull()
                .isEqualToComparingFieldByField(this.errorEvent);
        assertThat(behavior.actualLocationEvent).isNull();
    }

    @Test
    public void testDontRespondToEmptyErrorEvent() {
        WicketTester tester = this.getTester();

        final Map map = new Map("map");
        final TestLocationEventBehavior behavior = new TestLocationEventBehavior();
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, MapEventType.LOCATION_ERROR.getJavascriptName(), "");
        tester.processRequest(request);

        assertThat(behavior.actualError).isNull();
        assertThat(behavior.actualLocationEvent).isNull();
    }

    private final class TestLocationEventBehavior extends LocationEventBehavior {

        private ErrorEvent actualError = null;

        private LocationEvent actualLocationEvent = null;

        @Override
        protected void onError(ErrorEvent event, AjaxRequestTarget target) {
            this.actualError = event;
        }

        @Override
        protected void onEvent(LocationEvent event, AjaxRequestTarget target) {
            this.actualLocationEvent = event;
        }

    }

}
