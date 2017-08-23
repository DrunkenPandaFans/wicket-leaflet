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

import java.awt.*;
import java.util.Set;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.models.Point;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 *
 * @author Jan Ferko
 */
public final class MouseEventBehaviorTest extends AbstractLeafletTest {

    private MouseEvent mouseEvent;

    @Before
    public void setUp() {
        this.mouseEvent = MouseEvent.builder()
            .layerPoint(Point.of(10.0, 20.0))
            .latLng(LatLng.of(35.34, 135.34))
            .containerPoint(Point.of(12.0, 22.0))
            .type(MapEventType.CLICK)
            .build();
    }

    private MouseEvent prepareMouseEvent(MapEventType type) {
        return this.mouseEvent.withType(type);
    }

    @Test
    public void testDoesNotAllowUnsupportedEvents() {
        final Set<MapEventType> eventTypes = Sets.newHashSet(MapEventType.values());
        final Set<MapEventType> supportedEventTypes = Sets.newHashSet(MouseEventBehavior.SUPPORTED_EVENTS);
        final Set<MapEventType> unsupportedEventTypes = Sets.difference(eventTypes, supportedEventTypes);

        for (MapEventType eventType : unsupportedEventTypes) {
            try {
                final MouseEventBehavior behavior = new MouseEventBehavior(eventType) {
                    @Override
                    protected void onEvent(MouseEvent event, AjaxRequestTarget target) {
                    }
                };
                fail("MouseEventBehavior should throw IllegalArgumentException if unsupported event type is provided.");
            } catch (IllegalArgumentException ex) {
                // ok
            }
        }
    }

    @Test
    public void testTriggersSupportedEvents() {
        final WicketTester tester = this.getTester();
        Map map = new Map("map");

        ImmutableMap.Builder<MapEventType, TestMouseEventBehavior> behaviors = ImmutableMap.builder();
        for (MapEventType eventType : MouseEventBehavior.SUPPORTED_EVENTS) {
            final MouseEvent expectedEvent = this.prepareMouseEvent(eventType);
            final TestMouseEventBehavior behavior = new TestMouseEventBehavior(eventType, expectedEvent);
            map.add(behavior);

            behaviors.put(eventType, behavior);
        }

        tester.startComponentInPage(map);

        for (java.util.Map.Entry<MapEventType, TestMouseEventBehavior> entry : behaviors.build().entrySet()) {
            MouseEvent expected = this.prepareMouseEvent(entry.getKey());

            MockHttpServletRequest request = this.prepareRequest(tester, entry.getValue(), entry.getKey(), expected);
            tester.processRequest(request);

            assertThat(entry.getValue().actualEvent)
                    .as("Event [%s] was not triggered.", entry.getKey())
                    .isEqualToComparingFieldByField(expected);
        }
    }

    private class TestMouseEventBehavior extends MouseEventBehavior {
        private MouseEvent actualEvent;

        public TestMouseEventBehavior(MapEventType eventType, MouseEvent actualEvent) {
            super(eventType);
            this.actualEvent = actualEvent;
        }

        @Override
        protected void onEvent(MouseEvent actual, AjaxRequestTarget target) {
            this.actualEvent = actual;
        }
    }

}
