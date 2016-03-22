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

package sk.drunkenpanda.leaflet.events;

import sk.drunkenpanda.leaflet.components.map.MapEventType;

/**
 * Model that describes the base event. All other Leaflet events should inherit from this class.
 *
 * @author Jan Ferko
 */
public class Event {

    /**
     * The event type.
     */
    private final MapEventType type;

    /**
     * Constructs new event for given event type.
     *
     * @param type the event type
     */
    public Event(MapEventType type) {
        this.type = type;
    }

    /**
     * Returns type of event.
     *
     * @return type of event
     */
    public MapEventType getType() {
        return type;
    }

}
