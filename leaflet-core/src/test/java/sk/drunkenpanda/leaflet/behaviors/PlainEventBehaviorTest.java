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

import java.util.EnumMap;
import java.util.Set;
import com.google.common.collect.Sets;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.util.tester.WicketTester;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.Test;
import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.Event;
import sk.drunkenpanda.leaflet.json.model.JsonEvent;

/**
 *
 * @author Jan Ferko
 */
public final class PlainEventBehaviorTest extends AbstractLeafletTest {

    @Test
    public void testDoesntAllowUnsupportedTypes() {
        Set<MapEventType> supportedTypes = Sets.newHashSet(PlainEventBehavior.SUPPORTED_TYPES);
        Set<MapEventType> allTypes = Sets.newHashSet(MapEventType.values());
        Set<MapEventType> unsupportedTypes = Sets.difference(allTypes, supportedTypes);

        for (MapEventType eventType : unsupportedTypes) {
            try {
                final TestPlainEventBehavior behavior = new TestPlainEventBehavior(eventType);
                fail("PlainEventBehavior should not support [" + eventType.getJavascriptName() + "] event.");
            } catch (IllegalArgumentException ex) {
                // ok
            }
        }
    }

    @Test
    public void testTriggersSupportedEventBehavior() {
        WicketTester tester = this.getTester();

        Map map = new Map("map");

        java.util.Map<MapEventType, TestPlainEventBehavior> behaviors = new EnumMap<MapEventType, TestPlainEventBehavior>(MapEventType.class);
        for (MapEventType eventType : PlainEventBehavior.SUPPORTED_TYPES) {
            final TestPlainEventBehavior plainBehavior = new TestPlainEventBehavior(eventType);
            behaviors.put(eventType, plainBehavior);
            map.add(plainBehavior);
        }

        tester.startComponentInPage(map);

        for (java.util.Map.Entry<MapEventType, TestPlainEventBehavior> e : behaviors.entrySet()) {
            final JsonEvent jsonEvent = this.preparePlainEvent(e.getKey());
            MockHttpServletRequest request = this.prepareRequest(tester, e.getValue(), e.getKey(), jsonEvent);
            tester.processRequest(request);
            assertThat(e.getValue().wasNotified).isTrue();
        }
    }

    private JsonEvent preparePlainEvent(MapEventType eventType) {
        JsonEvent jsonEvent = new JsonEvent();
        jsonEvent.setType(eventType.getJavascriptName());
        return jsonEvent;
    }

    private class TestPlainEventBehavior extends PlainEventBehavior {

        boolean wasNotified = false;

        public TestPlainEventBehavior(MapEventType eventType) {
            super(eventType);
        }

        @Override
        protected void onEvent(Event event, AjaxRequestTarget target) {
            this.wasNotified = true;
        }

    }

}
