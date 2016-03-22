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

import java.util.Set;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.json.model.JsonLatLng;
import sk.drunkenpanda.leaflet.json.model.JsonMouseEvent;
import sk.drunkenpanda.leaflet.json.model.JsonPoint;

/**
 *
 * @author Jan Ferko
 */
public final class MouseEventBehaviorTest extends AbstractLeafletTest {

    private JsonMouseEvent jsonMouseEvent;

    @Before
    public void setUp() {
        this.jsonMouseEvent = new JsonMouseEvent();
        final JsonPoint point = new JsonPoint();
        point.setX(10.0);
        point.setY(20.0);

        final JsonLatLng latLng = new JsonLatLng();
        latLng.setLatitude(35.34);
        latLng.setLongitude(135.34);

        jsonMouseEvent.setLatLng(latLng);
        jsonMouseEvent.setContainerPoint(point);
        jsonMouseEvent.setLayerPoint(point);
        jsonMouseEvent.setType("click");
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

        ImmutableMap.Builder<MapEventType, MouseEventBehavior> behaviors = ImmutableMap.builder();
        for (MapEventType eventType : MouseEventBehavior.SUPPORTED_EVENTS) {
            final MouseEventBehavior behavior = new MouseEventBehavior(eventType) {
                @Override
                protected void onEvent(MouseEvent event, AjaxRequestTarget target) {
                    MouseEventBehaviorTest.this.checkReceivedEvent(jsonMouseEvent, event);
                }
            };
            map.add(behavior);

            behaviors.put(eventType, behavior);
        }

        tester.startComponentInPage(map);

        for (java.util.Map.Entry<MapEventType, MouseEventBehavior> entry : behaviors.build().entrySet()) {
            MockHttpServletRequest mockRequest = this.prepareRequest(tester, entry.getValue(), entry.getKey(), jsonMouseEvent);
            tester.setRequest(mockRequest);
            tester.applyRequest();
        }
    }

    private void checkReceivedEvent(JsonMouseEvent expected, MouseEvent actual) {
        assertNotNull(actual);
        if (expected.getContainerPoint() != null) {
            assertEquals(expected.getContainerPoint().toModel(), actual.getContainerPoint());
        } else {
            assertNull(actual.getContainerPoint());
        }
        if (expected.getLayerPoint() != null) {
            assertEquals(expected.getLayerPoint().toModel(), actual.getLayerPoint());
        } else {
            assertNull(actual.getLayerPoint());
        }
        if (expected.getLatLng() != null) {
            assertEquals(expected.getLatLng(), actual.getLatLng());
        } else {
            assertNull(actual.getLatLng());
        }

        assertEquals(expected.getType(), actual.getType());
    }

}
