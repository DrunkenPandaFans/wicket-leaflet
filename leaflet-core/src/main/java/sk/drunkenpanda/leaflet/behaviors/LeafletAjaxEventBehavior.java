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

/**
 * Abstract base class for processing Leaflet events on server using AJAX.
 *
 * @author Jan Ferko
 * @param <E> the event type processed by this class.
 */
public abstract class LeafletAjaxEventBehavior<E extends Event> extends LeafletAjaxBehavior {

    /**
     * The event type that this behavior is binded to.
     */
    private final MapEventType eventType;

    /**
     * The class of JSON payload that is sent from client when event is fired.
     */
    private final Class<E> jsonPayloadClass;

    /**
     * Constructor creates new instance of even behavior for given event type and json payload.
     *
     * @param eventType the event type that this behavior is binded to.
     * @param jsonPayloadClass The class of JSON payload that is sent from client when event is fired.
     * @param javascriptExpression the javascript expression that is used to retrieve json payload from event.
     */
    LeafletAjaxEventBehavior(MapEventType eventType, Class<E> jsonPayloadClass, String javascriptExpression) {
        this.eventType = eventType;
        this.jsonPayloadClass = jsonPayloadClass;
        this.addJavascriptValue(eventType.getJavascriptName(), javascriptExpression);
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        final ResourceReference javascriptReference = this.getJavascriptReference();
        response.render(JavaScriptHeaderItem.forReference(javascriptReference));

        final String script = this.getInitializationScript();
        response.render(OnLoadHeaderItem.forScript(script));
    }

    /**
     * Returns script that initializes event handler on client.
     *
     * @return the script that initializes event handler on client
     */
    protected String getInitializationScript() {
        final String callbackScript = this.getCallbackScript().toString();
        final Map map = (Map) this.getComponent();

        return String.format("%1$s.on('%2$s', function(event) { %3$s });\n",
                map.getMapVarName(), this.eventType.getJavascriptName(), callbackScript);
    }

    /**
     * Returns event type that is handled by this behavior.
     *
     * @return the event type that this behavior is binded to on client.
     */
    protected final MapEventType getEventType() {
        return this.eventType;
    }

    @Override
    protected void respond(AjaxRequestTarget target) {
        final StringValue eventJs = this.getVariableValue(this.eventType.getJavascriptName());

        if (!eventJs.isEmpty()) {
            final JsonRenderer jsonRenderer = JsonRendererFactory.getJsonRenderer();
            final E event = jsonRenderer.fromJson(eventJs.toString(), jsonPayloadClass);
            this.onEvent(event, target);
        }
    }

    /**
     * Returns reference to additional javascript resources that are needed to process event on client.
     * Usually it provides functions to extract json payload from events.
     *
     * @return the resource reference to additional javascript
     */
    protected abstract ResourceReference getJavascriptReference();

    /**
     * Method that handles event sent from client to server via AJAX.
     * This is usually implemented in client applications not in direct implementations
     * in library.
     *
     * @param event event sent from client to server
     * @param target AJAX request target that was sent when event was fired.
     */
    protected abstract void onEvent(E event, AjaxRequestTarget target);

}
