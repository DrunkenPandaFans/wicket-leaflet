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
import sk.drunkenpanda.leaflet.events.MouseEvent;
import sk.drunkenpanda.leaflet.json.model.JsonMouseEvent;

public abstract class MouseEventBehavior extends LeafletAjaxEventBehavior<MouseEvent, JsonMouseEvent> {

    static final MapEventType[] SUPPORTED_EVENTS = new MapEventType[] {
        MapEventType.CLICK, MapEventType.DOUBLE_CLICK, MapEventType.MOUSEDOWN,
        MapEventType.MOUSEMOVE, MapEventType.MOUSEOUT, MapEventType.MOUSEOVER,
        MapEventType.MOUSEUP, MapEventType.CONTEXT_MOVE, MapEventType.PRECLICK,
    };

    public MouseEventBehavior(MapEventType eventType) {
        super(isSupported(eventType), JsonMouseEvent.class);
        this.addJavascriptValue(eventType.getJavascriptName(), "WicketLeaflet.MouseEvent.getMouseEvent(event)");
    }

    @Override
    protected final ResourceReference getJavascriptReference() {
        return new JavaScriptResourceReference(MouseEventBehavior.class, "MouseEvent.js");
    }

    private static MapEventType isSupported(MapEventType eventType) {
        for (MapEventType e : SUPPORTED_EVENTS) {
            if (e.equals(eventType)) {
                return e;
            }
        }

        throw new IllegalArgumentException("Event type is not supported.");
    }

}
