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
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.DragEndEvent;
import sk.drunkenpanda.leaflet.json.model.JsonDragEndEvent;

/**
 * Behavior that allows drag end event processing on server-side using AJAX calls.
 * 
 * @author Jan Ferko
 */
public abstract class DragEndEventBehavior extends LeafletAjaxEventBehavior<DragEndEvent, JsonDragEndEvent> {

    public DragEndEventBehavior() {
        super(MapEventType.DRAG_END, JsonDragEndEvent.class, "WicketLeaflet.DragEndEvent.getDragEndEvent(event)");
    }

    @Override
    protected final ResourceReference getJavascriptReference() {
        return new JavaScriptResourceReference(DragEndEventBehavior.class, "DragEndEvent.js");
    }

}
