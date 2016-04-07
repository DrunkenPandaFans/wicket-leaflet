/*
 * Copyright 2016 ferko.
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
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.Event;
import sk.drunkenpanda.leaflet.json.model.JsonEvent;

/**
 * Behavior that processes simple events that don't have additional data.
 *
 * @author Jan Ferko
 */
public abstract class PlainEventBehavior extends LeafletAjaxEventBehavior<Event, JsonEvent> {

    /**
     * All events supported by this behavior.
     */
    /* internal */ static final MapEventType[] SUPPORTED_TYPES = new MapEventType[] {
        MapEventType.FOCUS, MapEventType.BLUR, MapEventType.LOAD, MapEventType.UNLOAD,
        MapEventType.VIEW_RESET, MapEventType.MOVE_START, MapEventType.MOVE, MapEventType.MOVE_END,
        MapEventType.DRAG_START, MapEventType.DRAG, MapEventType.ZOOM_START, MapEventType.ZOOM_END,
        MapEventType.ZOOM_LEVELS_CHANGE,  MapEventType.AUTOPAN_START
    };

    /**
     * Constructs new event for given event type.
     * If event type is not supported, it throws {@link IllegalArgumentException}.
     *
     * @param eventType the event type to which is this behavior binded to.
     * @throws IllegalArgumentException if event type is not supported
     */
    public PlainEventBehavior(MapEventType eventType) {
        super(isSupported(eventType), JsonEvent.class, "WicketLeaflet.Event.getEvent(event)");
    }

    @Override
    protected final ResourceReference getJavascriptReference() {
        return new JavaScriptResourceReference(PlainEventBehavior.class, "Event.js");
    }

    /**
     * Checks if event type is supported by this behavior.
     *
     * @param eventType the event type to check
     * @return given event type if it is supported
     * @throws IllegalArgumentException if event type is not supported
     */
    private static MapEventType isSupported(MapEventType eventType) {
        for (MapEventType e : SUPPORTED_TYPES) {
            if (e.equals(eventType)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Event type is not supported");
    }

}
