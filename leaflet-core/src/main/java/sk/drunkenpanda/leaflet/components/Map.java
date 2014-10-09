/*
 * Copyright 2014 Jan Ferko.
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

package sk.drunkenpanda.leaflet.components;

import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnLoadHeaderItem;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import sk.drunkenpanda.leaflet.resources.LeafletResourcesBehavior;

/**
 *
 * @author Jan Ferko
 */
public class Map<T> extends GenericPanel<T> {

    public Map(String id) {
        super(id);
    }

    public Map(String id, IModel<T> model) {
        super(id, model);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);
        response.render(OnLoadHeaderItem.forScript(getScript()));
    }        

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(LeafletResourcesBehavior.instance());
    }

    protected String getScript() {
        return "var " + getMarkupId() + " = L.map('" + getMarkupId() + "', {\n"
                + "    center: [51.505, -0.09],\n"
                + "    zoom: 13});"
                + "var osmUrl = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';\n" 
                + "osm = L.tileLayer(osmUrl, {maxZoom: 18});"
                + "osm.addTo(" + getMarkupId() + ");";
    }
}
