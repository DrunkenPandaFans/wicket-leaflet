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
 * Event that triggers when dragging map component ends.
 *
 * @author Jan Ferko
 */
public final class DragEndEvent extends Event {

    private final Number distance;

    /**
     * Constructs new drag end event for given distance.
     *
     * @param type the event type.
     * @param distance the distance in pixels the draggable element was moved.
     */
    public DragEndEvent(MapEventType type, Number distance) {
        super(type);
        this.distance = distance;
    }

    /**
     * Returns the distance in pixels the draggable element was moved.
     *
     * @return the distance in pixels the draggable element was moved
     */
    public Number getDistance() {
        return this.distance;
    }

}
