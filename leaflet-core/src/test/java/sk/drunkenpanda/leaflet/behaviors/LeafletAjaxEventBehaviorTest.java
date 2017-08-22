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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.protocol.http.mock.MockHttpServletRequest;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

import sk.drunkenpanda.leaflet.AbstractLeafletTest;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.Event;
import sk.drunkenpanda.leaflet.events.PlainEvent;
import sk.drunkenpanda.leaflet.json.model.JsonPlainEvent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Jan Ferko
 */
public final class LeafletAjaxEventBehaviorTest extends AbstractLeafletTest {

    @Test
    public void testRenderEventScript() {
        IHeaderResponse headerResponse = mock(IHeaderResponse.class);

        final TestAjaxEventBehavior behavior = new TestAjaxEventBehavior(MapEventType.CLICK);
        final Map map = new Map("map");
        map.add(behavior);

        getTester().startComponentInPage(map);

        behavior.renderHead(map, headerResponse);

        verify(headerResponse).render(JavaScriptHeaderItem.forReference(behavior.getJavascriptReference()));
        verify(headerResponse).render(OnLoadHeaderItem.forScript(behavior.getInitializationScript()));
    }

    @Test
    public void testGetInitializationScript() {
        final TestAjaxEventBehavior behavior = new TestAjaxEventBehavior(MapEventType.CLICK);
        Map map = new Map("map");
        map.add(behavior);

        getTester().startComponentInPage(map);

        assertThat(behavior.getInitializationScript())
                .startsWith(map.getMapVarName())
                .contains(MapEventType.CLICK.getJavascriptName());
    }

    @Test
    public void testDontTriggerEventOnMissingJson() {
        final WicketTester tester = this.getTester();

        final TestAjaxEventBehavior behavior = new TestAjaxEventBehavior(MapEventType.CLICK);
        final Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);

        MockHttpServletRequest request = this.prepareRequest(tester, behavior,
                MapEventType.CLICK.getJavascriptName(), "");

        tester.processRequest(request);

        assertThat(behavior.wasTriggered).isFalse();
    }

    @Test
    public void testOnResponseTriggersEvent() {
        final WicketTester tester = this.getTester();

        final TestAjaxEventBehavior behavior = new TestAjaxEventBehavior(MapEventType.CLICK);
        final Map map = new Map("map");
        map.add(behavior);

        tester.startComponentInPage(map);
        JsonPlainEvent jsonEvent = new JsonPlainEvent();
        jsonEvent.setType("click");

        MockHttpServletRequest request = this.prepareRequest(tester, behavior, MapEventType.CLICK, jsonEvent);
        tester.processRequest(request);

        assertThat(behavior.wasTriggered).isTrue();
        assertThat(behavior.lastEvent).isEqualToComparingFieldByField(jsonEvent.toModel());
    }

    private class TestAjaxEventBehavior extends LeafletAjaxEventBehavior<PlainEvent> {

        boolean wasTriggered = false;

        Event lastEvent = null;

        public TestAjaxEventBehavior(MapEventType eventType) {
            super(eventType, PlainEvent.class, "WicketLeaflet.Event.getEvent(event)");
        }

        @Override
        protected ResourceReference getJavascriptReference() {
            return new JavaScriptResourceReference(TestAjaxEventBehavior.class, "Event.js");
        }

        @Override
        protected void onEvent(PlainEvent event, AjaxRequestTarget target) {
            this.wasTriggered = true;
            this.lastEvent = event;
        }

    }

}
