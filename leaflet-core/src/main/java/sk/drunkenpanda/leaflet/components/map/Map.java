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

package sk.drunkenpanda.leaflet.components.map;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.lang.Args;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.resources.LeafletResourcesBehavior;

public class Map extends GenericPanel<LatLng> {

    private MapOptions options;

    public Map(String id) {
        this(id, new Model<LatLng>(), new MapOptions());
    }

    public Map(String id, IModel<LatLng> model) {
        this(id, model, new MapOptions());
    }

    public Map(String id, MapOptions options) {
        this(id, new Model<LatLng>(), options);
    }

    public Map(String id, IModel<LatLng> model, MapOptions options) {
        super(id, model);
        Args.notNull(options, "options");
        this.options = options;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(LeafletResourcesBehavior.instance());
        add(new MapBehavior(this));
    }

    public MapOptions getOptions() {
        return this.options;
    }

    public Map setOptions(MapOptions options) {
        this.options = options;
        return this;
    }

    public String getMapVarName() {
        return getMarkupId() + "Map";
    }
}
