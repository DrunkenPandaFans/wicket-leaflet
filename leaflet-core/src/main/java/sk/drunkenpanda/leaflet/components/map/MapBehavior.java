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

package sk.drunkenpanda.leaflet.components.map;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;
import sk.drunkenpanda.leaflet.json.JsonRenderer;
import sk.drunkenpanda.leaflet.json.JsonRendererFactory;

/**
 * Behavior that renders necessary javascript to create map on the page.
 *
 * @author Jan Ferko
 */
public final class MapBehavior extends Behavior {

    /** Map that is binded to this behavior. */
    private final Map map;

    /**
     * Constructs new map behavior that is binded to given map.
     * @param map
     */
    public MapBehavior(Map map) {
        this.map = map;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);
        response.render(JavaScriptHeaderItem.forReference(new PackageResourceReference(getClass(), "MapBehavior.js")));

        String optionsScript = getMapOptionsScript();
        String mapScript = String.format("window.%1$s = L.map('%2$s', %3$s);", map.getMapVarName(),
                map.getMarkupId(), getMapOptionsVarName());
        response.render(OnLoadHeaderItem.forScript(optionsScript + mapScript));
    }

    @Override
    public void bind(Component component) {
        if (!(component instanceof Map)) {
            throw new IllegalArgumentException("MapBehavior accepts only Map component.");
        }
        super.bind(component);
    }

    /**
     * Returns javascript variable name that references map options on client.
     *
     * @return javascript variable name that references map options on client.
     */
    private String getMapOptionsVarName() {
        return map.getMapVarName() + "Options";
    }

    /**
     * Renders javascript that creates map options on client and stores it to variable {@link #getMapOptionsVarName() }.
     *
     * @return rendered javascript that creates map options
     */
    private String getMapOptionsScript() {
        JsonRenderer renderer = JsonRendererFactory.getJsonRenderer();
        String optionsVarName = getMapOptionsVarName();
        String options = renderer.toJson(map.getOptions());
        String center = renderer.toJson(map.getModelObject());

        return String.format("var %1$s = WicketLeaflet.Map.prepareOptions(%2$s, %3$s);\n",
                optionsVarName, options, center);
    }
}
