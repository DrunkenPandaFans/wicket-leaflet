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

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.util.string.StringValue;
import sk.drunkenpanda.leaflet.components.map.Map;
import sk.drunkenpanda.leaflet.components.map.MapEventType;
import sk.drunkenpanda.leaflet.events.Event;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;
import sk.drunkenpanda.leaflet.json.model.JsonEntity;

public abstract class LeafletAjaxEventBehavior<E extends Event, J extends JsonEntity<E>> extends LeafletAjaxBehavior {

    private final MapEventType eventType;

    private final Class<J> jsonPayloadClass;

    LeafletAjaxEventBehavior(MapEventType eventType, Class<J> jsonPayloadClass) {
        this.eventType = eventType;
        this.jsonPayloadClass = jsonPayloadClass;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        final ResourceReference javascriptReference = this.getJavascriptReference();
        response.render(JavaScriptHeaderItem.forReference(javascriptReference));

        final String script = this.getInitializationScript();
        response.render(OnLoadHeaderItem.forScript(script));
    }

    protected String getInitializationScript() {
        final String callbackScript = this.getCallbackScript().toString();
        final Map map = (Map) this.getComponent();

        return String.format("%1$s.on('%2$s', function(event) { %3$s });",
                map.getMapVarName(), this.eventType.getJavascriptName(), callbackScript);
    }

    protected final MapEventType getEventType() {
        return this.eventType;
    }

    @Override
    protected void respond(AjaxRequestTarget target) {
        StringValue eventJs = this.getVariableValue(this.eventType.getJavascriptName());

        if (!eventJs.isEmpty()) {
            JsonRenderer jsonRenderer = JsonRendererFactory.getJsonRenderer();
            J jsonEvent = jsonRenderer.fromJson(eventJs.toString(), jsonPayloadClass);

            E dragEndEvent = jsonEvent.toModel();
            this.onEvent(dragEndEvent, target);
        }
    }

    protected abstract ResourceReference getJavascriptReference();

    protected abstract void onEvent(E event, AjaxRequestTarget target);

}
