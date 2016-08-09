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

import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.lang.Args;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.json.model.JsonMouseEvent;

/**
 * Behavior that allows mouse events processing on server-side using AJAX calls.
 *
 * @author Jan Ferko
 */
public abstract class MouseEventBehavior extends LeafletAjaxEventBehavior<MouseEvent, JsonMouseEvent> {

    /**
     * All event types supported by this behavior.
     */
    static final MapEventType[] SUPPORTED_EVENTS = new MapEventType[] {
        MapEventType.CLICK, MapEventType.DOUBLE_CLICK, MapEventType.MOUSEDOWN,
        MapEventType.MOUSEMOVE, MapEventType.MOUSEOUT, MapEventType.MOUSEOVER,
        MapEventType.MOUSEUP, MapEventType.CONTEXT_MENU, MapEventType.PRECLICK,
    };

    /**
     * Constructs new behavior instance for given event type.
     * If event type is not supported, it throws {@link IllegalArgumentException}.
     *
     * @param eventType type of event, that this behavior should be bind to.
     * @throws IllegalArgumentException if event type is not supported or is {@code null}.
     */
    public MouseEventBehavior(MapEventType eventType) {
        super(isSupported(eventType), JsonMouseEvent.class, "WicketLeaflet.MouseEvent.getMouseEvent(event)");
    }

    @Override
    protected final ResourceReference getJavascriptReference() {
        return new JavaScriptResourceReference(MouseEventBehavior.class, "MouseEvent.js");
    }

    /**
     * Checks if event type is supported by this behavior.
     * If event type is not supported or is {@code null}, it throws {@link IllegalArgumentException}.
     *
     * @param eventType event type to check for support
     * @return event type if it is supported
     * @throws IllegalArgumentException if event type is not supported or is {@code null}.
     */
    private static MapEventType isSupported(MapEventType eventType) {
        Args.notNull(eventType, "eventType");

        for (MapEventType e : SUPPORTED_EVENTS) {
            if (e.equals(eventType)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Event type is not supported.");
    }

}
