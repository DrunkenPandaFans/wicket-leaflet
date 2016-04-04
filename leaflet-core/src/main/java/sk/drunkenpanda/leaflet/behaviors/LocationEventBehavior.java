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

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.ErrorEvent;
import sk.drunkenpanda.leaflet.events.LocationEvent;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;
import sk.drunkenpanda.leaflet.json.model.JsonErrorEvent;
import sk.drunkenpanda.leaflet.json.model.JsonLocationEvent;

public abstract class LocationEventBehavior extends LeafletAjaxEventBehavior<LocationEvent, JsonLocationEvent> {

    public LocationEventBehavior() {
        super(MapEventType.LOCATION_FOUND, JsonLocationEvent.class, "WicketLeaflet.LocationEvent.getLocationEvent(event)");
        this.addJavascriptValue(MapEventType.LOCATION_ERROR.getJavascriptName(), "WicketLeaflet.LocationEvent.getLocationError(event)");
    }

    @Override
    protected final ResourceReference getJavascriptReference() {
        return new JavaScriptResourceReference(LocationEventBehavior.class, "LocationEvent.js");
    }

    @Override
    protected void respond(AjaxRequestTarget target) {
        StringValue locationEventJs = this.getVariableValue(this.getEventType().getJavascriptName());
        StringValue errorEventJs = this.getVariableValue(MapEventType.LOCATION_ERROR.getJavascriptName());
        if (!locationEventJs.isEmpty()) {
            super.respond(target);
        } else if (!errorEventJs.isEmpty()) {
            JsonRenderer jsonRenderer = JsonRendererFactory.getJsonRenderer();
            JsonErrorEvent jsonEvent = jsonRenderer.fromJson(errorEventJs.toString(), JsonErrorEvent.class);

            ErrorEvent errorEvent = jsonEvent.toModel();
            this.onError(errorEvent, target);
        }
    }

    protected abstract void onError(ErrorEvent event, AjaxRequestTarget target);

}
