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

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.lang.Args;
import sk.drunkenpanda.leaflet.models.LatLng;
import sk.drunkenpanda.leaflet.resources.LeafletResourcesBehavior;

/**
 * Central component of Leaflet library. It is used to create map on the page.
 *
 * @author Jan Ferko
 */
public class Map extends GenericPanel<LatLng> {

    /** The options to configure map.  */
    private final MapOptions options;

    /**
     * Constructs new map with given id and default options.
     *
     * @param id the map identifier.
     */
    public Map(String id) {
        this(id, new MapOptions());
    }

    /**
     * Constructs new map with given id and model and default configuration.
     * Model should contain center of map or is empty.
     *
     * @param id the map identifier
     * @param model the center of the map
     */
    public Map(String id, IModel<LatLng> model) {
        this(id, model, new MapOptions());
    }

    /**
     * Constructs new map with given identifier and options.
     * Map has empty model
     * @param id the map identifier
     * @param options the map configuration
     * @throws IllegalArgumentException if {@code options} is {@code null}.
     */
    public Map(String id, MapOptions options) {
        super(id);
        Args.notNull(options, "options");
        this.options = options;

        this.initComponent();
    }

    /**
     * Constructs new map with given identifier, model and configuration.
     *
     * @param id the map identifier
     * @param model the center of map
     * @param options the map configuration
     * @throws IllegalArgumentException if {@code options} is {@code null}
     */
    public Map(String id, IModel<LatLng> model, MapOptions options) {
        super(id, model);
        Args.notNull(options, "options");
        this.options = options;

        this.initComponent();
    }

    /**
     * Initializes map component and all necessary resources.
     */
    private void initComponent() {
        add(LeafletResourcesBehavior.instance());
        add(new MapBehavior(this));
    }

    /**
     * Returns map options associated with this map.
     *
     * @return map options associated with this map
     */
    public MapOptions getOptions() {
        return this.options;
    }

    /**
     * Returns javascript variable that stores reference to map on client.
     *
     * @return javascript variable that stores reference to map
     */
    public String getMapVarName() {
        return getMarkupId() + "Map";
    }
}
