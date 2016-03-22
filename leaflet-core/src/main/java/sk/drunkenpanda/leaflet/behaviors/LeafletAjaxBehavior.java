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
import org.apache.wicket.util.lang.Args;
import org.apache.wicket.util.string.StringValue;
import sk.drunkenpanda.leaflet.components.map.Map;

/**
 * This behavior passes the values of set Leaflet variables to the server via AJAX.
 *
 * @author Jan Ferko
 */
public abstract class LeafletAjaxBehavior extends AbstractDefaultAjaxBehavior {

    private static final long serialVersionUID = 20160316195034L;

    /** Map of parameters, that are stored to request parameters. */
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

    /**
     * Reads the value of the given javascript variable from the AJAX request.
     *
     * @param parameterName the parameter name of javascript variable whose value should be read.
     *                      Parameter name must have been specified earlier
     *                      using {@link #addJavascriptValue(java.lang.String, java.lang.String)}.
     * @return the string representation of javascript variable
     */
    protected StringValue getVariableValue(String parameterName) {
        Args.notNull(parameterName, "parameterName");

        RequestCycle requestCycle = RequestCycle.get();
        WebRequest request = (WebRequest) requestCycle.getRequest();
        StringValue parameterValue = request.getRequestParameters().getParameterValue(parameterName);
        return parameterValue;
    }

    /**
     * Adds a javascript expression whose value is to be passed from client to server.
     * The result of this expression is sent to server via AJAX and can be accessed using {@link #getVariableValue(java.lang.String) }.
     * A javascript variable with the given name must exist within the scope the callback script of this behavior is called
     * on the client side.
     *
     * @param parameterName the name under which expression can be accessed later.
     * @param expression the javascript expression whose value is passed from client to server.
     */
    protected void addJavascriptValue(String parameterName, String expression) {
        Args.notNull(parameterName, "parameterName");
        Args.notNull(expression, "expression");

        this.parameters.put(parameterName, expression);
    }

    /**
     * Adds set of javascript expressions to be passed from client to server.
     *
     * @param javascriptExpressions a map containing parameter name as key and javascript expression as value
     * @see #addJavascriptValue(java.lang.String, java.lang.String)
     */
    protected void addJavascriptValues(java.util.Map<String, String> javascriptExpressions) {
        Args.notNull(javascriptExpressions, "javascriptExpressions");

        for (java.util.Map.Entry<String, String> expression : javascriptExpressions.entrySet()) {
            this.addJavascriptValue(expression.getKey(), expression.getValue());
        }
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
