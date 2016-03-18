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

import java.util.HashMap;
import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.util.string.StringValue;
import sk.drunkenpanda.leaflet.components.map.Map;

public abstract class LeafletAjaxBehavior extends AbstractDefaultAjaxBehavior {

    private static final long serialVersionUID = 20160316195034L;

    private final java.util.Map<String, String> parameters = new HashMap<String, String>();

    @Override
    public CharSequence getCallbackScript() {
        String script = super.getCallbackScript().toString();
        for (java.util.Map.Entry<String, String> entry : this.parameters.entrySet()) {
            String expression = entry.getValue();
            script = script.replace("\"" + expression + "\"", expression);
        }
        return script;
    }

    protected StringValue getVariableValue(String parameterName) {
        RequestCycle requestCycle = RequestCycle.get();
        WebRequest request = (WebRequest) requestCycle.getRequest();
        StringValue parameterValue = request.getRequestParameters().getParameterValue(parameterName);
        return parameterValue;
    }

    protected void addJavascriptValue(String parameterName, String expression) {
        this.parameters.put(parameterName, expression);
    }

    protected void addJavascriptValues(java.util.Map<String, String> javascriptExpressions) {
        this.parameters.putAll(javascriptExpressions);
    }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);
        for (java.util.Map.Entry<String, String> entry : this.parameters.entrySet()) {
            String parameterName = entry.getKey();
            String parameterValue = entry.getValue();
            attributes.getExtraParameters().put(parameterName, parameterValue);
        }
    }

    @Override
    protected void onBind() {
        super.onBind();
        if(!(getComponent() instanceof Map)) {
            throw new IllegalStateException(
                    LeafletAjaxBehavior.class.getName() + " can only be bound to component of type "
                    + Map.class.getName() + ".");
        }
    }

}
